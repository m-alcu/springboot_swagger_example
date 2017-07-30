package guru.springframework.common.exception;

import java.sql.SQLException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import guru.springframework.dto.ErrorDetailDto;
import guru.springframework.dto.ErrorDto;
import guru.springframework.errorcodes.ErrorCode;
import guru.springframework.errorcodes.GlobalErrorCode;
import guru.springframework.exception.AbstractCommonException;
import guru.springframework.exception.BackofficeException;
import guru.springframework.exception.CommonExceptionDetail;
import guru.springframework.exception.EntityNotFoundException;
import guru.springframework.exception.FeignClientErrorException;
import guru.springframework.exception.InvalidParameterValueException;
import guru.springframework.exception.MissingHeaderException;
import guru.springframework.exception.ParameterNotFoundException;
import guru.springframework.lang.ErrorMessageResolver;

/**
 * Global Exception Handler.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private ErrorMessageResolver errorMessageResolver;

    /**
     * Handle rest exception.
     *
     * @param exception the exception
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(AbstractCommonException.class)
    public ResponseEntity<Object> handleAbstractCommonException(AbstractCommonException exception, WebRequest request) {
        return handleExceptionInternal(exception, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    /**
     * Handle backoffice exception response entity.
     *
     * @param exception the exception
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(BackofficeException.class)
    public ResponseEntity<Object> handleBackofficeException(BackofficeException exception, WebRequest request) {
        return handleExceptionInternal(exception, null, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    /**
     * Handle entity not found exception response entity.
     *
     * @param exception the exception
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest request) {
        return handleExceptionInternal(exception, null, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    /**
     * Handle hystrix runtime exception.
     *
     * @param exception the exception
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(HystrixRuntimeException.class)
    public ResponseEntity<Object> handleHystrixRuntimeException(final HystrixRuntimeException exception, WebRequest request) {
        ResponseEntity<Object> result;

        if (exception.getCause() instanceof FeignClientErrorException) {
            final FeignClientErrorException feignException = (FeignClientErrorException) exception.getCause();
            final ErrorDto error =
                new ErrorDto(feignException.getErrorResult().getCode(), feignException.getErrorResult().getMessage(),
                    feignException.getErrorResult().getReason(), feignException.getErrorResult().getDetails());

            result = handleExceptionInternal(exception, error, new HttpHeaders(), feignException.getHttpStatus(), request);
        } else {
            result = handleUnknownException(exception, request);
        }

        return result;
    }

    /**
     * Handle parameter and header exception response entity.
     *
     * @param exception the exception
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler({
        InvalidParameterValueException.class, MissingHeaderException.class, ParameterNotFoundException.class
    })
    public ResponseEntity<Object> handleParameterAndHeaderException(Exception exception, WebRequest request) {
        return handleExceptionInternal(exception, null, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handle user exception.
     *
     * @param exception the exception
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(UserException.class)
    public ResponseEntity<Object> handleUserException(UserException exception, WebRequest request) {
        return this.handleExceptionInternal(exception, null, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handle security exception.
     *
     * @param exception the exception
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<Object> handleSecurityException(SecurityException exception, WebRequest request) {
        return this.handleExceptionInternal(exception, null, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    /**
     * Handle data base exception.
     *
     * @param exception the exception
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler({
        SQLException.class, DataAccessException.class
    })
    public ResponseEntity<Object> handleDataBaseException(Exception exception, WebRequest request) {
        return this.handleExceptionInternal(exception, null, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    /**
     * Handle unknown exception.
     *
     * @param exception the exception
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownException(Exception exception, WebRequest request) {
        return this.handleExceptionInternal(exception, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    /**
     * Handle access denied exception.
     *
     * @param exception the exception
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception, WebRequest request) {
        ErrorDto error = new ErrorDto();
        error.setCode(GlobalErrorCode.ACCESS_DENIED.getCode());
        error.setMessage(this.errorMessageResolver.getMessage(error.getCode()));
        error.setReason(this.getReasonFromException(exception));
        return this.handleExceptionInternal(exception, error, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request) {
        ErrorDto errorDto = new ErrorDto(GlobalErrorCode.VALIDATION_ERROR.getCode(),
            this.errorMessageResolver.getMessage(GlobalErrorCode.VALIDATION_ERROR.getCode()), this.getReasonFromException(ex));
        for (final ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            final FieldError fieldError = (FieldError) objectError;
            errorDto.addErrorDetail(new ErrorDetailDto(fieldError.getField(), fieldError.getDefaultMessage(), ""));
        }
        LOG.error("Method argument error {}", errorDto.toString());
        return new ResponseEntity<>(errorDto, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
        Exception exception,
        Object body,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request) {
        LOG.error("Message: {}, StackTrace: ", exception.getMessage(), exception);
        final ErrorDto errorResult;
        if (body instanceof ErrorDto) {
            errorResult = (ErrorDto) body;
        } else {
            errorResult = buildErrorResult(exception);
        }
        final HttpStatus responseStatus = status == null ? HttpStatus.INTERNAL_SERVER_ERROR : status;
        return new ResponseEntity<>(errorResult, headers, responseStatus);
    }

    /**
     * Build error result error dto.
     *
     * @param exception the exception
     * @return the error dto
     */
    protected ErrorDto buildErrorResult(Exception exception) {
        ErrorDto errorResult = new ErrorDto();
        errorResult.setReason(this.getReasonFromException(exception));
        errorResult.setCode(GlobalErrorCode.UNKNOWN_ERROR.getCode());
        Object[] errorMessageParams = new Object[0];
        if (exception instanceof AbstractCommonException) {
            AbstractCommonException commonException = (AbstractCommonException) exception;
            errorResult.setCode(commonException.getErrorCode().getCode());
            this.fillCommonExceptionDetails(errorResult, commonException);
            errorMessageParams = commonException.getErrorMessageParams();
        }
        if (ErrorCode.isResolvableCode(errorResult.getCode())) {
            errorResult.setMessage(this.errorMessageResolver.getMessage(errorResult.getCode(), errorMessageParams));
        } else {
            errorResult
                .setMessage(StringUtils.isNotBlank(exception.getCause().getMessage()) ? exception.getCause().getMessage() : exception.getMessage());
        }
        return errorResult;
    }

    /**
     * Gets the error reason from the received exception.
     *
     * @param exception the exception
     * @return the reason from exception
     */
    protected String getReasonFromException(Exception exception) {
        return ExceptionUtils.getStackTrace(exception);
    }

    /**
     * Build common exception details.
     *
     * @param error the error
     * @param commonException the common exception
     */
    protected void fillCommonExceptionDetails(ErrorDto error, AbstractCommonException commonException) {
        if (CollectionUtils.isNotEmpty(commonException.getExceptionDetails())) {
            for (CommonExceptionDetail detail : commonException.getExceptionDetails()) {
                error.addErrorDetail(
                    new ErrorDetailDto(detail.getDomain(),
                        this.errorMessageResolver.getMessage(detail.getErrorCode().getCode(), detail.getErrorMessageParams()), detail.getReason()));
            }
        }
    }

}
