package com.zeffee.lib;

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

    public ResponseMap put(String key,Object value) {
        dataMap.put(key,value);
        return this;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }
}
