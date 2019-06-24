package com.bigdatajcg2.dao;

public class Result {


    private String web_max;
    private String web_man;
    private String web_woman;

    public Result(String web_max, String web_man, String web_woman) {
        this.web_max = web_max;
        this.web_man = web_man;
        this.web_woman = web_woman;
    }

    public Result() {
    }

    public String getWeb_max() {
        return web_max;
    }

    public void setWeb_max(String web_max) {
        this.web_max = web_max;
    }

    public String getWeb_man() {
        return web_man;
    }

    public void setWeb_man(String web_man) {
        this.web_man = web_man;
    }

    public String getWeb_woman() {
        return web_woman;
    }

    public void setWeb_woman(String web_woman) {
        this.web_woman = web_woman;
    }
}
