package com.webperside.commonservice.exception;

import com.webperside.commonservice.enums.ErrorEnum;

public class ExceptionUtil {

    public static RuntimeException throwFor(ErrorEnum errorEnum){
        throw new RestException(errorEnum);
    }
}
