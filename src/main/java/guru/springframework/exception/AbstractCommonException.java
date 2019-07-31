package guru.springframework.exception;

import java.util.ArrayList;
import java.util.List;

import guru.springframework.errorcodes.ErrorCode;
import guru.springframework.errorcodes.GlobalErrorCode;



/**
 * Common exception for generic use. It will serve as the base for other exceptions.
 */
public abstract class AbstractCommonException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8953928427610643197L;

    private final ErrorCode errorCode;
    private List<CommonExceptionDetail> exceptionDetails;
    private Object[] errorMessageParams;

    /**
     * Instantiates a new abstract common exception.
     *
     * @param message the message
     */
    public AbstractCommonException(String message) {
        super(message);
        errorCode = GlobalErrorCode.UNKNOWN_ERROR;
    }

    /**
     * Instantiates a new abstract common exception.
     *
     * @param message the message
     * @param cause what caused the exception
     */
    public AbstractCommonException(String message, Throwable cause) {
        super(message, cause);
        errorCode = GlobalErrorCode.UNKNOWN_ERROR;
    }

    /**
     * Instantiates a new abstract common exception.
     *
     * @param errorCode the error code
     * @param errorMessageParams parameters to personalize the error message
     */
    public AbstractCommonException(ErrorCode errorCode, Object... errorMessageParams) {
        super();
        this.errorCode = errorCode;
        this.setErrorMessageParams(errorMessageParams);
    }

    /**
     * Instantiates a new abstract common exception.
     *
     * @param errorCode the error code
     * @param cause what caused the exception
     * @param errorMessageParams parameters to personalize the error message
     */
    public AbstractCommonException(ErrorCode errorCode, Throwable cause, Object... errorMessageParams) {
        super(cause);
        this.errorCode = errorCode;
        this.setErrorMessageParams(errorMessageParams);
    }

    /**
     * Instantiates a new abstract common exception.
     *
     * @param message the message
     * @param errorCode the error code
     * @param errorMessageParams parameters to personalize the error message
     */
    public AbstractCommonException(String message, ErrorCode errorCode, Object... errorMessageParams) {
        super(message);
        this.errorCode = errorCode;
        this.setErrorMessageParams(errorMessageParams);
    }

    /**
     * Instantiates a new abstract common exception.
     *
     * @param message the message
     * @param errorCode the error code
     * @param cause what caused the exception
     * @param errorMessageParams parameters to personalize the error message
     */
    public AbstractCommonException(String message, ErrorCode errorCode, Throwable cause, Object... errorMessageParams) {
        super(message, cause);
        this.errorCode = errorCode;
        this.setErrorMessageParams(errorMessageParams);
    }

    /**
     * Gets the error code.
     *
     * @return the error code
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    /**
     * Gets the list of errors that explains the current exception
     * 
     * @return the list of {@link CommonExceptionDetail}s
     */
    public List<CommonExceptionDetail> getExceptionDetails() {
        return exceptionDetails;
    }

    /**
     * Gets the parameters to be used to personalize the error message
     * 
     * @return the array of message parameters
     */
    public Object[] getErrorMessageParams() {
        return errorMessageParams;
    }

    /**
     * Adds a new error to the list of errors of this exception
     * 
     * @param detail the error detail to add
     */
    public void addExceptionDetail(CommonExceptionDetail detail) {
        if (this.exceptionDetails == null) {
            this.exceptionDetails = new ArrayList<>();
        }
        this.exceptionDetails.add(detail);
    }

    /**
     * Sets exception details.
     *
     * @param exceptionDetails the exception details
     */
    public void setExceptionDetails(List<CommonExceptionDetail> exceptionDetails) {
        this.exceptionDetails = exceptionDetails;
    }

    private void setErrorMessageParams(Object[] errorMessageParams) {
        List<Object> params = new ArrayList<>();
        if (errorMessageParams != null) {
            for (Object messageParam : errorMessageParams) {
                if (messageParam != null) {
                    params.add(messageParam);
                }
            }
        }
        this.errorMessageParams = params.toArray();
    }
}
