package com.example.ecommerce;

import com.example.ecommerce.view.DisplayCustomer;
import com.example.ecommerce.models.Customers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class CustomerController {

    @GetMapping
    public String getCustomers(Model model) throws SQLException {
//        model.addAttribute("customers", "HELLO");
        // Retrieve the list of customers from the displayCustomer class
        List<Customers> customers = DisplayCustomer.getCustomersList();

        // Add the list of customers to the model
        model.addAttribute("customers", customers);

//        try {
//
//        } catch (SQLException e) {
//            // Handle SQL exception
//            e.printStackTrace();
//            // You might want to return an error view here
//            return "error";
//        }

        // Return the view name (HTML file)
        return "customers";
    }
}
