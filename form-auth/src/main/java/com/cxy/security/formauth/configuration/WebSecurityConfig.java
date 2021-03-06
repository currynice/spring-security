package com.cxy.security.formauth.configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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


@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //配置自己的登录页
               // .loginPage("myLogin.html")
                //自定义URL method:POST http://localhost:8080/myLogin?username=user&password=f54ed9e5-880f-4d4d-a3c4-7c39f81da5e7
                .loginProcessingUrl("/myLogin")
                .successHandler(authSuccessHandler())
                .failureHandler(authFailureHandler())
                //登录页不设限
                .permitAll()
                .and()
                .csrf().disable();
    }

    //登录鉴权通过逻辑
    private AuthenticationSuccessHandler authSuccessHandler(){
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
    private AuthenticationFailureHandler authFailureHandler(){
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
