
package com.thero.plugin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thero.util.HttpRequest;

public class YouDaoTrans {
    public YouDaoTrans() {
    }

    public static String trans(String tranStr) {
        String url = "http://fanyi.youdao.com/openapi.do";
        String param = "keyfrom=intellijTrans&key=961536271&type=data&doctype=json&version=1.1&q=" + tranStr;
        String result = HttpRequest.sendGet(url, param);
        JSONObject transResult = JSON.parseObject(result);
        JSONArray transArr = (JSONArray)transResult.get("translation");
        String tran = "";
        if(transArr.size() >= 1) {
            tran = (String)transArr.get(0);
        }

        return tran;
    }
}
