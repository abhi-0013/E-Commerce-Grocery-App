package com.example.abhishek.mygrocerystore.Models;

import java.io.Serializable;

public class ViewAllProduct_Model implements Serializable {

    private String name;
    private String description;
    private String ratings;
    private String price;
    private String img_url;
    private String type;

    public ViewAllProduct_Model() {
    }

    public ViewAllProduct_Model(String name, String description, String ratings, String price, String img_url, String type) {
        this.name = name;
        this.description = description;
        this.ratings = ratings;
        this.price = price;
        this.img_url = img_url;
        this.type = type;
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

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ViewAllProduct_Model{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ratings='" + ratings + '\'' +
                ", price='" + price + '\'' +
                ", img_url='" + img_url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
