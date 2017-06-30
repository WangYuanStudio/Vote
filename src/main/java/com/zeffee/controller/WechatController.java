package com.zeffee.controller;

import com.zeffee.lib.Wechat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zeffee on 2017/6/19.
 */
@Controller
public class WechatController {
    @RequestMapping(value = "/verify_vote.html", method = RequestMethod.GET)
    public void verify(@RequestParam(value = "code") String code, HttpServletResponse response, HttpSession session) throws IOException {
        String openid = Wechat.getOpenidByCode(code);

        if("code error".equals(openid)){
            response.sendRedirect("/404.html");
            return;
        }

        session.setAttribute("openid", openid);
        response.sendRedirect("/getOpenid");
    }
}
