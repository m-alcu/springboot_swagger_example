package guru.springframework.exception;

import guru.springframework.errorcodes.GlobalErrorCode;

/**
 * The Class InvalidParameterValueException.
 */
public final class InvalidParameterValueException extends AbstractCommonException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6147359093126358541L;

    private final String parameterName;
    private final String parameterValue;

    /**
     * Instantiates a new Invalid parameter value exception.
     *
     * @param parameterName the parameter name
     * @param parameterValue the parameter value
     */
    public InvalidParameterValueException(String parameterName, String parameterValue) {
        super(GlobalErrorCode.INVALID_PARAMETER_VALUE, parameterValue, parameterName);
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
    }

    /**
     * Gets parameter name.
     *
     * @return the parameter name
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     * Gets parameter value.
     *
     * @return the parameter value
     */
    public String getParameterValue() {
        return parameterValue;
    }
}
