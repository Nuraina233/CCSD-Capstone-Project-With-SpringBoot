package com.example.ecommerce.models;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    private int orderId;
    private List<OrdersProduct> ordersProduct;

    public Orders(int orderId){
        this.orderId = orderId;
        this.ordersProduct = new ArrayList<>();
    }

    public int getOrderId(){
        return orderId;
    }

    public List<OrdersProduct> getOrderProducts(){
        return ordersProduct;
    }

    public void addProduct(int prodId, int quantity) {
        //ordersProduct.add(orderId);
        // Implement logic to add a product to the order
        // You might fetch product details from a database or some other data source
        // and then create an OrdersProduct object to add to the orderProducts list
    }
//    public void addProduct(OrdersProduct ordersProduct){}

    public void updateProductQuantity(int prodId, int quantity) {
        // Implement logic to update the quantity of a product in the order
        // Find the OrdersProduct with the given prodId and update its quantity
    }

    public void removeProduct(int prodId) {
        // Implement logic to remove a product from the order
        // Find the OrdersProduct with the given prodId and remove it from the orderProducts list
        ordersProduct.removeIf(product -> product.getProdID() == prodId);
    }

    public double calcTotal(){
        double total = 0;
        for (OrdersProduct order : ordersProduct){
            total += order.getSubtotal();
        }
        return total;
    }
}
