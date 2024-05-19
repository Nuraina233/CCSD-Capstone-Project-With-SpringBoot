/*package com.example.ecommerce.controller;

import com.example.ecommerce.models.OrdersProduct;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.DisplayOrderProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class OrderProductsController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders")
    public String getAllOrders(Model model) throws SQLException {
        // Retrieve the current order or cart
        List<OrdersProduct> currentOrder = DisplayOrderProducts.getOrderProductsList(); // Ensure this method is implemented in DisplayOrders

        System.out.println(currentOrder);
        model.addAttribute("orders", currentOrder);

        return "orderSummary"; // This returns the orderSummary.html view
    }
}*/
