package com.example.security.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@RestController
public class HelloApi {
    
    @RequestMapping(value = "/api/hello", method = RequestMethod.GET)
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok(HelloResponseDto.builder()
                .message("hello world")
                .build());
    }
    
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class HelloResponseDto {
        
        private String message;
        
        
        @Builder
        public HelloResponseDto(String message) {
            this.message = message;
        }
    }
}
