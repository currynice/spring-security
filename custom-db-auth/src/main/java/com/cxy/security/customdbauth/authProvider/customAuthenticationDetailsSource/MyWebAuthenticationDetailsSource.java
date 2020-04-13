package com.cxy.security.customdbauth.authProvider.customAuthenticationDetailsSource;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyWebAuthenticationDetailsSource implements
        AuthenticationDetailsSource<HttpServletRequest, MyWebAuthenticationDetails> {

    // ~ Methods
    // ========================================================================================================

    /**
     * @param context the {@code HttpServletRequest} object.
     * @return the {@code WebAuthenticationDetails} containing information about the
     * current request
     */
    @Override
    public MyWebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new MyWebAuthenticationDetails(context);
    }
}
