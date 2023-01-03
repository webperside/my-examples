package com.webperside.argumentcaptor.model.dto;

public class NotificationDto {

    private String receiverId;

    public NotificationDto(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
}
