package com.example.validate.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "unit-test")
@Transactional
public class ValidControllerTests {

    @Autowired
    private WebApplicationContext ctx;
    
    private MockMvc mockMvc;
    
    
    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }
    
    
    @Test
    public void test_DTO_검증_실패() throws Exception {
        // given
        
        // when
        JSONObject jsonObject = new JSONObject();
        String content = jsonObject.toString();
        
        // then
        mockMvc.perform(post("/api/valid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.ok").value(false))
        .andExpect(jsonPath("$.status").value(400))
        .andExpect(jsonPath("$.error.code").value(-1))
        .andExpect(jsonPath("$.error.message").value("잘못된 인자입니다."))
        .andExpect(jsonPath("$.validations.length()").value(2))
        .andExpect(jsonPath("$.validations[*].field").value(containsInAnyOrder("email", "message")))
        ;
    }
    
    @Test
    public void test_DTO_검증_성공() throws Exception {
        // given
        
        // when
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "test@test.com");
        jsonObject.put("message", "test message");
        String content = jsonObject.toString();
        
        // then
        mockMvc.perform(post("/api/valid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
        .andDo(print())
        .andExpect(status().isOk())
        ;
    }
}
