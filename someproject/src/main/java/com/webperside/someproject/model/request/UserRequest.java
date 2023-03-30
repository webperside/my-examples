package com.webperside.someproject.model.request;

import com.webperside.someproject.model.enums.UserStatus;
import com.webperside.someproject.model.mybatis.User;

import java.util.List;

public class UserRequest {

    private String firstName;
    private String lastName;
    private List<AccountRequest> accounts;

    public User toUser() {
        User u = new User();
        u.setUserStatus(UserStatus.ACCOUNT_WAITING_FOR_APPROVAL);
        u.setFirstName(firstName);
        u.setLastName(lastName);

        return u;
    }

    public UserRequest() {
    }

    public UserRequest(String firstName, String lastName, List<AccountRequest> accounts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = accounts;
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

    public List<AccountRequest> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountRequest> accounts) {
        this.accounts = accounts;
    }
}
