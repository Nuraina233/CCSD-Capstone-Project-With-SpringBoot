package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.models.OrderSummary;
import com.example.ecommerce.models.Products;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.service.OrderSummaryService;
import com.example.ecommerce.service.CustomerService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class OrderSummaryController {
    private CustomerService customerService;
    OrderSummary orderSummary;

    public OrderSummaryController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @Autowired
    private OrderSummaryService orderSummaryService;

    @GetMapping("/orderSummary")
    public String showOrderSummary(Model model) {

        LocalDate currentDate = LocalDate.now();

        Date sqlDate = Date.valueOf(currentDate);

        System.out.println("Current date: " + currentDate);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();
        Customer customer = customerService.getCustomerByEmail(currentEmail);

        System.out.println(currentEmail);
        List<OrderSummary> orderSummaries = new ArrayList<>();

        double totalPrice = 0;
        for(Products product : OrderController.productsList){
            totalPrice += product.getProdTotal();
        }
        orderSummary = new OrderSummary(customer.getCustomer_id(), sqlDate, totalPrice );

        model.addAttribute("orders", OrderController.productsList);
        model.addAttribute("orderSummary", orderSummary);

        return "orderSummary";
    }

    @PostMapping("/payment")
    public String payment(Model model) {

        return "payments";
    }

    @PostMapping("/receipt")
    public String receipt(@RequestParam("payment-method") String payment_method,
                          Model model) throws SQLException {

        // Generate a random UUID
        String invoiceNumber = UUID.randomUUID().toString();

        // Optionally, you can remove dashes
        invoiceNumber = invoiceNumber.replace("-", "");

        System.out.println("Generated Invoice Number: " + invoiceNumber);

        orderSummary = new OrderSummary(orderSummary.getCustomerId(), orderSummary.getOrderDate(), orderSummary.getTotalPrice(), payment_method);

        List<Products> list = OrderController.productsList;

        for (Products product : list) {
            OrderRepository.updateStock(product);
        }

        OrderRepository.createOrder(list, orderSummary, invoiceNumber);

        model.addAttribute("invoice", invoiceNumber);
        model.addAttribute("payment", orderSummary.getPayment_method());
        model.addAttribute("price", orderSummary.getTotalPrice());

        return "receipt";
    }
}
