package guru.springframework.errorcodes;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * General definition for Errors.
 */
public interface ErrorCode extends Serializable {

    /**
     * Gets the code.
     *
     * @return the code
     */
    String getCode();

    /**
     * Utility method to know if a given error code may be one of the handled by the application (controlled error codes). The method looks if the
     * code starts with any of the controlled prefixes
     *
     * @param code the code to be checked
     * @return true if code is not null or empty, and it also starts with any of the managed prefixes; false otherwise
     */
    static boolean isResolvableCode(String code) {
        return StringUtils.isNotBlank(code) && code.startsWith("BAS-");
    }

}
