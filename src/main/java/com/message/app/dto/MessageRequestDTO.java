package com.message.app.dto;

public class MessageRequestDTO {

    private Integer pageStart;
    private Integer pageSize;
    private boolean isPageable;
    private String userId;
    private String senderId;

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isPageable() {
        return isPageable;
    }

    public void setPageable(boolean pageable) {
        isPageable = pageable;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "MessageRequestDTO{" +
                "pageStart=" + pageStart +
                ", pageSize=" + pageSize +
                ", isPageable=" + isPageable +
                ", userId='" + userId + '\'' +
                ", senderId='" + senderId + '\'' +
                '}';
    }
}
