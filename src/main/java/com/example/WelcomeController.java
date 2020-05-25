package com.example;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private Environment env;
    
    
    public WelcomeController(Environment env) {
        this.env = env;
    }
    
    
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }
    
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfile () {
        String profile = Arrays.stream(env.getActiveProfiles())
                .findFirst()
                .orElse("");
        
        logger.debug(profile);
        
        return profile;
    }
}
