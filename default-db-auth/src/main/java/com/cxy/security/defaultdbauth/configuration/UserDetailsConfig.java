//package com.cxy.security.defaultdbauth.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
///**
// * @Author: cxy
// * @Date: 2020/4/4 22:02
// * @Description
// */
//@Configuration
//public class UserDetailsConfig {
//    /**
//     * 基于内存的多用户支持
//     * @return
//     */
//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user").passwordEncoder(new BCryptPasswordEncoder()).password("123").roles("USER").build());
//        manager.createUser(User.withUsername("admin").password("123").roles("ADMIN").build());
//
//        return manager;
//    }
//
//}
