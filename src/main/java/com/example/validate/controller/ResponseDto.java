package com.example.validate.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ResponseDto<T> {
    
    private boolean ok;
    
    private Integer status;
    
    private T data;
    
    private Error error;
    
    private List<Validation> validations;
    
    
    private ResponseDto(boolean ok, Integer status, @Nullable T data,
            @Nullable Error error, @Nullable List<Validation> validations) {
        super();
        this.ok = ok;
        this.status = status;
        this.data = data;
        this.error = error;
        this.validations = validations;
    }
    
    
    public static <T> ResponseDto<T> ok(T data) {
        return new ResponseDto<>(true, HttpStatus.OK.value(), data, null, null);
    }
    
    public static <T> ResponseDto<T> badRequest(String message) {
        return badRequest(message, null);
    }
    public static <T> ResponseDto<T> badRequest(String message, List<Validation> validations) {
        Error error = ResponseDto.Error.builder()
                .code(-1)
                .message(message)
                .build();
        
        return new ResponseDto<>(false, HttpStatus.BAD_REQUEST.value(), null, error, validations);
    }
    
    
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    static class Error {
        
        private int code;
        
        private String message;
        
        
        @Builder
        public Error(int code, String message) {
            super();
            this.code = code;
            this.message = message;
        }
    }
    
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    static class Validation {
        
        private String field;
        
        private String message;
        
        
        @Builder
        public Validation(String field, String message) {
            super();
            this.field = field;
            this.message = message;
        }
    }
}

