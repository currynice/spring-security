package com.cxy.security.formauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FormAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormAuthApplication.class, args);
    }


    @GetMapping("hello")
    public String hello(){
        return "hi";
    }
}
