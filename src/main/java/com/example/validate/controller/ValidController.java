package com.example.validate.controller;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@RestController
public class ValidController {
    
    @RequestMapping(value = "/api/valid", method = RequestMethod.POST)
    public ResponseEntity<?> hello(@Valid @RequestBody HelloRequestDto requestDto) {
        return ResponseEntity.ok()
                .build();
    }
    
    
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class HelloRequestDto {
        
        @NotEmpty(message = "email should not be empty")
        @Email(message = "email should be valid")
        private String email;
        
        @NotEmpty(message = "message should not be empty")
        private String message;
    }
}
