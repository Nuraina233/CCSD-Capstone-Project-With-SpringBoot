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
import java.util.List;

@Controller
public class ProductController {

    //admin homepage
    @GetMapping("/admin")
    public String adminPage(Model model) throws SQLException {
        List<Products> products = ProductRepository.getProductList();

        // Add the list of products to the model
        model.addAttribute("products", products);

        // Return the view name (HTML file)
        return "adminPage";
    }

    //filter products based on gender
    @GetMapping("/admin/gender")
    public String genderPage(@RequestParam String gender, Model model) throws SQLException {

        //List the product by Gender
        List<Products> products = ProductRepository.getProductByGenderList(gender);
        model.addAttribute("products", products);

        return "adminPage";
    }

    //filter products based on category
    @GetMapping("/admin/category")
    public String categoryPage(@RequestParam String category, Model model) throws SQLException {

        //List the product by category
        List<Products> products = ProductRepository.getProductByCategoryList(category);
        model.addAttribute("products", products);

        return "adminPage";
    }

    //redirect to edit product page
    @PostMapping("/admin/editProduct")
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
        Products products = ProductRepository.getProductById(id);
        model.addAttribute("products", products);
        model.addAttribute("genders", genders);
        model.addAttribute("categories", categories);

        // Return the result page
        return "editProduct";
    }

    //update product process and redirect to admin homepage
    @PostMapping("/admin/editProduct/updateProduct")
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
        Products products = ProductRepository.getProductById(Integer.parseInt(passId));
        model.addAttribute("products", products);
        model.addAttribute("genders", genders);
        model.addAttribute("categories", categories);

        return "editProduct";
    }

    //delete product process and redirect to admin homepage
    @PostMapping("/admin/deleteProduct")
    public String deleteProduct(@RequestParam("prod_id") int id, Model model) throws SQLException {

        //get the list of product that want to edit
        ProductRepository.getDeleteProduct(id);

        // Add the list of products to the model
        List<Products> products = ProductRepository.getProductList();
        model.addAttribute("products", products);

        // Return the result page
        return "redirect:/admin";
    }

    // redirect to add new product page
    @PostMapping("/admin/add")
    public String addProductPage(Model model) throws SQLException {

        // Add the list of products to the model
        List<Products> products = ProductRepository.getProductList();
        model.addAttribute("products", products);

        return "addProduct";
    }

    //add new product into database and redirect to admin homepage
    @PostMapping("/admin/add/addProduct")
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

        return "redirect:/admin";
    }

    //redirect to add stock page
    @PostMapping("/admin/stock")
    public String stockProductPage(@RequestParam("prod_id") int id, Model model) throws SQLException {

        //List the product by Id
        Products products = ProductRepository.getProductById(id);
        model.addAttribute("products", products);

        //List of stock by product id
        List<Stocks> stocks= ProductRepository.getStockProduct(id);
        model.addAttribute("stocks", stocks);

        return "stockProduct";
    }

    //remove the size of shoes from the stock table in database and back to stock to display new list of stock
    @PostMapping("/admin/stock/remove")
    public String removeStock(@RequestParam("stockId") int stockId,
                              @RequestParam("prodId") int prodId,
                              Model model) throws SQLException {

        //get the list of product that want to edit
        ProductRepository.getRemoveStock(stockId);

        //List the product by Id
        Products products = ProductRepository.getProductById(prodId);
        model.addAttribute("products", products);

        //List of stock by product id
        List<Stocks> stocks= ProductRepository.getStockProduct(prodId);
        model.addAttribute("stocks", stocks);

        return "stockProduct";
    }

    //go to add size for stock page
    @PostMapping("/admin/stock/addButton")
    public String addStockButton(@RequestParam("passProdId") int id,
                                 Model model) throws SQLException {

        //List the product by Id
        Products products = ProductRepository.getProductById(id);
        model.addAttribute("products", products);

        //List of stock by product id
        List<Stocks> stocks= ProductRepository.getStockProduct(id);
        model.addAttribute("stocks", stocks);

        return "addStock";
    }

    // add new size with quantities in database
    @PostMapping("/admin/stock/addStock")
    public String addStock(@RequestParam("prodId") int prodId,
                           @RequestParam("passSize") double passSize,
                           @RequestParam("passStock") int passStock,
                           Model model) throws SQLException {

        //get the list of product that want to edit
        ProductRepository.getInsertStock(prodId, passSize, passStock);

        //List the product by Id
        Products products = ProductRepository.getProductById(prodId);
        model.addAttribute("products", products);

        //List of stock by product id
        List<Stocks> stocks= ProductRepository.getStockProduct(prodId);
        model.addAttribute("stocks", stocks);

        return "stockProduct";
    }

    // update the quantities of shoes based on their sizes
    @PostMapping("/admin/stock/update")
    public String updateStock(@RequestParam("stockId") int stockId,
                              @RequestParam("prodId") int prodId,
                              @RequestParam("passStockQty") int passStockQty,
                              Model model) throws SQLException {

        //get the list of product that want to edit
        ProductRepository.getUpdateStock(passStockQty, stockId);

        //List the product by Id
        Products products = ProductRepository.getProductById(prodId);
        model.addAttribute("products", products);

        //List of stock by product id
        List<Stocks> stocks= ProductRepository.getStockProduct(prodId);
        model.addAttribute("stocks", stocks);

        System.out.println("Quantity: " +passStockQty);

        return "stockProduct";
    }

    // back to admin page process
    @PostMapping("/admin/back")
    public String back(){

        return "redirect:/admin";
    }
}
