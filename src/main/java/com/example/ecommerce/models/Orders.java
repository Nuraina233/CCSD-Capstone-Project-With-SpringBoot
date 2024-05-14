package com.example.ecommerce.models;

public class Orders {
    private int orderId;
    private int prodID;
    private int paymentID;
    private Products products;
//    private double size;
    private int prodQuantity;

    public Orders(int orderId, int prodID, int prodQuantity, int paymentID/*, Products products, double size*/){
        this.orderId = orderId;
        this.prodID = prodID;
        this.paymentID = paymentID;
        //this.products = products;
        //this.size = size;
        this.prodQuantity = prodQuantity;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

//    public double getSize() {
//        return size;
//    }
//
//    public void setSize(double size) {
//        this.size = size;
//    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
