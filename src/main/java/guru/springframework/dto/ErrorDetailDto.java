package guru.springframework.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

/**
 * DTO to represent the details (list of inner errors) of an error
 *
 * @author yramos on 25/01/2017.
 */
@JsonPropertyOrder({
    "domain", "message", "reason"
})
public class ErrorDetailDto implements Serializable {

    private static final long serialVersionUID = 3052513175800561504L;

    @ApiModelProperty(value = "Error detail domain")
    @JsonProperty("domain")
    private String domain;

    @ApiModelProperty(value = "Error detail message")
    @JsonProperty("message")
    private String message;

    @ApiModelProperty(value = "Error detail reason")
    @JsonProperty("reason")
    private String reason;

    /**
     * Instantiates a new Error detail dto.
     */
    public ErrorDetailDto() {
        super();
    }

    /**
     * Instantiates a new Error detail dto.
     *
     * @param domain the domain
     * @param message the message
     * @param reason the reason
     */
    public ErrorDetailDto(String domain, String message, String reason) {
        this.domain = domain;
        this.message = message;
        this.reason = reason;
    }

    /**
     * Instantiates a new Error detail dto.
     *
     * @param message the message
     * @param reason the reason
     */
    public ErrorDetailDto(String message, String reason) {
        this.message = message;
        this.reason = reason;
    }

    /**
     * Unique identifier for the service raising this error. This helps distinguish service-specific errors (i.e. error inserting an event in a
     * calendar) from general protocol errors (i.e. file not found).
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * {
     *     "error":{
     *         "errors": [{"domain": "Calendar"}]
     *     }
     * }
     * </pre>
     *
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets domain.
     *
     * @param domain the domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * A human readable message providing more details about the error.
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * {
     *     "error":{
     *         "code": 404
     *         "message": "File could not be read",
     *         "errors": [{"domain":"Voucher", "message": "File Not Found"}]
     *     }
     * }
     * </pre>
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Further details about what caused the error. Usually this will be the stacktrace of an exception.
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * {
     *     "error":{
     *         "errors": [{"reason": "ResourceNotFoundException"}]
     *     }
     * }
     * </pre>
     *
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets reason.
     *
     * @param reason the reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("domain: ").append(domain).append(", message: ").append(message).append(", reason: ").append(reason);
        return sb.toString();
    }

}
