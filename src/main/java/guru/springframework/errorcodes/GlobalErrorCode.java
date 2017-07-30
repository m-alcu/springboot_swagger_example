package guru.springframework.errorcodes;

/**
 * Definition of Global Error Codes that can be used in every module.
 */
public enum GlobalErrorCode implements ErrorCode {

    UNKNOWN_ERROR("BAS-000"),

    /** The server error. */
    SERVER_ERROR("BAS-401"),

    /** The entity not found. */
    ENTITY_NOT_FOUND("BAS-402"),

    /** The entity exists. */
    ENTITY_EXISTS("BAS-403"),

    /** The result number exceeded. */
    RESULT_NUMBER_EXCEEDED("BAS-404"),

    /** Invalid credentials/unauthorized error (used with 403 HTTP status) */
    INVALID_CREDENTIALS("BAS-405"),

    /** The invalid parameter value. */
    INVALID_PARAMETER_VALUE("BAS-406"),

    /** The missing configuration. */
    MISSING_CONFIGURATION("BAS-407"),

    /** The missing header. */
    MISSING_HEADER("BAS-408"),

    /** The parameter required. */
    PARAMETER_REQUIRED("BAS-409"),

    /** The validation error. */
    VALIDATION_ERROR("BAS-410"),

    /** Access denied/forbidden error (used with 401 HTTP status) */
    ACCESS_DENIED("BAS-411");

    /** The code. */
    private String code;

    /**
     * Instantiates a new global error code.
     *
     * @param code the code
     */
    GlobalErrorCode(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

}
