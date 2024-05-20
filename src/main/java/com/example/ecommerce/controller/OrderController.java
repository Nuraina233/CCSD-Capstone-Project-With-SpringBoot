package com.example.ecommerce.controller;

import com.example.ecommerce.models.OrdersProduct;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class OrderController {

    @GetMapping("/cart")
    public String showCart(Model model) throws SQLException {
        // Retrieve the current order or cart
        List<OrdersProduct> currentOrder = OrderRepository.getOrderProductList(1);

        // Calculate total subtotal
        double totalSubtotal = 0;
        for (OrdersProduct ordersProduct : currentOrder) {
            totalSubtotal += ordersProduct.getSubtotal();
        }

        model.addAttribute("orderproducts", currentOrder);
        model.addAttribute("totalSubtotal", totalSubtotal);

        return "cart";
    }

    @PostMapping("/cart/updateQuantity")
    public String updateQuantity(@RequestParam("orderId") int orderId,
                                 @RequestParam("prodId") int prodId,
                                 @RequestParam("quantity") int quantity) throws SQLException {
        // Update the quantity in the database using OrderRepository
        OrderRepository.updateProductQuantity(orderId, prodId, quantity);

        return "redirect:/cart"; // Redirect back to the cart page
    }

    @PostMapping("/cart/removeProduct")
    public String removeProduct(@RequestParam("orderproductId") int orderproductId) throws SQLException {
        // Remove the product item
        OrderRepository.removeOrderProduct(orderproductId);

        return "redirect:/cart";
    }
}
