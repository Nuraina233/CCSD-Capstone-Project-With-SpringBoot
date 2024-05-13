package com.example.ecommerce.models;

public class Orders {
    private Products products;
    private double size;
    private int prodQuantity;

    public Orders(Products products, double size, int prodQuantity){
        this.products = products;
        this.size = size;
        this.prodQuantity = prodQuantity;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(int prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    public double getSubtotal(){
        return products.getProdPrice() * prodQuantity;
    }

}
