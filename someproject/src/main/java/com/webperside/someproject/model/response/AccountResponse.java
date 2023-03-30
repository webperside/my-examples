package com.webperside.someproject.model.response;

import java.math.BigDecimal;

public class AccountResponse {

    private Long id;
    private String accountNumber;
    private BigDecimal amount;
    private Long userId;

    public AccountResponse() {
    }

    public AccountResponse(Long id, String accountNumber, BigDecimal amount, Long userId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
