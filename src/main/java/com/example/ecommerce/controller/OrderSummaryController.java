package com.example.ecommerce.controller;

import com.example.ecommerce.models.OrderSummary;
import com.example.ecommerce.service.OrderSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderSummaryController {

    @Autowired
    private OrderSummaryService orderSummaryService;

    @GetMapping("/orderSummary")
    public String showOrderSummary(Model model) {
        List<OrderSummary> orderSummaries = orderSummaryService.getOrderSummaries();
        model.addAttribute("orderSummaries", orderSummaries);
        return "orderSummary";
    }
}
