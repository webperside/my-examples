package com.hamidsultanzadeh.authserver.exception;

import com.hamidsultanzadeh.authserver.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestException extends RuntimeException {

    private ErrorEnum error;
    private String developerMessage;

    public RestException(ErrorEnum error) {
        this.error = error;
    }
}
