package com.bigdatajcg2.entities;

public class Data {

    private Integer id;
    private Student student;
    private Site site;
    private Time time;
    private Integer num;

    @Override
    public String toString() {
        return ""+student + site + time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Data(Integer id, Student student, Site site, Time time, Integer num) {
        this.id = id;
        this.student = student;
        this.site = site;
        this.time = time;
        this.num = num;
    }

    public Data() {
    }
}
