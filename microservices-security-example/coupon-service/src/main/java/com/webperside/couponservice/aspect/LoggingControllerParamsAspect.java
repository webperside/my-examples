package com.webperside.couponservice.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log4j2
public class LoggingControllerParamsAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restControllerClassMethods() { }

    @Before(value = "restControllerClassMethods()")
    public void before(JoinPoint joinPoint){
        final String TEMPLATE = "Class : [{}], Method : [{}], Params : [{}]";
        Signature signature = joinPoint.getSignature();
        final String className = signature.getDeclaringType().getSimpleName();
        final String methodName = signature.getName();
        final String params = Arrays.toString(joinPoint.getArgs());
        log.info(TEMPLATE,className, methodName, params);
    }
}
