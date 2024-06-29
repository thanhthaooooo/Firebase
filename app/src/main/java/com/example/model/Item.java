package com.example.model;

import java.io.Serializable;

public class Item implements Serializable {
    public Item() {
    }

    public Item(String name, String author, String img, Float price) {
        this.name = name;
        this.author = author;
        this.img = img;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    private String name,author,img;
    private Float price;

    @Override
    public String toString() {
        String msg=img+"\n"+author+"\n"+price+"(VNĐ)"+"\n"+name;
        return msg;
    }
}
