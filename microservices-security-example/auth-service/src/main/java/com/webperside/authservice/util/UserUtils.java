package com.webperside.authservice.util;

import com.webperside.authservice.security.JwtUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {

    public static Integer CURRENT_USER_ID(){
        return ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

}
