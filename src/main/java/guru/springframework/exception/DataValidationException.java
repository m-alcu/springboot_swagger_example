package guru.springframework.exception;

import guru.springframework.errorcodes.ErrorCode;

/**
 * Exception to indicate errors related with the data access.
 */
public class DataValidationException extends AbstractCommonException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8342713450074626594L;

    /**
     * Instantiates a new Data validation exception.
     *
     * @param code the code
     * @param cause the cause
     * @param messageParameters the message parameters
     */
    public DataValidationException(ErrorCode code, Throwable cause, Object... messageParameters) {
        super(code, cause, messageParameters);
    }

    /**
     * Instantiates a new Data validation exception.
     *
     * @param code the code
     * @param messageParameters the message parameters
     */
    public DataValidationException(ErrorCode code, Object... messageParameters) {
        super(code, messageParameters);
    }
}
