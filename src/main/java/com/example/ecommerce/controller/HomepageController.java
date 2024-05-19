package com.example.ecommerce.controller;

import com.example.ecommerce.models.Products;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

public class HomepageController {

    @GetMapping("/home")
    public String homepage(Model model) throws SQLException {
        List<Products> products1 = ProductRepository.getProductList();

        // Add the list of products to the model
        model.addAttribute("products", products1);

        // Return the view name (HTML file)
        return "homepage";
    }

}
