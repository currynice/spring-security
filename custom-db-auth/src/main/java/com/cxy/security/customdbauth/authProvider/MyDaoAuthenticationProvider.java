package com.cxy.security.customdbauth.authProvider;

import com.cxy.security.customdbauth.authProvider.customAuthenticationDetailsSource.MyWebAuthenticationDetails;
import com.cxy.security.customdbauth.exception.VerificationCodeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 希望在常规的认证之上增加了图形验证码的校验，其他流程并没有变化，继承DaoAuthenticationProvider
 * 谁将调用本Provider?
 *  一次完整的认证可以包含多个AuthenticationProvider，AuthenticationProvider都是由ProviderManager管理的，而
 * ProviderManager 是由 UsernamePasswordAuthenticationFilter 调用的。也就是说，所有的
 * AuthenticationProvider包含的Authentication都来源于UsernamePasswordAuthenticationFilter。
 * UsernamePasswordAuthenticationFilter(line 137) setDetails设置用户详细信息方法，其使用的AuthenticationDetailsSource接口，在该类中的默认实现是一个标准的Web认证
 * 源WebAuthenticationDetailsSource，携带的是用户的sessionId和IP地址，我们可以学习，携带别的信息
 */
@Component
public class MyDaoAuthenticationProvider extends DaoAuthenticationProvider {

    public MyDaoAuthenticationProvider(@Qualifier("customUserDteailService") UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
         this.setUserDetailsService(userDetailsService);
        this.setPasswordEncoder(passwordEncoder);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
            //根据authentication 	Object getDetails();
        // 传递 用户提交的验证码和session存储的验证码
        MyWebAuthenticationDetails details =(MyWebAuthenticationDetails) authentication.getDetails();
            if(!details.isImageCodePass()){
              throw new VerificationCodeException();
            }
            super.additionalAuthenticationChecks(userDetails,authentication);
    }




}
