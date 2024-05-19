package com.example.ecommerce.service;

import com.example.ecommerce.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomer();

    Customer saveCustomer(Customer customer);

    Customer getCustomerById(Integer customer_id);

    Customer updateCustomer(Customer customer);

    void deleteCustomerById(Integer customer_id);

    Customer getCustomerByEmail(String currentEmail);
}
