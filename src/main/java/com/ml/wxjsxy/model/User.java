package com.ml.wxjsxy.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;            //名字
    private String password;        //密码
    private String sex;             //性别
    private String role;            //权限
    private String telephone;       //电话

    private List<Privilege> list = new ArrayList<Privilege>();  //权限集合


    public User() {
    }

    public User(String name, String password, String sex) {
        this.name = name;
        this.password = password;
        this.sex = sex;
    }

    public User(String name, String password, String sex, String telephone) {
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Privilege> getList() {
        return list;
    }

    public void setList(Privilege privilege) {
        this.list.add(privilege);
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
