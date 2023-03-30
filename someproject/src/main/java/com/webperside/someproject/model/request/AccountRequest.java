package com.webperside.someproject.model.request;

import com.webperside.someproject.model.mybatis.Account;

import java.math.BigDecimal;

public class AccountRequest {

    private String accountNumber;
    private BigDecimal amount;

    public Account toAccount() {
        Account acc = new Account();
        acc.setAccountNumber(accountNumber);
        acc.setAmount(amount);
        return acc;
    }

    public AccountRequest() {
    }

    public AccountRequest(String accountNumber, BigDecimal amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
