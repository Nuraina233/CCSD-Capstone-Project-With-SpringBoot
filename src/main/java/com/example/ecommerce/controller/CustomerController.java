package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.service.CustomerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/edit")
    public String editCustomerForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();
        Customer customer = customerService.getCustomerByEmail(currentEmail);
        System.out.println(currentEmail);
        System.out.println(customer.getCustomer_id());
        model.addAttribute("customer", customer);
        return "edit_customerb";
    }

    @PostMapping("/customer/update")
    public String updateCustomer(@ModelAttribute("customer") Customer customer, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();
        Customer existingCustomer = customerService.getCustomerByEmail(currentEmail);
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setPhone(customer.getPhone());
        customerService.updateCustomer(existingCustomer);
        return "redirect:/customer/detail";
    }

    @GetMapping("/customer/detail")
    public String viewCustomer(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();
        Customer customer = customerService.getCustomerByEmail(currentEmail);
        model.addAttribute("customer", customer);
        return "customer_detail";
    }

}