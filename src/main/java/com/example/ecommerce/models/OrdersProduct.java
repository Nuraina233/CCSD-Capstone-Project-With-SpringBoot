package com.example.ecommerce.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
