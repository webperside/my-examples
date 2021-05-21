package com.webperside.commonservice.enums;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum ErrorEnum {

    DEFAULT_EXCEPTION(100, "Default Server error", INTERNAL_SERVER_ERROR),
    USERNAME_NOT_FOUND_EXCEPTION(101, "Username not found", BAD_REQUEST),
    PASSWORD_DOES_NOT_MATCH_EXCEPTION(102, "Password does not match", BAD_REQUEST),
    ACCESS_TOKEN_IS_EXPIRED_EXCEPTION(103,"Access token is expired", FORBIDDEN),
    REFRESH_TOKEN_IS_EXPIRED_EXCEPTION(104, "Refresh token is expired", NOT_FOUND),
    USER_NOT_FOUND_EXCEPTION(105, "User not found exception", NOT_FOUND),
    COUPON_NOT_FOUND_EXCEPTION(106, "Coupon not found exception", NOT_FOUND),
    COUPON_ALREADY_USED_EXCEPTION(107, "Coupon already used exception", BAD_REQUEST),
    COURSE_NOT_FOUND_EXCEPTION(108, "Course not found exception", NOT_FOUND);

    private final Integer errorCode;
    private final String errorMessage;
    private final HttpStatus status;

    ErrorEnum(Integer errorCode, String errorMessage, HttpStatus status) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
