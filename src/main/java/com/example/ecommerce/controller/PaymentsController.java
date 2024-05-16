package com.example.ecommerce.controller;

//import com.example.ecommerce.models.Payments;
//import com.example.ecommerce.view.DisplayPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import service.PaymentService;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/api")
public class PaymentsController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/paypal-transaction-complete")
    public String completePayment(@RequestBody String orderID) {
        // Implement logic to handle the completion of the payment
        // This typically involves saving the transaction details to the database
        // and interacting with the PayPal API to verify the transaction
        return paymentService.completePayment(orderID);
    }
    }

