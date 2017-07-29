package guru.springframework.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.LocaleResolver;

import guru.springframework.util.Constants;



/**
 * Filter with the ability to read the languages passed in the Accept-Language request header and resolve the Locale to be used by the application to
 * fulfill the request. It also sets the Content-Value request header according to the used locale
 */
@Component
public class ResponseLocaleFilter extends OncePerRequestFilter {

    @Autowired
    private LocaleResolver localeResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException,
        IOException {
        // Setting Spring LocaleContextHolder according to the language in the request
        LocaleContextHolder.resetLocaleContext();
        LocaleContextHolder.setLocale(localeResolver.resolveLocale(request));

        response.addHeader(Constants.HEADER_CONTENT_LANGUAGE, LocaleContextHolder.getLocale().getLanguage());
        response.addHeader(Constants.HEADER_VARY, Constants.HEADER_ACCEPT_LANGUAGE);

        filterChain.doFilter(request, response);
    }

}
