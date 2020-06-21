package com.example.validate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = ControllerExceptionAdvice.class)
public class ControllerExceptionAdvice {
    
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<?>> handleException(MethodArgumentNotValidException ex) {
        List<ResponseDto.Validation> validations = new ArrayList<>();
        
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        for (FieldError error : errors) {
            String field = error.getField();
            String message = error.getDefaultMessage();
            
            validations.add(ResponseDto.Validation.builder()
                    .field(field)
                    .message(message)
                    .build());
        }
        
        return ResponseEntity.badRequest()
                .body(ResponseDto.badRequest("잘못된 인자입니다.", validations));
    }
}
