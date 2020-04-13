package com.cxy.security.customdbauth.exception;

import org.springframework.security.core.AuthenticationException;

public class VerificationCodeException extends AuthenticationException {

    public VerificationCodeException() {
        super("验证码校验失败");
    }
}
