package com.thero.plugin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thero.Exception.PluginException;
import com.thero.enums.PluginErrorEnum;
import com.thero.util.HttpRequest;

import java.io.UnsupportedEncodingException;

/**
 * Created by thridHero on 2016/12/29.
 */
public class YouDaoTrans {
    public static String trans(String tranStr) throws UnsupportedEncodingException {
        String url = "http://fanyi.youdao.com/openapi.do";

        String param = "keyfrom=intellijTrans&key=961536271&type=data&doctype=json&version=1.1&q="+new String(tranStr.getBytes(),"utf-8");
        String result = HttpRequest.sendGet(url,param);
        JSONObject transResult = JSON.parseObject(result);
        if(transResult.getInteger(("errorCode"))!=0){
            throw new PluginException(PluginErrorEnum.FAIL_LOAD_YOUDAO);
        }

        JSONArray transArr = (JSONArray) transResult.get("translation");
        String tran = "";
        if(transArr!=null && transArr.size()>=1){
            tran = (String) transArr.get(0);
        }
        return tran;
    }
}
