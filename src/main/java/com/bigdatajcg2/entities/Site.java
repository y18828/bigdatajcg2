package com.bigdatajcg2.entities;

public class Site {

    private Integer id;
    private String web;

    @Override
    public String toString() {
        return "-" +
                web + '-';
    }

    public Site(Integer id, String web) {
        this.id = id;
        this.web = web;
    }

    public Site() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
