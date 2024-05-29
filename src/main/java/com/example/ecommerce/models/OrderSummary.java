package com.example.ecommerce.models;

import java.sql.Date;
import java.time.LocalDate;

public class OrderSummary {

//    private Long orderId;
    private int customerId;
    private Date orderDate;
    private Double totalPrice;
    private String payment_method;

    public OrderSummary(int customerId, Date orderDate, Double totalPrice) {
//        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public OrderSummary(int customerId, Date orderDate, Double totalPrice, String payment_method) {
//        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.payment_method = payment_method;
    }

    // Getters
//    public Long getOrderId() {
//        return orderId;
//    }

    public int getCustomerId() {
        return customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public String getPayment_method() {
        return payment_method;
    }

    // Setters
//    public void setOrderId(Long orderId) {
//        this.orderId = orderId;
//    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
