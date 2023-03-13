package model;

import java.util.List;

public class JSONFailureResponseDelete {

    private String timestamp;
    private Integer status;
    private String message;

    public JSONFailureResponseDelete(String timestamp, Integer status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    public JSONFailureResponseDelete() {
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
                ", errors=" + message +
                '}';
    }
}
