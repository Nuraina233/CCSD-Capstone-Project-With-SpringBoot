package com.example.ecommerce.controller;

import com.example.ecommerce.models.Orders;
import com.example.ecommerce.models.OrdersProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class OrderController {
    @GetMapping("/cart")
    public String order(){

        return "cart";
    }

//    private Orders orders;
//
//    @GetMapping("/cart")
//    public String displayCart(Model model) throws SQLException {
//        List<OrdersProduct> items = orders.getOrders();
//        double total = orders.calcTotal();
//        model.addAttribute("items", items);
//        model.addAttribute("total", total);
//        return "cart"; // Return the name of the Thymeleaf template (cart.html)
//    }
}
