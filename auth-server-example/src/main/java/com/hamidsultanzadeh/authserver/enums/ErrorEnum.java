package com.hamidsultanzadeh.authserver.enums;

public enum ErrorEnum {
    USERNAME_NOT_FOUND_EXCEPTION("Username not found", 1),
    BAD_CREDENTIALS_EXCEPTION("Username or password not valid",2);


    private final String message;
    private final Integer code;

    ErrorEnum(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String toString() {
        return "ErrorEnum{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
