package com.webperside.someproject.model.response;

import com.webperside.someproject.model.enums.UserStatus;

import java.util.List;

public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private UserStatus userStatus;

    private List<AccountResponse> accounts;

    public UserResponse() {
    }

    public UserResponse(Long id, String firstName, String lastName, UserStatus userStatus, List<AccountResponse> accounts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userStatus = userStatus;
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public List<AccountResponse> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountResponse> accounts) {
        this.accounts = accounts;
    }
}
