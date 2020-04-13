package com.cxy.security.customdbauth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class AuthHandlerConfig {


    @Bean("authSuccessHandler")
    //登录鉴权通过逻辑
    public AuthenticationSuccessHandler authSuccessHandler(){
        /**
         * auth:携带当前登录用户名及其角色等信息
         */
        return (request,response,auth)->{
            //登录信息，不包含密码
            Object principal = auth.getPrincipal();
            response.setContentType ("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            response.setStatus(200);
            Map<String, Object> map= new HashMap<>() ;
            map.put ("status", 200);
            map.put ("principal", principal);
            ObjectMapper om = new ObjectMapper();
            //简单起见:map->json对象
            out.write(om.writeValueAsString(map));
            out.flush();
            out.close();
        };

    }


    //登录鉴权失败逻辑
    @Bean("authFailureHandler")
    public AuthenticationFailureHandler authFailureHandler(){
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                //登录信息，不包含密码
                response.setContentType ("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                response.setStatus(401);//认证失败
                Map<String, Object> map= new HashMap<>() ;
                map.put ("status", 401);
                map.put("msg","登录失败"+e.getMessage());
                ObjectMapper om = new ObjectMapper();
                //map->json对象
                out.write(om.writeValueAsString(map));
                out.flush();
                out.close();
                e.printStackTrace();
            }
        };
    }

}
