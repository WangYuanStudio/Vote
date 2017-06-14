package com.zeffee.lib;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Map;
import java.util.Observable;

/**
 * Created by zeffee on 2017/6/3.
 */
public class Common {
    public static Map<String, Object>  getResponseMap(int status) {
        return getResponseBody(status, "", "");
    }

    public static Map<String, Object>  getResponseMap(int status, Object unknowedData) {
        if (status == 200) {
            return getResponseBody(status, unknowedData, "");
        }

        return getResponseBody(status, "", unknowedData.toString());
    }


    private static Map<String, Object> getResponseBody(int status, Object data, String errorMsg) {
        return new ResponseMap()
                .put("status", status)
                .put("data", data)
                .put("errorMsg", errorMsg)
                .getDataMap();
    }

    public static String toJson(Object data){
        return JSONObject.fromObject(data).toString();
    }
}
