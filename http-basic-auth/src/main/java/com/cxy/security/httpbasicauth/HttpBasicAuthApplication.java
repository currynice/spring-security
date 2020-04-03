package com.cxy.security.httpbasicauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HttpBasicAuthApplication {

    @GetMapping("hello")
    public String hello(){
        return "经过Basic认证才可以看见的内容";
    }

    public static void main(String[] args) {
        SpringApplication.run(HttpBasicAuthApplication.class, args);
    }

}
