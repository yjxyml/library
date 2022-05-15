package com.cs.wxjsxy.model;

public class Student {
    private String name;
    private int age;
    private int id;

    public Student() {
    }

    public Student(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @ValueScan("wl")
    public String getName() {
        return name;
    }

    @ValueScan("jb")
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
}
