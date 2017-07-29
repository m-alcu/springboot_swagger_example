package guru.springframework.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Represents an error in the application. It could have details in the form of a list of another errors
 */
@ApiModel(value = "Error")
@JsonTypeName(value = "Error")
@JsonPropertyOrder({
    "code", "message", "errors", "reason"
})
public class ErrorDto implements Serializable {

    private static final long serialVersionUID = -427042395609265085L;

    @ApiModelProperty(value = "Error code")
    @JsonProperty("code")
    private String code;

    @ApiModelProperty(value = "Error message")
    @JsonProperty("message")
    private String message;

    @ApiModelProperty(value = "Error reason")
    @JsonProperty("reason")
    private String reason;

    @ApiModelProperty(value = "Error details")
    @JsonProperty("errors")
    private List<ErrorDetailDto> details;

    /**
     * Instantiates a new Error dto.
     */
    public ErrorDto() {
        super();
    }

    /**
     * Instantiates a new Error dto.
     *
     * @param code the code
     * @param message the message
     * @param reason the reason
     * @param details the details
     */
    public ErrorDto(String code, String message, String reason, List<ErrorDetailDto> details) {
        this.code = code;
        this.message = message;
        this.reason = reason;
        this.details = details;
    }

    /**
     * Instantiates a new Error dto.
     *
     * @param code the code
     * @param message the message
     * @param reason the reason
     */
    public ErrorDto(String code, String message, String reason) {
        this.code = code;
        this.message = message;
        this.reason = reason;
    }

    /**
     * Instantiates a new Error dto.
     *
     * @param code the code
     * @param message the message
     */
    public ErrorDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Instantiates a new Error dto.
     *
     * @param code the code
     * @param message the message
     * @param details the details
     */
    public ErrorDto(String code, String message, List<ErrorDetailDto> details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

    /**
     * Adds a new detail to the current error
     * 
     * @param errorDetail the new error detail
     */
    public void addErrorDetail(ErrorDetailDto errorDetail) {
        if (this.details == null) {
            this.details = new ArrayList<>();
        }
        this.details.add(errorDetail);
    }

    /**
     * Unique identifier representing the error.
     * 
     * @return the error code
     */
    public String getCode() {
        return code;
    }

    /**
     * A readable, user-friendly message describing the error
     *
     * @return the error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * A detailed message providing further information of the cause of the error. Typically it will be the exception stacktrace.
     * 
     * @return the error reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Container for any additional information regarding the error. If the service returns multiple errors, each element in the details array
     * represents a different error.
     *
     * @return the details
     */
    public List<ErrorDetailDto> getDetails() {
        return details;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
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
     * Sets reason.
     *
     * @param reason the reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Sets details.
     *
     * @param details the details
     */
    public void setDetails(List<ErrorDetailDto> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("code: ").append(code).append(", message: ").append(message).append(", errors: {");
        if (CollectionUtils.isNotEmpty(this.details)) {
            for (final ErrorDetailDto detail : details) {
                sb.append('[').append(detail).append("] ");
            }
        }
        sb.append("}, ");

        sb.append("reason: ").append(reason);
        return sb.toString();
    }

}
