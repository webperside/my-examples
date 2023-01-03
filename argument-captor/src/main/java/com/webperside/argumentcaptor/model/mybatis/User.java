package com.webperside.argumentcaptor.model.mybatis;

import com.webperside.argumentcaptor.model.enums.UserStatus;

public class User {

    private long id;
    private String email;
    private String receiverId;
    private UserStatus userStatus;

    public User() {
    }

    public User(long id, String email, String receiverId, UserStatus userStatus) {
        this.id = id;
        this.email = email;
        this.receiverId = receiverId;
        this.userStatus = userStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
