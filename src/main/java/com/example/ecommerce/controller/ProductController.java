package com.example.ecommerce.controller;

import com.example.ecommerce.models.Products;
import com.example.ecommerce.models.Stocks;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class ProductController {

    @GetMapping("/productAdmin")
    public String getCustomers(Model model) throws SQLException {
        List<Products> products = ProductRepository.getProductList();


        // Add the list of products to the model
        model.addAttribute("products", products);

        // Return the view name (HTML file)
        return "adminPage";
    }

    @PostMapping("/productAdmin/editProduct")
    public String editProductPage(@RequestParam("prod_id") int id, Model model) throws SQLException {

        List<String> categories = new ArrayList<>();
        categories.clear();
        categories.add("Running Shoes");
        categories.add("Sneakers");
        categories.add("Outdoor");

        List<String> genders = new ArrayList<>();
        genders.clear();
        genders.add("Men");
        genders.add("Women");

        //get the list of product that want to edit
        Products products = ProductRepository.getEditProduct(id);
        model.addAttribute("products", products);
        model.addAttribute("genders", genders);
        model.addAttribute("categories", categories);

        // Return the result page
        return "editProduct";
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("prod_id") int id, Model model) throws SQLException {

        //get the list of product that want to edit
        ProductRepository.getDeleteProduct(id);

        // Add the list of products to the model
        List<Products> products = ProductRepository.getProductList();
        model.addAttribute("products", products);

        // Return the result page
        return "redirect:/productAdmin";
    }

    @PostMapping("/update")
    public String updateProduct(@RequestParam("passId") String passId,
                         @RequestParam("passName") String passName,
                         @RequestParam("passImage") String passImage,
                         @RequestParam("passPrice") String passPrice,
                         @RequestParam("passDesc") String passDesc,
                         @RequestParam("passColor") String passColor,
                         @RequestParam("passCategory") String passCategory,
                         @RequestParam("passGender") String passGender,
                         Model model) throws SQLException {

        ProductRepository dp = new ProductRepository();
        dp.getUpdateProduct(Integer.parseInt(passId),passName,passImage,Double.parseDouble(passPrice),passDesc,passColor,passCategory,passGender);

        // Add the list of products to the model
        List<Products> products = ProductRepository.getProductList();
        model.addAttribute("products", products);

        return "redirect:/productAdmin";
    }

    @PostMapping("/productAdmin/addProduct")
    public String addProductPage(Model model) throws SQLException {

        // Add the list of products to the model
        List<Products> products = ProductRepository.getProductList();
        model.addAttribute("products", products);

        return "addProduct";
    }

    @PostMapping("/back")
    public String back(){

        return "redirect:/productAdmin";
    }



    @PostMapping("/addProduct")
    public String addProduct(@RequestParam("passName") String passName,
                                 @RequestParam("passImage") String passImage,
                                 @RequestParam("passPrice") String passPrice,
                                 @RequestParam("passDesc") String passDesc,
                                 @RequestParam("passColor") String passColor,
                                 @RequestParam("passCategory") String passCategory,
                                 @RequestParam("passGender") String passGender,
                                 Model model) throws SQLException {

        ProductRepository dp = new ProductRepository();
        dp.getInsertProduct(passName,passImage,Double.parseDouble(passPrice),passDesc,passColor,passCategory,passGender);

        // Add the list of products to the model
        List<Products> products = ProductRepository.getProductList();
        model.addAttribute("products", products);

        return "redirect:/productAdmin";
    }

    @PostMapping("/productAdmin/stock")
    public String stockProductPage(@RequestParam("prod_id") int id, Model model) throws SQLException {

        //List the product by Id
        Products products = ProductRepository.getEditProduct(id);
        model.addAttribute("products", products);

        //List of stock by product id
        List<Stocks> stocks= ProductRepository.getStockProduct(id);
        model.addAttribute("stocks", stocks);

        return "stockProduct";
    }

    @PostMapping("/productAdmin/stock/remove")
    public String removeStock(@RequestParam("stockId") int stockId,
                              @RequestParam("prodId") int prodId,
                              Model model) throws SQLException {

        //get the list of product that want to edit
        ProductRepository.getRemoveStock(stockId);

        //List the product by Id
        Products products = ProductRepository.getEditProduct(prodId);
        model.addAttribute("products", products);

        //List of stock by product id
        List<Stocks> stocks= ProductRepository.getStockProduct(prodId);
        model.addAttribute("stocks", stocks);

        return "stockProduct";
    }

    @PostMapping("/productAdmin/stock/addButton")
    public String addStockButton(@RequestParam("passProdId") int id,
                              Model model) throws SQLException {

        //List the product by Id
        Products products = ProductRepository.getEditProduct(id);
        model.addAttribute("products", products);

        //List of stock by product id
        List<Stocks> stocks= ProductRepository.getStockProduct(id);
        model.addAttribute("stocks", stocks);

        return "addStock";
    }

    @PostMapping("/productAdmin/stock/addStock")
    public String addStock(@RequestParam("prodId") int prodId,
                           @RequestParam("passSize") double passSize,
                           @RequestParam("passStock") int passStock,
                              Model model) throws SQLException {

        //get the list of product that want to edit
        ProductRepository.getInsertStock(prodId, passSize, passStock);

        //List the product by Id
        Products products = ProductRepository.getEditProduct(prodId);
        model.addAttribute("products", products);

        //List of stock by product id
        List<Stocks> stocks= ProductRepository.getStockProduct(prodId);
        model.addAttribute("stocks", stocks);

        return "stockProduct";
    }


    @PostMapping("/productAdmin/stock/update")
    public String updateStock(@RequestParam("stockId") int stockId,
                              @RequestParam("prodId") int prodId,
                              @RequestParam("passStockQty") int passStockQty,
                              Model model) throws SQLException {

        //get the list of product that want to edit
        ProductRepository.getUpdateStock(passStockQty, stockId);

        //List the product by Id
        Products products = ProductRepository.getEditProduct(prodId);
        model.addAttribute("products", products);

        //List of stock by product id
        List<Stocks> stocks= ProductRepository.getStockProduct(prodId);
        model.addAttribute("stocks", stocks);

        System.out.println("Quantity: " +passStockQty);

        return "stockProduct";
    }

}
