package com.thero.bean;

/**
 * Created by thridHero on 2017/1/1.
 */
public class ResultBean {
    private int state;
    private String msg;
    private String data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ResultBean() {
    }

    public ResultBean(int state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public ResultBean(int state, String msg, String data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }
}
