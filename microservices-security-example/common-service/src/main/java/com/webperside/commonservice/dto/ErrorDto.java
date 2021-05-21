package com.webperside.commonservice.dto;

import java.util.Date;

public class ErrorDto {

    private Integer status;
    private Integer errorCode;
    private String errorMessage;
    private Date timestamp;

    public ErrorDto() {
    }

    public ErrorDto(Integer status, Integer errorCode, String errorMessage, Date timestamp) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
