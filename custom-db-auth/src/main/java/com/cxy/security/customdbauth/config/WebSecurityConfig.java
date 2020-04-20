package com.cxy.security.customdbauth.config;



//import com.cxy.security.customdbauth.filter.VerificationCodeFilter;
import com.cxy.security.customdbauth.authProvider.customAuthenticationDetailsSource.MyWebAuthenticationDetailsSource;
import com.cxy.security.customdbauth.service.UserDetailService.CustomUserDteailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;



    @Autowired
    @Qualifier("authSuccessHandler")
    private AuthenticationSuccessHandler authSuccessHandler;

    @Autowired
    @Qualifier("authFailureHandler")
    private AuthenticationFailureHandler authFailureHandler;


    @Autowired
    private MyWebAuthenticationDetailsSource myWebAuthenticationDetailsSource;

    @Autowired
    private CustomUserDteailService customUserDteailService;




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

        JdbcTokenRepositoryImpl myJdbcTokenRepository = new JdbcTokenRepositoryImpl();
        myJdbcTokenRepository.setDataSource(dataSource);

        http.authorizeRequests()
                //配置ANT模式URL匹配器(ANT: ？[匹配任意单个字符]，*[匹配0或任意数量的字符]，**[匹配0或者更多的目录])
                // 必须角色为ADMIN才可以访问
                .antMatchers("/admin/api/**").hasRole("ADMIN") // "hasRole('ROLE_" + role + "')",hasRole('ROLE_" + role + "')"
                //公开权限
                .antMatchers("/app/api/**","/captcha.jpg").permitAll()
                // 必须角色为USER才可以访问
                .antMatchers("/user/api/**").hasRole("USER")

                .anyRequest().authenticated()
                .and()
                .formLogin()
                //配置自己的登录页
                // .loginPage("myLogin.html")
                //验证码实现方式二 start：
             //   .authenticationDetailsSource(myWebAuthenticationDetailsSource)
                //验证码实现方式二 end：

                //自定义URL method:POST http://localhost:8080/myLogin?username=user&password=f54ed9e5-880f-4d4d-a3c4-7c39f81da5e7
                .loginProcessingUrl("/myLogin")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
                //登录页不设限
                .permitAll()
                .and()
                .csrf().disable()
        //验证码实现方式一start：在密码验证过程前添加自定义验证码拦截器
//        http.addFilterBefore(new VerificationCodeFilter(), UsernamePasswordAuthenticationFilter.class);
        //验证码实现方式一end

        //自动登录实现方式一start：散列算法加密 requestParam:remember-me=on ,详情参见 AbstractRememberMeServices ,TokenBasedRememberMeServices
//             .rememberMe().userDetailsService(customUserDteailService)
//             .key("assignKey")//可以指定key,避免每次重启服务后，之前的所有自动登录cookie失效，以及多实例部署，不同实例间的统一key
        //自动登录实现方式一end

                //自动登录实现方式二 start：持久化令牌 详情参见 PersistentTokenBasedRememberMeServices ,PersistentRememberMeToken,JdbcTokenRepositoryImpl
                .rememberMe().userDetailsService(customUserDteailService)
                .tokenRepository(myJdbcTokenRepository);
        //自动登录实现方式一end

//  配置注销(LogoutFilter) start
//                http.logout()
//                     //   .logoutUrl("/xxx")//注销成功后的重定向路由
//                        //注销成功的处理方式，比重定向更加灵活
//                         .logoutSuccessHandler(new LogoutSuccessHandler() {
//                             @Override
//                             public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//
//                             }
//                         })
//                        .invalidateHttpSession(true)//让该request的HttpSession失效
//                        .deleteCookies("cookie1")//删除指定的cookie
//                        //可以再定义些额外逻辑
//                        .addLogoutHandler(new LogoutHandler() {
//                            @Override
//                            public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//
//                            }
//                        })
        //  配置注销(LogoutFilter) end


    }








}
