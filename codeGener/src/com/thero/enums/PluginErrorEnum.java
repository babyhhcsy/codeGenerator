package com.thero.enums;


/**
 * Created by thridHero on 2017/1/1.
 */
public enum  PluginErrorEnum {

    FAIL_LOAD_YOUDAO(-1,"有道词典加载错误！"),

    FAIL_NOT_SUPPORT_LANGULAGE(-2,"不支持编码格式"),

    FAIL_URISYNTAXEXCEPTION(-4,"uri解析错误"),

    FAIL_IOEXCEPTION(-3,"IO异常");

    private final int state;//状态码

    private final String msg;//状态说明

    private PluginErrorEnum(int state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public int getState(){ return state;};

    public String getMsg(){ return msg;};
}
