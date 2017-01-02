package com.thero.plugin.youdao.enums;

/**
 * Created by thridHero on 2017/1/1.
 */
public enum  YouDaoEnum {
/*　0 - 正常
　20 - 要翻译的文本过长
　30 - 无法进行有效的翻译
　40 - 不支持的语言类型
　50 - 无效的key
　60 - 无词典结果，仅在获取词典结果生效*/


    SUCCESS(0,"加载成功"),
    QUERY_STRING_TOO_LONG(20,"要翻译的文本过长"),
    CAN_NOT_TRANSLATE(30,"无法进行有效的翻译"),
    INVALID_LANGUAGE(40,"不支持的语言类型"),
    INVALID_KEY(50,"无效的key"),
    NO_RESULT(60,"无词典结果，仅在获取词典结果生效"),
    WHATUP(-1,"结果不明");

    private final int state;//状态码

    private final String msg;//状态说明

    private YouDaoEnum(int state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public int getState(){ return state;};

    public String getMsg(){ return msg;};
}
