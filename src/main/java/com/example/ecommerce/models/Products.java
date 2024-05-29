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
    private int prodQty;
    private double prodTotal;
    private String prodSize;
    private int prodStock;

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

    public Products(int prodId, String prodName, String prodImage, double prodPrice, int prodQty, double prodTotal, String prodSize, int prodStock){
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodImage = prodImage;
        this.prodPrice = prodPrice;
        this.prodQty = prodQty;
        this.prodTotal = prodTotal;
        this.prodSize = prodSize;
        this.prodStock = prodStock;
    }

    public Products(int prodId, String prodColor){
        this.prodId = prodId;
        this.prodColor = prodColor;
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

    public int getProdQty() {
        return prodQty;
    }

    public double getProdTotal() {
        return prodTotal;
    }

    public String getProdSize() {
        return prodSize;
    }

    public int getProdStock() {
        return prodStock;
    }

    public void setProdTotal(int qty) {
        this.prodTotal =  qty * prodPrice;
    }

    public void setProdQty(int prodQty) {
        this.prodQty = prodQty;
    }

    public void setProdSize(String prodSize) {
        this.prodSize = prodSize;
    }

    public void setProdStock(int prodStock) {
        this.prodStock = prodStock;
    }
}
