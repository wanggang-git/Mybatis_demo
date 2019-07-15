package com.wg.demo.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FunUtil {

    public static List Long2Str(List data, String... keys) {
        if (data instanceof JSONArray) {
            JSONArray dataInfo = (JSONArray) data;
            for (int i = 0; i < dataInfo.size(); i++) {
                JSONObject item = dataInfo.getJSONObject(i);
                for (String key : keys) {
                    if (item.containsKey(key)) {
                        item.put(key, String.valueOf(item.get(key)));
                    }
                }
                dataInfo.set(i, item);
            }
        } else {
            List<Map> dataInfo = data;
            for (int i = 0; i < dataInfo.size(); i++) {
                Map item = dataInfo.get(i);
                for (String key : keys) {
                    if (item.containsKey(key)) {
                        item.put(key, String.valueOf(item.get(key)));
                    }
                }
            }
        }
        return data;
    }

    public static void generateKeyCache(Map<String, List<Map>> cache, String key, Map item) {
        if (cache.containsKey(key)) {
            cache.get(key).add(item);
        } else {
            List<Map> list = new ArrayList<>();
            list.add(item);
            cache.put(key, list);
        }
    }
}
