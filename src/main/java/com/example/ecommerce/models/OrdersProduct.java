package com.example.ecommerce.models;

public class OrdersProduct {
    private int orderId;
    private int prodID;
    private int paymentID;
    private Products products;
    private int prodQuantity;

    public OrdersProduct(int orderId, int prodID, int prodQuantity, int paymentID/*, Products products*/){
        this.orderId = orderId;
        this.prodID = prodID;
        this.paymentID = paymentID;
        //this.products = products;
        this.prodQuantity = prodQuantity;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

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
