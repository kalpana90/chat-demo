package com.message.app.dto;

public class LoginResponseDTO {

    private boolean status;
    private String statusMessage;
    private long userId;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "status=" + status +
                ", statusMessage='" + statusMessage + '\'' +
                ", userId=" + userId +
                '}';
    }
}
