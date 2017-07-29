package guru.springframework.filter;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import guru.springframework.lang.ApplicationLocaleService;
import guru.springframework.util.Constants;



/**
 * Locale resolver with ability to select the more suitable Locale using the Accept-Language header of the HTTP Request and the application supported
 * languages
 */
public class SmartLocaleResolver extends AcceptHeaderLocaleResolver {

    @Autowired
    private ApplicationLocaleService localeService;

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        return localeService.getLocaleByLanguageRange(request.getHeader(Constants.HEADER_ACCEPT_LANGUAGE));
    }

}
