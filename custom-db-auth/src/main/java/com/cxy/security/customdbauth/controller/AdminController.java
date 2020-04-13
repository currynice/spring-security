package com.cxy.security.customdbauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cxy
 * @Date: 2020/4/4 21:15
 * @Description: 系统管理相关api, 管理员角色才能操作
 */
@RestController
@RequestMapping("/admin/api")
public class AdminController {

    @GetMapping("hello")
    public String hello() {
        return "hello,admin";
    }

}
