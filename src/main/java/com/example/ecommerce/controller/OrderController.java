package com.example.ecommerce.controller;//package com.example.ecommerce.controller;

import com.example.ecommerce.models.Orders;
import com.example.ecommerce.models.OrdersProduct;
import com.example.ecommerce.repository.DisplayOrders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@Controller
public class OrderController {

    @GetMapping("/cart")
    public String showCart(Model model) throws SQLException {
        // Retrieve the current order or cart
        List<OrdersProduct> currentOrder = DisplayOrders.getOrdersList(); // You need to implement this method

        System.out.println(currentOrder);
        model.addAttribute("orders", currentOrder);

        return "cart";
    }

    @PostMapping("/cart/addProduct")
    public String addProductToOrder(@RequestParam("orderId") int orderId,
                                    @RequestParam("prodId") int prodId,
                                    @RequestParam("quantity") int quantity) {
        Orders order = getOrderById(orderId);
        if (order != null) {
            // Assuming you have a method in Orders class to add a product with quantity
            order.addProduct(prodId, quantity); // You need to implement this method
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/updateQuantity")
    public String updateQuantity(@RequestParam("orderId") int orderId,
                                 @RequestParam("prodId") int prodId,
                                 @RequestParam("quantity") int quantity) {
        Orders order = getOrderById(orderId);
        if (order != null) {
            // Assuming you have a method in Orders class to update the quantity of a product
            order.updateProductQuantity(prodId, quantity); // You need to implement this method
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/removeProduct")
    public String removeProduct(@RequestParam("orderId") int orderId,
                                @RequestParam("prodId") int prodId) {
        Orders order = getOrderById(orderId);
        if (order != null) {
            // Assuming you have a method in Orders class to remove a product
            order.removeProduct(prodId); // You need to implement this method
        }
        return "redirect:/cart";
    }

    // Helper method to get order by ID
    private Orders getOrderById(int orderId) {
        // Implement logic to retrieve order by ID from your data source
        return null; // Placeholder, replace with actual implementation
    }
    // Other methods for managing orders
}