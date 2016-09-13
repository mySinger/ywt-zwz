package com.gkzxhn.ywt_gkzx.utils;

/**
 * Created by ZengWenZhi on 2016/8/29 0029.
 * 电子商务模块的商品类
 */

public class Goods {
    public String name;
    public String introduce;
    public String price;
    public int image;
    public int num;
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Goods(){}

    public Goods(String name, String introduce, String price, int image, int num) {
        this.name = name;
        this.introduce = introduce;
        this.price = price;
        this.image = image;
        this.num = num;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + ", image=" + image + ", introduce=" + introduce + ",num=" + num +'}';
    }
}
