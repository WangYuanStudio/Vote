package com.zeffee.lib;

import net.sf.json.JSONObject;

/**
 * Created by zeffee on 2017/6/18.
 */
public class Wechat {

    public static JSONObject getWechatInfoByCode(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx3b3c63ee9e929594&secret=cc65325e2c790a732995b97c67832fc1&code=" + code + "&grant_type=authorization_code";
        String responseBody = HttpRequest.get(url);
        JSONObject accessToken_Openid = JSONObject.fromObject(responseBody);
        Object accessToken = accessToken_Openid.get("access_token");
        Object openid = accessToken_Openid.get("openid");

        if (accessToken == null || openid == null) return null;

        url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken.toString() + "&openid=" + openid.toString() + "&lang=zh_CN";
        responseBody = HttpRequest.get(url);
        return JSONObject.fromObject(responseBody);
    }
}
