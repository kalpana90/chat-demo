package com.message.app.dto;

public class SendMessageRequestDTO {

    private String message;
    private String userId;
    private String senderId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        return "SendMessageRequestDTO{" +
                "message='" + message + '\'' +
                ", userId='" + userId + '\'' +
                ", senderId='" + senderId + '\'' +
                '}';
    }
}
