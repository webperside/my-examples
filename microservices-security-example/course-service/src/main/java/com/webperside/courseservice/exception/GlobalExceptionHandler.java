package com.webperside.courseservice.exception;

import com.webperside.commonservice.dto.ErrorDto;
import com.webperside.commonservice.exception.RestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        ObjectError objectError = ex.getBindingResult().getAllErrors().stream().findAny()
                .orElse(null);

        FieldError fieldError = (FieldError) objectError;

        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDto.setErrorCode(100);
        errorDto.setErrorMessage(fieldError.getDefaultMessage());
        errorDto.setTimestamp(new Date());

        return ResponseEntity.badRequest().body(errorDto);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException ex) {
        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().stream().findAny().orElse(null);

        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDto.setErrorCode(100);
        errorDto.setTimestamp(new Date());

        if(constraintViolation != null){
            errorDto.setErrorMessage(constraintViolation.getMessage());
        }

        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorDto> handleRestException(RestException ex){

        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(ex.getStatus().value());
        errorDto.setErrorCode(ex.getErrorCode());
        errorDto.setErrorMessage(ex.getErrorMessage());
        errorDto.setTimestamp(new Date());

        return ResponseEntity.badRequest().body(errorDto);
    }

}
