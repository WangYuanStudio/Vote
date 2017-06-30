package com.zeffee.lib;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zeffee on 2017/6/8.
 */
public class ResponseMap {

    private Map<String, Object> dataMap;

    public ResponseMap() {
        dataMap = new HashMap<String, Object>();
    }

    ResponseMap put(String key,Object value) {
        dataMap.put(key,value);
        return this;
    }

    Map<String, Object> getDataMap() {
        return dataMap;
    }

    static Map<String, Object> getResponseBody(int status, Object data, String errorMsg) {
        return new ResponseMap()
                .put("status", status)
                .put("data", data)
                .put("errorMsg", errorMsg)
                .getDataMap();
    }
}
