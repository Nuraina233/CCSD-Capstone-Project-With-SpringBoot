package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        super();
        this.customerService = customerService;
    }

    //    @GetMapping("/customers")
//    public String listCustomers(Model model){
//        model.addAttribute("customers", customerService.getAllCustomer());
//        return "customers";
//    }
//
//    @GetMapping("/customers/new")
//    public String createCustomerForm(Model model){
//
//        Customer customer = new Customer();
//        model.addAttribute("customer",customer);
//        return "create_customer";
//    }
//
//    @PostMapping("/customers")
//    public String saveCustomerForm(@ModelAttribute("customer") Customer customer){
//        customerService.saveCustomer(customer);
//        return "redirect:/customers";
//    }
//
    @GetMapping("/customers/edit/{customer_id}")
    public String editCustomerForm(@PathVariable Integer customer_id, Model model){
        model.addAttribute("customer", customerService.getCustomerById(customer_id));
        return"edit_customerb";
    }

    @PostMapping("/customers/{customer_id}")
    public String updateCustomer(@PathVariable Integer customer_id,@ModelAttribute("customer")Customer customer, Model model){
        Customer existingCustomer = customerService.getCustomerById(customer_id);
        existingCustomer.setCustomer_id(customer_id);
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setPassword(customer.getPassword());

        customerService.updateCustomer((existingCustomer));
        return "redirect:/customer/" + customer_id;
    }

//    @GetMapping("/customers/{customer_id}")
//    public String deleteStudent(@PathVariable Integer customer_id){
//        customerService.deleteCustomerById(customer_id);
//        return "redirect:/customers";
//    }

    @GetMapping("/customer/{customer_id}")
    public String viewCustomerById(@PathVariable Integer customer_id, Model model){
        Customer customer = customerService.getCustomerById(customer_id);
        model.addAttribute("customer", customer);
        return "customer_detail"; // Ensure you have a template named "customer_detail.html"
    }
}

