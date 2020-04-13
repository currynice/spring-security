//package com.cxy.security.customdbauth.authProvider;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
////参考 DaoAuthenticationProvider 就可以实现一个自己的了
//@Component
//public class MyAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

///**
// * 添加额外逻辑
// */
//    @Override
//    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//
//    }
//
// /**
//  * 检索用户
//  */
//    @Override
//    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//        return null;
//    }
//}
