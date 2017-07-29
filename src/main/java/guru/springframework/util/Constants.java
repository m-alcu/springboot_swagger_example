package guru.springframework.util;

import java.nio.charset.Charset;

/**
 * Global constants available to all layers of the API
 *
 * @author yramos on 17/10/2016.
 */
public final class Constants {

    /**
     * The constant HEADER_TOTAL_COUNT.
     */
    public static final String HEADER_TOTAL_COUNT = "X-Total-Count";
    /**
     * The constant HEADER_ACCEPT_LANGUAGE.
     */
    public static final String HEADER_ACCEPT_LANGUAGE = "Accept-Language";
    /**
     * The constant HEADER_CONTENT_LANGUAGE.
     */
    public static final String HEADER_CONTENT_LANGUAGE = "Content-Language";
    /**
     * The constant HEADER_VARY.
     */
    public static final String HEADER_VARY = "Vary";

    /** The Constant DEFAULT_CHARSET. */
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Constants() {
        //Do nothing... just to avoid instantiation
    }

}
