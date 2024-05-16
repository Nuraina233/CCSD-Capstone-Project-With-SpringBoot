package service;

import org.springframework.stereotype.Service;

public class PaymentService {
    public String completePayment(String orderID) {
        // Logic to complete payment
        // Verify the payment with PayPal
        // Save transaction details to the database
        return "Payment completed successfully";
    }
}
