package com.example.ecommerce.models;

public class Products {
    private int prodId;
    private String prodName;
    private String prodImage;
    private double prodPrice;
    private String prodDesc;
    private String prodColor;
    private String prodCategory;
    private String prodGender;

    //constructor
    public Products(int id, String name, String image, double price, String desc, String color, String category, String gender){
        this.prodId = id;
        this.prodName = name;
        this.prodImage = image;
        this.prodPrice = price;
        this.prodDesc = desc;
        this.prodColor = color;
        this.prodCategory = category;
        this.prodGender = gender;
    }

    public Products(int prodId, String prodName, String prodImage, double prodPrice){
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodImage = prodImage;
        this.prodPrice = prodPrice;
    }


    //getter method
    public int getProdId(){
        return prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public String getProdImage() {
        return prodImage;
    }

    public double getProdPrice() {
        return prodPrice;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public String getProdColor() {
        return prodColor;
    }

    public String getProdCategory() {
        return prodCategory;
    }

    public String getProdGender() {
        return prodGender;
    }
}
