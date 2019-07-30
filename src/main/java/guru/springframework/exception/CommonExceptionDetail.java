package guru.springframework.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;

import guru.springframework.errorcodes.ErrorCode;

/**
 * Class to represent the details (list of inner errors) of an exception
 *
 * @author yramos on 25/01/2017.
 */
public class CommonExceptionDetail implements Serializable {

    private static final long serialVersionUID = -8532344035686567914L;

    private ErrorCode errorCode;
    private String domain;
    private String reason;
    private Object[] errorMessageParams;

    /**
     * Creates an instance of {@link CommonExceptionDetail}
     * 
     * @param errorCode the error
     * @param domain the entity of the error
     * @param reason the reason of the error
     * @param errorMessageParams parameters to personalize the error message
     */
    public CommonExceptionDetail(ErrorCode errorCode, String domain, String reason, Object... errorMessageParams) {
        this.errorCode = errorCode;
        this.domain = domain;
        this.reason = reason;
        this.setErrorMessageParams(errorMessageParams);
    }

    /**
     * Creates an instance of {@link CommonExceptionDetail}
     *
     * @param errorCode the error
     * @param domain the entity of the error
     * @param errorMessageParams parameters to personalize the error message
     */
    public CommonExceptionDetail(ErrorCode errorCode, String domain, Object... errorMessageParams) {
        this.errorCode = errorCode;
        this.domain = domain;
        this.setErrorMessageParams(errorMessageParams);
    }

    /**
     * Creates an instance of {@link CommonExceptionDetail}
     *
     * @param errorCode the error
     * @param errorMessageParams parameters to personalize the error message
     */
    public CommonExceptionDetail(ErrorCode errorCode, Object... errorMessageParams) {
        this.errorCode = errorCode;
        this.setErrorMessageParams(errorMessageParams);
    }

    /**
     * Creates an instance of {@link CommonExceptionDetail}
     *
     * @param errorCode the error
     * @param domain the entity of the error
     * @param reason the exception causing the error
     * @param errorMessageParams parameters to personalize the error message
     */
    public CommonExceptionDetail(ErrorCode errorCode, String domain, Throwable reason, Object... errorMessageParams) {
        this.errorCode = errorCode;
        this.domain = domain;
        this.reason = ExceptionUtils.getStackTrace(reason.getCause());
        this.setErrorMessageParams(errorMessageParams);
    }

    /**
     * Creates an instance of {@link CommonExceptionDetail}
     *
     * @param errorCode the error
     * @param reason the exception causing the error
     * @param errorMessageParams parameters to personalize the error message
     */
    public CommonExceptionDetail(ErrorCode errorCode, Throwable reason, Object... errorMessageParams) {
        this.errorCode = errorCode;
        this.reason = ExceptionUtils.getStackTrace(reason.getCause());
        this.setErrorMessageParams(errorMessageParams);
    }

    /**
     * Gets the error code
     * 
     * @return the error code
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    /**
     * Gets the entity where the error occurred
     * 
     * @return the entity
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Gets the reason causing the error, normally the stacktrace
     * 
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Gets the parameters to be used to personalize the error message
     *
     * @return the array of message parameters
     */
    public Object[] getErrorMessageParams() {
        return errorMessageParams;
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
