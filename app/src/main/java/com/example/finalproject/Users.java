package com.example.finalproject;

public class Users {

    private String name ;

    private String titelJop ;

    private int age ;
    private int id ;
    private String descr ;

    public Users(String name ,String titelJop, String descr) {
        this.name = name;

        this.descr = descr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
