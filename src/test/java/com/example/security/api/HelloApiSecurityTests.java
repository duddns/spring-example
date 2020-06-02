package com.example.security.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.example.security.dao.UserRepository;
import com.example.security.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "unit-test")
@Transactional
public class HelloApiSecurityTests {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    @Test
    public void test_로그인후_api_호출_성공() throws Exception {
        // given
        userRepository.save(User.builder()
                .username("user1")
                .password(passwordEncoder.encode("pass1"))
                .build());
        
        // when
        final ObjectMapper objectMapper = new ObjectMapper();
        
        // then
        AuthApi.AuthRequestDto requestDto = AuthApi.AuthRequestDto.builder()
                .username("user1")
                .password("pass1")
                .build();
        
        MvcResult authnResult = mockMvc.perform(post("/api/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andReturn()
                ;
        
        AuthApi.AuthResponseDto responseDto = objectMapper.readValue(authnResult.getResponse().getContentAsString(), AuthApi.AuthResponseDto.class);
        String token = responseDto.getToken();
        
        mockMvc.perform(get("/api/hello")
                .header("Authorization", "Bearer " + token))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value("hello world"))
        ;
    }
    
    @Test
    public void test_로그인_안하고_api_호출_실패() throws Exception {
        // given
        userRepository.save(User.builder()
                .username("user1")
                .password(passwordEncoder.encode("pass1"))
                .build());
        
        // when
        
        // then
        mockMvc.perform(get("/api/hello"))
        .andDo(print())
        .andExpect(status().isForbidden())
        ;
    }
}
