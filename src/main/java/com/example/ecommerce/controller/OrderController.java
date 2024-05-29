package com.example.ecommerce.controller;

import com.example.ecommerce.models.*;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    public static List<Products> productsList = new ArrayList<>();

    @GetMapping("/viewCart")
    public String viewCart(Model model) throws SQLException{
        if(productsList.isEmpty()) {
            return "redirect:/home?back";
        }else {

            model.addAttribute("orderproducts", productsList);
            double subtotal = 0;
            for(Products product : productsList){
                subtotal += product.getProdTotal();
            }
            model.addAttribute("totalSubtotal", subtotal);

            return "cart";
        }
    }

    @PostMapping("/cart")
    public String showCart(@RequestParam("prod_id") int prodId,
                           @RequestParam("size") String size,
                           Model model) throws SQLException {

        System.out.println("SIZE: " + size);

        if(size.isEmpty()) {
            return "redirect:/singlePage/" + prodId + "?alert";
        }else {

            String[] parts = size.split(",");
            size = parts[0]; // Extract size
            int stock = Integer.parseInt(parts[1]);   // Extract id

            for(Products product:productsList){
                if(product.getProdId()==prodId)
                    return "redirect:/home";
            }

            System.out.println("Product ID in Cart: " + prodId);
            System.out.println("SIZE: " + size);

            Products productCart = OrderRepository.getProductById(prodId, size, stock);

            productsList.add(productCart);

            model.addAttribute("orderproducts", productsList);
            double subtotal = 0;
            for(Products product : productsList){
                subtotal += product.getProdTotal();
            }
            model.addAttribute("totalSubtotal", subtotal);

            return "cart";
        }
    }

    @PostMapping("/cart/updateQuantity")
    public String updateQuantity(@RequestParam("prodId") int prodId,
                                 @RequestParam("prodQty") int prodQty,
                                 Model model) throws SQLException {

        double subtotal = 0;

        System.out.println("PROD ID:" +prodId);
        System.out.println("PROD Qty:" +prodQty);

        for(Products product:productsList){
            if(product.getProdId()==prodId) {
                product.setProdQty(prodQty);
                product.setProdTotal(prodQty);
            }

            subtotal += product.getProdTotal();
        }

        model.addAttribute("orderproducts", productsList);
        model.addAttribute("totalSubtotal", subtotal);

        return "cart";
    }

    @PostMapping("/cart/removeProduct")
    public String removeProduct(@RequestParam("orderproductId") int orderproductId,
                                Model model) throws SQLException {

        for(Products product: productsList){
            if(product.getProdId() == orderproductId) {
                productsList.remove(product);
                break;
            }
        }
        if(productsList.isEmpty()) {
            return "redirect:/home";
        }
        else {
            model.addAttribute("orderproducts", productsList);

            double subtotal = 0;
            for(Products product : productsList){
                subtotal += product.getProdTotal();
            }
            model.addAttribute("totalSubtotal", subtotal);

            return "cart";
        }
    }


}
