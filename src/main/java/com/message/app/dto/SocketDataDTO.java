package com.message.app.dto;

public class SocketDataDTO {

    private String message;
    private String userName;
    private String urlPath;
    private String userId;
    private String senderId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
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
        return "SocketDataDTO{" +
                "message='" + message + '\'' +
                ", userName='" + userName + '\'' +
                ", urlPath='" + urlPath + '\'' +
                ", userId='" + userId + '\'' +
                ", senderId='" + senderId + '\'' +
                '}';
    }
}
