package com.cxy.security.customdbauth.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @Author: cxy
 * @Date: 2020/4/20 20:56
 * @Description:兼容的PasswordEncoder
 * 当密码不是BCrypt密文时，才启用自定义的匹配逻辑，其余还是 沿用原来的方案
 */
//@Component
public class CompatiblePasswordEncoder  extends BCryptPasswordEncoder {

    private static final Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        //密文不是BCRYPT加密方式，一种校验方法，以达到兼容旧密码数据的目的
        if(!BCRYPT_PATTERN.matcher(encodedPassword).matches()){
            //..
        }
        return super.matches(rawPassword, encodedPassword);
    }
}
