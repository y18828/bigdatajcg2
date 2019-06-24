package com.bigdatajcg2.entities;

public class Time  {

    private Integer id;
    private String date;

    @Override
    public String toString() {
        return date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Time(Integer id, String date) {
        this.id = id;
        this.date = date;
    }

    public Time() {
    }
}
