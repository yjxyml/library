package com.ml.wxjsxy.model;

public class Book {

    private int id;                 //编号
    private String name;            //名称
    private String desc;            //简介
    private String author;          //作者
    private double price;           //价格
    private int stock;              //数量
    private int updateId;

    public Book() {
    }

    public Book(String name, String desc, String author, double price, int stock) {
        this.name = name;
        this.desc = desc;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }

    public Book(int id, String name, String desc, String author, double price, int stock) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }

    public Book(int id, String name, String desc, String author, double price, int stock, int updateId) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.author = author;
        this.price = price;
        this.stock = stock;
        this.updateId = updateId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }
}
