package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
class WelcomeControllerTests {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void test () {
        // when
        String profile = this.restTemplate.getForObject("/profile", String.class);
        
        // then
        assertThat(profile).isEqualTo("local");
    }
}
