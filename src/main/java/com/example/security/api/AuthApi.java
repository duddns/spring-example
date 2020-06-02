package com.example.security.api;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.security.dao.UserRepository;
import com.example.security.model.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@RestController
public class AuthApi {
    
    public static final String SECRET = "JWT-SECRET-KEY";
    public static final long EXPIRATION_TIME = 3600000; // 1 hours
    
    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    
    @Autowired
    public AuthApi(UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    
    @RequestMapping(value = "/api/auth", method = RequestMethod.POST)
    public ResponseEntity<?> hello(@RequestBody AuthRequestDto requestDto) {
        Optional<User> optional = userRepository.findByUsername(requestDto.getUsername());
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException(requestDto.getUsername());
        }
        User user = optional.get();
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new UsernameNotFoundException(requestDto.getUsername());
        }
        
        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
        
        return ResponseEntity.ok(AuthResponseDto.builder()
                .token(token)
                .build());
    }
    
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class AuthRequestDto {
        
        private String username;
        
        private String password;
        
        
        @Builder(access = AccessLevel.PACKAGE)
        public AuthRequestDto(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
    
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class AuthResponseDto {
        
        private String token;
        
        
        @Builder
        public AuthResponseDto(String token) {
            this.token = token;
        }
    }
}

