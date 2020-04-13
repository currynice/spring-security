package com.cxy.security.customdbauth.authProvider.customAuthenticationDetailsSource;

import cn.hutool.core.util.StrUtil;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;


public class MyWebAuthenticationDetails extends WebAuthenticationDetails  implements Serializable  {

    //图像验证码是否pass
    private boolean imageCodePass;

    //图像验证码
    private String imageCode;

    private String savedCode;

    public String getSavedCode() {
        return savedCode;
    }

    public boolean isImageCodePass() {
        return imageCodePass;
    }

    public String getImageCode() {
        return imageCode;
    }

    /**
     */
    public MyWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.imageCode = request.getParameter("captcha");
        this.savedCode =(String) request.getSession().getAttribute("captcha");
        if(!StrUtil.isBlank(savedCode)){
            //清除旧的
            request.getSession().removeAttribute("captcha");
            if(StrUtil.isBlank(imageCode)||!savedCode.equals(imageCode)){
                imageCodePass = true;
            }
        }
    }
}
