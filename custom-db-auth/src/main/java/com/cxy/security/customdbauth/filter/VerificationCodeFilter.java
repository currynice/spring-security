//package com.cxy.security.customdbauth.filter;
//
//import cn.hutool.core.util.StrUtil;
//import com.cxy.security.customdbauth.exception.VerificationCodeException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Servlet 方式实现验证码
// */
//public class VerificationCodeFilter extends OncePerRequestFilter {
//
//    //登录时表单提交的地址或者处理登录请求的url
//    private static final String LOGIN_REQUEST_URL = "myLogin";
//
//
//
//    //验证码未通过失败逻辑
//    private AuthenticationFailureHandler codeFailureHandler(){
//        return new AuthenticationFailureHandler() {
//            @Override
//            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
//                //登录信息，不包含密码
//                response.setContentType ("application/json;charset=utf-8");
//                PrintWriter out = response.getWriter();
//                response.setStatus(401);//认证失败
//                Map<String, Object> map= new HashMap<>() ;
//                map.put ("status", 401);
//                map.put("msg",e.getMessage());
//                ObjectMapper om = new ObjectMapper();
//                //map->json对象
//                out.write(om.writeValueAsString(map));
//                out.flush();
//                out.close();
//                e.printStackTrace();
//            }
//        };
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        //只对登录请求进行过滤
//        if(!LOGIN_REQUEST_URL.equals(request.getRequestURI())){
//            filterChain.doFilter(request,response);
//        }else {
//            try {
//                verify(request);
//                filterChain.doFilter(request,response);
//            }catch (VerificationCodeException e){
//                codeFailureHandler().onAuthenticationFailure(request,response,e);
//            }
//        }
//    }
//
//
//    //从session中取
//    private void verify(HttpServletRequest request){
//        String codeFromUser = request.getParameter("captcha");
//
//        String savedCode = (String) request.getSession().getAttribute("captcha");
//
//        if(!StrUtil.isBlank(savedCode)){
//            //清除旧的
//            request.getSession().removeAttribute("captcha");
//        }
//        if(StrUtil.isBlank(codeFromUser)||!savedCode.equals(codeFromUser)){
//            throw new VerificationCodeException();
//        }
//    }
//}
