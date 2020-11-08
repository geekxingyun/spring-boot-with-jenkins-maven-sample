package com.xingyun.springbootwithjenkinsmavensample.api;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qing-feng.zhao
 */
@RestController
public class HomeEndPoint {

    private final Environment environment;
    public HomeEndPoint(Environment environment) {
        this.environment = environment;
    }

    @GetMapping(value = "/")
    public String Home(){
        return "hello world,I am "+this.environment.getProperty("spring.application.name");
    }
}
