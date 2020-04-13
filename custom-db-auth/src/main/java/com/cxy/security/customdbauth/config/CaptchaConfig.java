package com.cxy.security.customdbauth.config;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *  线段干扰的验证码
 */
@Component
public class CaptchaConfig {

    /**
     * LineCaptcha 线段干扰的验证码 https://www.hutool.cn/docs/#/captcha/%E6%A6%82%E8%BF%B0?id=%E8%87%AA%E5%AE%9A%E4%B9%89%E9%AA%8C%E8%AF%81%E7%A0%81
     */
    @Bean("mylineCaptcha")
    public LineCaptcha  mylineCaptcha(){
     return CaptchaUtil.createLineCaptcha(200, 100);
    }


}
