package guru.springframework.exception;

import guru.springframework.errorcodes.GlobalErrorCode;

/**
 * The Class ParameterNotFoundException.
 */
public final class ParameterNotFoundException extends AbstractCommonException {

    /**
     * 
     */
    private static final long serialVersionUID = -1132695574379504767L;

    private final String parameterName;

    /**
     * Instantiates a new Parameter not found exception.
     *
     * @param parameterName the parameter name
     */
    public ParameterNotFoundException(String parameterName) {
        super(GlobalErrorCode.PARAMETER_REQUIRED, parameterName);
        this.parameterName = parameterName;
    }

    /**
     * Gets parameter name.
     *
     * @return the parameter name
     */
    public String getParameterName() {
        return parameterName;
    }
}
