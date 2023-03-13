package model;

import java.util.List;

public class JSONFailureResponse {

    private String timestamp;
    private Integer status;
    private List<String> errors;

    private String message;

    public JSONFailureResponse() {
    }

    public JSONFailureResponse(String timestamp, Integer status, List<String> errors, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.errors = errors;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "JSONFailureResponse{" +
                "timestamp='" + timestamp + '\'' +
                ", status=" + status +
                ", errors=" + errors +
                '}';
    }
}