package guru.springframework.exception;

import org.springframework.http.HttpStatus;

import guru.springframework.dto.ErrorDto;



/**
 * Exception used to propagate Feign Client errors (received from service invocations).
 */
public class FeignClientErrorException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -788672913849872689L;

    /** The http status. */
    private final HttpStatus httpStatus;

    /** The error result. */
    private final ErrorDto errorResult;

    /**
     * Instantiates a new feign client error exception.
     *
     * @param errorResult the error result
     * @param httpStatus the http status
     */
    public FeignClientErrorException(ErrorDto errorResult, HttpStatus httpStatus) {
        super();
        this.errorResult = errorResult;
        this.httpStatus = httpStatus;
    }

    /**
     * Gets the error result.
     *
     * @return the error result
     */
    public ErrorDto getErrorResult() {
        return errorResult;
    }

    /**
     * Gets the http status.
     *
     * @return the http status
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
