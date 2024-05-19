package com.example.ecommerce.service;

import com.example.ecommerce.models.ProductPage;

import java.util.List;

public interface ProductPageService {
    List<ProductPage> getAllProductPage();

    ProductPage getProductPageById(Integer product_id);
}
