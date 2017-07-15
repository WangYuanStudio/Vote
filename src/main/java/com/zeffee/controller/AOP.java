package com.zeffee.controller;

import com.zeffee.exception.InvalidStatusException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Created by zeffee on 2017/6/19.
 */
@Component
@Aspect
public class AOP {
    @Autowired
    private HttpSession session;

    @Pointcut("execution(* com.zeffee.controller.ThemeController.*(..)) || execution(* com.zeffee.controller.VoteController.*(..)) ")
    public void loginCut() {
    }

    @Before("loginCut()")
    public void checkUserStatus() {
        if (session.getAttribute("openid") == null) {
            throw new InvalidStatusException("Please login again!");
        }
    }
}
