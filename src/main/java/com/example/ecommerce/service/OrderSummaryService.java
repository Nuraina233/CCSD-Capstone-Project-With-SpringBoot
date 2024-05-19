package com.example.ecommerce.service;

import com.example.ecommerce.models.OrderSummary;
import com.example.ecommerce.repository.OrderSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderSummaryService {

    @Autowired
    private OrderSummaryRepository orderSummaryRepository;

    public List<OrderSummary> getOrderSummaries() {
        return orderSummaryRepository.findAllOrderSummaries();
    }
}
