package guru.springframework.exception;

import guru.springframework.errorcodes.GlobalErrorCode;

/**
 * The Class MissingHeaderException.
 */
public final class MissingHeaderException extends AbstractCommonException {

    /**
     * 
     */
    private static final long serialVersionUID = 2300237759720228477L;

    private final String headerName;

    /**
     * Instantiates a new Missing header exception.
     *
     * @param headerName the header name
     */
    public MissingHeaderException(String headerName) {
        super(GlobalErrorCode.MISSING_HEADER, headerName);
        this.headerName = headerName;
    }

    /**
     * Gets header name.
     *
     * @return the header name
     */
    public String getHeaderName() {
        return headerName;
    }
}
