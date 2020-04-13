package com.cxy.security.customdbauth.controller;

import cn.hutool.captcha.LineCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//todo获得地址
@Controller
public class CaptchaController {
    @Autowired
    @Qualifier("mylineCaptcha")
    private LineCaptcha mylineCaptcha;


    @GetMapping("/captcha.jpg")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");
       mylineCaptcha.createCode();
        request.getSession().setAttribute("captcha",mylineCaptcha.getCode());
       try(ServletOutputStream servletOutputStream = response.getOutputStream()){
           mylineCaptcha.write(servletOutputStream);
       }catch (IOException e){
           e.printStackTrace();
       }

    }

    private void e()throws IOException{
        throw new IOException("测试");
    }


}
