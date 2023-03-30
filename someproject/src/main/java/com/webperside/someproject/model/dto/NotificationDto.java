package com.webperside.someproject.model.dto;

public class NotificationDto {

    private Long receiverId;
    private String title;
    private String content;

    public NotificationDto(Long receiverId, String title, String content) {
        this.receiverId = receiverId;
        this.title = title;
        this.content = content;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
