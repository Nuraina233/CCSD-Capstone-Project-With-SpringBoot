package com.example.ecommerce.service.impl;

import com.example.ecommerce.models.ProductPage;
import com.example.ecommerce.repository.ProductPageRepository;
import com.example.ecommerce.service.ProductPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPageServiceImpl implements ProductPageService {

    @Autowired
    private ProductPageRepository productPageRepository;

    @Override
    public List<ProductPage> getAllProductPage(){
        return productPageRepository.findAll();
    }
}
