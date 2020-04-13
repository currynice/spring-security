package com.cxy.security.customdbauth.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import javax.sql.DataSource;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    /**
     * 根据权限管理模式配置不同的expressions
     * hasRole(role0)  当前principal  有特定role时返回 true ,defaultRolePrefix会是ROLE_,会自动加上该前缀，可以自定义设置( expressionHandler(new DefaultWebSecurityExpressionHandler(xxxx)))
     * hasAnyRole(String ...roles)   当前principal 拥有roles中任意一个即可,会自动加上该前缀ROLE_
     * hasAuthority(authority)  当前principal有特定authority时
     * hasAnyAuthority(String ...authorities)  当前principal拥有authorities中任意一个即可
     * permitAll
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //配置ANT模式URL匹配器(ANT: ？[匹配任意单个字符]，*[匹配0或任意数量的字符]，**[匹配0或者更多的目录])
                // 必须角色为ADMIN才可以访问
                .antMatchers("/admin/api/**").hasRole("ADMIN") // "hasRole('ROLE_" + role + "')",hasRole('ROLE_" + role + "')"
                //公开权限
                .antMatchers("/app/api/**").permitAll()
                // 必须角色为USER才可以访问
                .antMatchers("/user/api/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }







}
