package com.zeffee.lib;

import net.sf.json.JSONObject;

import java.util.Map;

/**
 * Created by zeffee on 2017/6/3.
 */
public class Common {
    public static Map<String, Object> getResponseMap(int status) {
        return ResponseMap.getResponseBody(status, "", "");
    }

    public static Map<String, Object> getResponseMap(int status, Object unknowedData) {
        if (status == 200) {
            return ResponseMap.getResponseBody(status, unknowedData, "");
        }

        return ResponseMap.getResponseBody(status, "", unknowedData.toString());
    }

    public static String toJson(Object object) {
        return JSONObject.fromObject(object).toString();
    }
}
