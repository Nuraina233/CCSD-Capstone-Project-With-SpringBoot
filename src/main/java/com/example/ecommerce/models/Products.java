package com.example.ecommerce.models;

public class Products {
    private int product_id;
    private String product_name;
    private double product_price;
    private String product_desc;
    private String product_color;
    private String product_category;

    //constructor
    public Products(int id, String name, double price, String desc, String color, String category){
        this.product_id = id;
        this.product_name = name;
        this.product_price = price;
        this.product_desc = desc;
        this.product_color = color;
        this.product_category = category;
    }

    //setter
    public int getProduct_id(){
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public String getProduct_color() {
        return product_color;
    }

    public String getProduct_category() {
        return product_category;
    }
}
