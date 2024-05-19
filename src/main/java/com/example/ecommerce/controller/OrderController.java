package com.example.ecommerce.controller;

import com.example.ecommerce.models.Orders;
import com.example.ecommerce.models.OrdersProduct;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class OrderController {

    @GetMapping
    public String showCart(Model model) throws SQLException {
        List<OrdersProduct> currentOrder = OrderRepository.getOrderProductList(1); // Placeholder order ID
        double totalSubtotal = calculateTotalSubtotal(currentOrder);

        model.addAttribute("orderproducts", currentOrder);
        model.addAttribute("totalSubtotal", totalSubtotal);

        return "cart";
    }

    @PostMapping("/addProduct")
    public String addProductToOrder(@RequestParam("orderId") int orderId,
                                    @RequestParam("prodId") int prodId,
                                    @RequestParam("quantity") int quantity) throws SQLException {
        OrderRepository.insertOrder(orderId, prodId, quantity, 1); // Assuming a paymentId of 1, change as needed
        return "redirect:/cart";
    }

    @PostMapping("/updateQuantity")
    public String updateQuantity(@RequestParam("orderId") int orderId,
                                 @RequestParam("prodId") int prodId,
                                 @RequestParam("quantity") int quantity) throws SQLException {
        OrderRepository.updateProductQuantity(orderId, prodId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/removeProduct")
    public String removeProduct(@RequestParam("orderproductId") int orderproductId,
                                Model model) throws SQLException {
        OrderRepository.removeOrderProduct(orderproductId);

        List<OrdersProduct> currentOrder = OrderRepository.getOrderProductList(1); // Placeholder order ID
        double totalSubtotal = calculateTotalSubtotal(currentOrder);

        model.addAttribute("orderproducts", currentOrder);
        model.addAttribute("totalSubtotal", totalSubtotal);

        return "redirect:/cart";
    }

    private double calculateTotalSubtotal(List<OrdersProduct> orderProducts) {
        return orderProducts.stream().mapToDouble(OrdersProduct::getSubtotal).sum();
    }
}
