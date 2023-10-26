package dev.Innocent.CRUDREST.Exception;

public class StudentErrorResponse {
    private int Status;
    private String message;
    private Long timeStamp;

    public StudentErrorResponse() {
    }

    public StudentErrorResponse(int status, String message, Long timeStamp) {
        Status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
