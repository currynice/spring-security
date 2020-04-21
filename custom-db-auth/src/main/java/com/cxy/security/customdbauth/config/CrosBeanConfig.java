package com.cxy.security.customdbauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * 详情参见 {@link org.springframework.web.cors.DefaultCorsProcessor#handleInternal}
 * 根据配置处理跨域请求
 */
@Configuration
public class CrosBeanConfig {


    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new  CorsConfiguration();
        corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST"));
        //scheme需要哦
        corsConfiguration.setAllowedOrigins(Arrays.asList("https://www.baidu.com"));
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        corsConfiguration.setMaxAge();
        //配置对所有url生效
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }
}
