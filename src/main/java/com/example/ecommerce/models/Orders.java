package com.example.ecommerce.models;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    private List<OrdersProduct> orders;

    public Orders(){
        this.orders = new ArrayList<>();
    }

    public void addOrder(OrdersProduct order){
        orders.add(order);
    }

    public void removeOrder(OrdersProduct order){
        orders.remove(order);
    }

    public double calcTotal(){
        double total = 0;
        for (OrdersProduct order : orders){
            total += order.getSubtotal();
        }
        return total;
    }

    public List<OrdersProduct> getOrders() {
        return orders;
    }
}
