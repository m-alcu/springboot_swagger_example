package guru.springframework.lang;

import java.util.List;
import java.util.Locale;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class to hold the Locale Configuration of the application
 */
@Configuration
@ConfigurationProperties(prefix = "application.locale")
public class LocaleConfiguration {

    private Locale defaultLocale;
    private List<Locale> supportedLocales;

    /**
     * Gets default locale.
     *
     * @return the default locale
     */
    public Locale getDefaultLocale() {
        return defaultLocale;
    }

    /**
     * Sets default locale.
     *
     * @param defaultLocale the default locale
     */
    public void setDefaultLocale(Locale defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    /**
     * Gets supported locales.
     *
     * @return the supported locales
     */
    public List<Locale> getSupportedLocales() {
        return supportedLocales;
    }

    /**
     * Sets supported locales.
     *
     * @param supportedLocales the supported locales
     */
    public void setSupportedLocales(List<Locale> supportedLocales) {
        this.supportedLocales = supportedLocales;
    }

    /**
     * Bean definition for the {@link ApplicationLocaleService}
     *
     * @return the application locale service
     */
    @Bean
    public ApplicationLocaleService applicationLocaleService() {
        return new ApplicationLocaleService(defaultLocale, supportedLocales);
    }
}
