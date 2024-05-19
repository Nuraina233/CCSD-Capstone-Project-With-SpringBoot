package com.example.ecommerce.controller;

import com.example.ecommerce.models.ProductPage;
import com.example.ecommerce.service.ProductPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductPageController {

    @Autowired
    private ProductPageService productPageService;

    @GetMapping("/product_page")
    public String viewProductPage(Model model){
     model.addAttribute("listProducts", productPageService.getAllProductPage());
     return "product_page";
    }
    @GetMapping("/singlePage/{product_id}")
    public String viewProductPageById(@PathVariable Integer product_id, Model model){
        ProductPage productPage = productPageService.getProductPageById(product_id);
        model.addAttribute("singleProducts", productPage);
        return "single_product";
    }
    }
