package com.webperside.commonservice.exception;

import com.webperside.commonservice.dto.ErrorDto;
import com.webperside.commonservice.enums.ErrorEnum;
import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException{

    private HttpStatus status;
    private Integer errorCode;
    private String errorMessage;

    public RestException() {
    }

    public RestException(ErrorEnum errorEnum){
        this.status = errorEnum.getStatus();
        this.errorCode = errorEnum.getErrorCode();
        this.errorMessage = errorEnum.getErrorMessage();
    }

    public RestException(HttpStatus status, Integer errorCode, String errorMessage) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
