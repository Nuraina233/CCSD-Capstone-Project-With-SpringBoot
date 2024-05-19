package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        super();
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    @Override
    public  Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Integer customer_id){
        return customerRepository.findById(customer_id).get();
    }

    @Override
    public Customer updateCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Integer customer_id){
        customerRepository.deleteById(customer_id);
    }

    @Override
    public Customer getCustomerByEmail(String currentEmail) {

        return customerRepository.findByEmail(currentEmail);
    }



}



