package com.example.ecommerce.models;

public class OrdersProduct {
    private int orderproductId;
    private int orderId;
    private int prodID;
    private int paymentID;
    private Products products;
    private int prodQuantity;
    private double total;

    public OrdersProduct(int orderproductId, int orderId, int prodID, int prodQuantity, int paymentID, Products products){
        super();
        this.orderproductId = orderproductId;
        this.orderId = orderId;
        this.prodID = prodID;
        this.paymentID = paymentID;
        this.prodQuantity = prodQuantity;
        this.products = products;
    }

    public OrdersProduct(int orderproductId, int orderId, int prodID, int prodQuantity, Products product, double total){
        super();
        this.orderproductId = orderproductId;
        this.orderId = orderId;
        this.prodID = prodID;
        this.prodQuantity = prodQuantity;
        this.products = product;
        this.total = total;
    }

    public int getOrderproductId() {
        return orderproductId;
    }

    public void setOrderproductId(int orderproductId) {
        this.orderproductId = orderproductId;
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

    public int getProdID() {
        return prodID;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }
}
