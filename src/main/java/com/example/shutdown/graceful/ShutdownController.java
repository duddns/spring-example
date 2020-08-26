package com.example.shutdown.graceful;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShutdownController {
    
    @RequestMapping(value = "/grace", method = RequestMethod.GET)
    public String welcgraceome() throws InterruptedException {
        Thread.sleep(5000);
        
        return "welcome";
    }
}
