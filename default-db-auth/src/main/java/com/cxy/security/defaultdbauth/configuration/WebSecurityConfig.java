package com.cxy.security.defaultdbauth.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

//自己配置Security，舍弃自动配置的安全模式
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //配置ANT模式URL匹配器(ANT: ？[匹配任意单个字符]，*[匹配0或任意数量的字符]，**[匹配0或者更多的目录])
                // 必须角色为ADMIN才可以访问
                .antMatchers("/admin/api/**").hasRole("ADMIN")
                //公开权限
                .antMatchers("/app/api/**").permitAll()
                // 必须角色为USER才可以访问
                .antMatchers("/user/api/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }


    /**
     * 官方推荐的加密方式
     * @return
     */
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    /**
//     * 基于内存的多用户支持 认证信息管理
//     * spring5中摒弃了原有的密码存储格式，官方把spring security的密码存储格式改了
//     *
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //认证信息存储到内存中
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("user").password(new BCryptPasswordEncoder().encode("123")).roles("USER");
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("admin").password(new BCryptPasswordEncoder().encode("123")).roles("ADMIN");
//    }


    /**
     * 基于内存的多用户支持 认证信息管理
     * spring5中摒弃了原有的密码存储格式，官方把spring security的密码存储格式改了
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //认证信息存储到数据源的user表,重启后主键重复问题
        JdbcUserDetailsManagerConfigurer jdbcUserDetailsManagerConfigurer = auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());
        jdbcUserDetailsManagerConfigurer.withUser("user").password(new BCryptPasswordEncoder().encode("123")).roles("USER");
        jdbcUserDetailsManagerConfigurer.withUser("admin").password(new BCryptPasswordEncoder().encode("123")).roles("ADMIN");
    }



}
