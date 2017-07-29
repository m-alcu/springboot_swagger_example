package guru.springframework.exception;

import guru.springframework.errorcodes.ErrorCode;

/**
 * General purpose exception
 */
public final class BackofficeException extends AbstractCommonException {

    /**
     * 
     */
    private static final long serialVersionUID = 3237670392459228721L;

    /**
     * Instantiates a new Backoffice exception.
     *
     * @param message the message
     */
    public BackofficeException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Backoffice exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public BackofficeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Backoffice exception.
     *
     * @param errorCode the error code
     * @param errorMessageParams the error message params
     */
    public BackofficeException(ErrorCode errorCode, Object... errorMessageParams) {
        super(errorCode, errorMessageParams);
    }

    /**
     * Instantiates a new Backoffice exception.
     *
     * @param errorCode the error code
     * @param cause the cause
     * @param errorMessageParams the error message params
     */
    public BackofficeException(ErrorCode errorCode, Throwable cause, Object... errorMessageParams) {
        super(errorCode, cause, errorMessageParams);
    }

    /**
     * Instantiates a new Backoffice exception.
     *
     * @param message the message
     * @param errorCode the error code
     * @param errorMessageParams the error message params
     */
    public BackofficeException(String message, ErrorCode errorCode, Object... errorMessageParams) {
        super(message, errorCode, errorMessageParams);
    }

    /**
     * Instantiates a new Backoffice exception.
     *
     * @param message the message
     * @param errorCode the error code
     * @param cause the cause
     * @param errorMessageParams the error message params
     */
    public BackofficeException(String message, ErrorCode errorCode, Throwable cause, Object... errorMessageParams) {
        super(message, errorCode, cause, errorMessageParams);
    }
}
