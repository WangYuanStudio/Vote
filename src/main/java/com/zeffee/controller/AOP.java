package com.zeffee.controller;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zeffee on 2017/6/19.
 */
@Component
@Aspect
public class AOP {
    @Autowired
    private HttpSession session;   // TODO:: UNSafe  // TODO:: UNSafe // TODO:: UNSafe

    @Pointcut("execution(* com.zeffee.controller.ThemeController.*(..)) || execution(* com.zeffee.controller.VoteController.*(..)) || execution(* com.zeffee.controller.UserController.*(..))")
    public void loginCut() {
    }

    @Before("loginCut()")
    public void checkUserStatus() {
        if (session.getAttribute("openid") == null) {
            //throw new InvalidStatusException("Please login again!");
            session.setAttribute("openid", "zeffee");
        }
    }

    @After("loginCut()")
    public void returnCorsReader(){
        HttpServletResponse response=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        response.addHeader("Access-Control-Allow-Origin","http://local.zeffee.com");
        response.addHeader("Access-Control-Allow-Methods","POST,GET,PUT,DELETE");
        response.addHeader("Access-Control-Allow-Headers","content-type,accept");
    }
}
