package io.gabriel.taskmanager.infra;

public class CustomErrorResponse {
    private int status;
    private String error;
    private String customMessage;

    public CustomErrorResponse(int status, String error, String message) {
        this.setStatus(status);
        this.setError(error);
        this.setCustomMessage(message);
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setCustomMessage(String message) {
        this.customMessage = message;
    }
}
