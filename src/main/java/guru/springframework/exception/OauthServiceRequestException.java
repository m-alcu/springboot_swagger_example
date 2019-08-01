package guru.springframework.exception;

/**
 * Used to signal that an error occurred when making a request to the OAuth2 Service.
 */
public class OauthServiceRequestException extends RuntimeException {

    private static final long serialVersionUID = 3084270736453365713L;

    /**
     * @see Exception#Exception()
     */
    public OauthServiceRequestException() {
        super();
    }

    /**
     * @see Exception#Exception(String)
     * @param message the message
     */
    public OauthServiceRequestException(String message) {
        super(message);
    }

    /**
     * @see Exception#Exception(Throwable)
     * @param cause the cause
     */
    public OauthServiceRequestException(Throwable cause) {
        super(cause);
    }

    /**
     * @see Exception#Exception(String, Throwable)
     * @param message the message
     * @param cause the cause
     */
    public OauthServiceRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
