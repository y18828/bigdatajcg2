package com.bigdatajcg2.entities;

public class Result {

    private String max_web;
    private String max_man;
    private String max_woman;

    public Result(String max_web, String max_man, String max_woman) {
        this.max_web = max_web;
        this.max_man = max_man;
        this.max_woman = max_woman;
    }

    public Result() {
    }

    public String getMax_web() {
        return max_web;
    }

    public void setMax_web(String max_web) {
        this.max_web = max_web;
    }

    public String getMax_man() {
        return max_man;
    }

    public void setMax_man(String max_man) {
        this.max_man = max_man;
    }

    public String getMax_woman() {
        return max_woman;
    }

    public void setMax_woman(String max_woman) {
        this.max_woman = max_woman;
    }
}
