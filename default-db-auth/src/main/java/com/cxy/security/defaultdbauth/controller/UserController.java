package com.cxy.security.defaultdbauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cxy
 * @Date: 2020/4/4 21:15
 * @Description: 用户操作自身信息相关api, 必须是登陆用户才可以操作
 */

@RestController
@RequestMapping("/user/api")
public class UserController {

    @GetMapping("hello")
    public String hello() {
        return "hello,user";
    }
}
