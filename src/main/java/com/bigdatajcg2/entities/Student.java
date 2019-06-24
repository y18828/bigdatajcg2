package com.bigdatajcg2.entities;

public class Student{

    private Integer id;
    private String name;
    private String sex;
    private Integer sno;



    public Student(Integer id, String name, String sex, Integer sno) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.sno = sno;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return name + '-' +
                 sex + '-' + sno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }
}
