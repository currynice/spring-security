package com.cxy.security.customdbauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cxy
 * @Date: 2020/4/4 21:15
 * @Description: 面向客户端相关api，用户角色
 */
@RestController
@RequestMapping("/app/api")
public class AppController {

    @GetMapping("hello")
    public String hello() {
        return "hello,app";
    }
}
