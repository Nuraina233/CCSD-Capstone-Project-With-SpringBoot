package com.example.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ProductViewController {
    @GetMapping("/productView")
    public String productView(){
        return "productView"; // Return the view name for the home page
    }
}
