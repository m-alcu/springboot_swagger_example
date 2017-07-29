package guru.springframework.lang;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Useful class to resolver error messages
 * 
 * @author yramos on 07/02/2017.
 */
@Component
public class ErrorMessageResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorMessageResolver.class);
    private static final String FILE_NAME_ERRORS = "error_codes.service_error";

    @Autowired
    private ApplicationLocaleService applicationLocaleService;

    /**
     * Gets the internationalized message for the given error code.
     *
     * @param errorCode the error code
     * @param errorMessageParams parameters to personalize the error message
     * @return the internationalized message
     */
    public String getMessage(String errorCode, Object... errorMessageParams) {
        String message;
        try {
            message = ResourceBundle.getBundle(FILE_NAME_ERRORS, LocaleContextHolder.getLocale()).getString(errorCode);
        } catch (final MissingResourceException mre) {
            LOGGER.warn("Message for error code {} with language {} not found. Setting english locale. Error: {}", errorCode,
                LocaleContextHolder.getLocale().getLanguage(), mre);
            // Lets check default resource
            message = getInternationalizedMessageForDefaultLocale(errorCode);
        }
        if (ArrayUtils.isNotEmpty(errorMessageParams)) {
            message = String.format(message, errorMessageParams);
        }
        return message;
    }

    /**
     * Sets the default language message.
     *
     * @param errorCode the error code
     * @return the string
     */
    private String getInternationalizedMessageForDefaultLocale(String errorCode) {
        String message;
        try {
            message = ResourceBundle.getBundle(FILE_NAME_ERRORS, this.applicationLocaleService.getDefaultLocale()).getString(errorCode);
        } catch (final MissingResourceException e) {
            LOGGER.error("Message for error code {} using the default language not found: {} ", errorCode, e);
            message = "?" + errorCode + "?";
        }
        return message;
    }
}
