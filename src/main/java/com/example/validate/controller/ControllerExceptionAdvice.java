package com.example.validate.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = ControllerExceptionAdvice.class)
public class ControllerExceptionAdvice {
    
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 400);
        map.put("message", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        
        return ResponseEntity.badRequest()
                .body(map);
    }
}
