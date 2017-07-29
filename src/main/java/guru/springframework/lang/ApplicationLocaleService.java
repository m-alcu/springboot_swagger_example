package guru.springframework.lang;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

/**
 * Service responsible to resolve the applicable language/locale according to Locale configuration (languages supported by the system)
 */
public class ApplicationLocaleService {

    private final Locale defaultLocale;
    private final List<Locale> supportedLocales;

    /**
     * Instantiates a new {@link ApplicationLocaleService}
     *
     * @param defaultLocale the default locale
     * @param supportedLocales the supported locales
     */
    public ApplicationLocaleService(Locale defaultLocale, List<Locale> supportedLocales) {
        this.defaultLocale = defaultLocale;
        this.supportedLocales = supportedLocales;
    }

    /**
     * Resolves the applicable language from the given language range (comma-separated array of languages).
     *
     * @param languageRange the language range
     * @return the applicable language from the given range or the default language if none of them are supported
     */
    public String getLanguageByLanguageRange(String languageRange) {
        return getLocaleByLanguageRange(languageRange).getLanguage();
    }

    /**
     * Resolves the applicable Locale from the given language range (comma-separated array of languages).
     *
     * @param languageRange the language range
     * @return the applicable Locale from the given range or the default Locale if none of them are supported
     */
    public Locale getLocaleByLanguageRange(String languageRange) {
        if (StringUtils.isBlank(languageRange)) {
            return defaultLocale;
        }
        final List<Locale.LanguageRange> list = Locale.LanguageRange.parse(languageRange);
        Locale locale = Locale.lookup(list, supportedLocales);
        if (locale == null) {
            locale = defaultLocale;
        }
        return locale;
    }

    /**
     * Gets the default Locale of the application
     * 
     * @return a Locale
     */
    public Locale getDefaultLocale() {
        return defaultLocale;
    }
}
