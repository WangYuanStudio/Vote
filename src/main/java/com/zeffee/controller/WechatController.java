package com.zeffee.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.zeffee.dao.UserDAO;
import com.zeffee.entity.User;
import com.zeffee.lib.Wechat;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zeffee on 2017/6/19.
 */
@Controller
@Transactional
@Api(value = "wechat", hidden = true)
public class WechatController {
    @Autowired
    private UserDAO userDAO;

    @ApiOperation(value = "", hidden = true)
    @RequestMapping(value = "/verify_{code}_vote.html", method = RequestMethod.GET)
    public void verify(@PathVariable(value = "code") String code, HttpServletResponse response, HttpSession session) throws IOException {
        JSONObject wechatInfo = Wechat.getWechatInfoByCode(code);

        if (wechatInfo == null) {
            response.sendRedirect("/404.html");
            return;
        }

        User user = new User(wechatInfo.get("openid").toString(), wechatInfo.get("nickname").toString(), wechatInfo.get("headimgurl").toString());
        userDAO.updateUserInfo(user);

        session.setAttribute("openid", wechatInfo.get("openid"));
        response.sendRedirect("/index.jsp");
    }
}
