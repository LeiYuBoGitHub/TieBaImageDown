package com.lyb.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @Auther: 野性的呼唤
 * @Date: 2019/7/18 17:01
 * @Description:
 */
public class JsonUtil {

    public static String getValue(String json, String keyName){
        String[] key = keyName.split(",");
        String s = key[(key.length)-1];
        JSONObject oba = JSONObject.parseObject(json);
        for (int i = 0;i<key.length-1;i++){
            oba = JSONObject.parseObject(oba.getString(key[i]));
        }
        return oba.getString(s);
    }
}
