package guru.springframework.exception;

import guru.springframework.errorcodes.GlobalErrorCode;

/**
 * An expected configuration property or parameter doesn't exist
 */
public class MissingConfigurationException extends AbstractCommonException {

    private static final long serialVersionUID = -1365045282335958438L;

    /**
     * Instantiates a new Missing configuration exception.
     *
     * @param cause the cause
     * @param messageParameters the message parameters
     */
    public MissingConfigurationException(Throwable cause, Object... messageParameters) {
        super(GlobalErrorCode.MISSING_CONFIGURATION, cause, messageParameters);
    }

    /**
     * Instantiates a new Missing configuration exception.
     *
     * @param messageParameters the message parameters
     */
    public MissingConfigurationException(Object[] messageParameters) {
        super(GlobalErrorCode.MISSING_CONFIGURATION, messageParameters);
    }

}
