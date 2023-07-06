package com.example.abhishek.mygrocerystore.Models;

public class nav_category_Models {

    private String name;
    private String description;
    private String discount;
    private String img_url;


    public nav_category_Models(){

    }
    public nav_category_Models(String name, String description, String discount, String img_url) {
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
