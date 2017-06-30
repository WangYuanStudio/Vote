package com.zeffee.lib;

import net.sf.json.JSONObject;

/**
 * Created by zeffee on 2017/6/18.
 */
public class Wechat {
    public static String getOpenidByCode(String code) {
        String reponse = HttpRequest.get("http://wechat-test.wangyuan.info/api/getOauthAccessToken/" + code);
        JSONObject responseJsonObject = JSONObject.fromObject(reponse);
        if (200 != (int) responseJsonObject.get("status")) {
            return responseJsonObject.get("errmsg").toString();
        }

        return responseJsonObject.getJSONObject("data").get("openid").toString();
    }

}
