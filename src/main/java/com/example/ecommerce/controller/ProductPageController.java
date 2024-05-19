package com.example.ecommerce.controller;

import com.example.ecommerce.service.ProductPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductPageController {

    @Autowired
    private ProductPageService productPageService;

    @GetMapping("/product_page")
    public String viewProductPage(Model model){
     model.addAttribute("listProducts", productPageService.getAllProductPage());
     return "product_page";
    }
    }
