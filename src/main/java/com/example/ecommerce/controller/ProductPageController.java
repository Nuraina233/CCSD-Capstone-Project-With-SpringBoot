package com.example.ecommerce.controller;

import com.example.ecommerce.models.ProductPage;
import com.example.ecommerce.models.Products;
import com.example.ecommerce.models.Stocks;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ProductPageController {

    @Autowired
    private ProductPageService productPageService;

    @GetMapping("/product_page")
    public String viewProductPage(Model model){
     model.addAttribute("listProducts", productPageService.getAllProductPage());
     return "product_page";
    }

    @GetMapping("/singlePage/{product_id}")
    public String viewProductPageById(@PathVariable Integer product_id,
                                      //@RequestParam("prod_name") String name,
                                      Model model) throws SQLException {

        System.out.println("THE PRODUCT ID: " +product_id);

        ProductPage productPage = productPageService.getProductPageById(product_id);

        List<Stocks> stocks = ProductRepository.getStockProduct(product_id);
        //List<Products> colors = ProductRepository.getProductByColorList(name);

        model.addAttribute("singleProducts", productPage);
        model.addAttribute("stocks", stocks);
        //model.addAttribute("colors", colors);

        return "single_product";
    }

    @GetMapping("/home")
    public String homepage(Model model) throws SQLException {
        List<Products> products = ProductRepository.getProductList(); // You need to implement this method

        model.addAttribute("products", products);

        return "homepage";
    }

    @GetMapping("/home/gender")
    public String homeGenderPage(@RequestParam String gender, Model model) throws SQLException {

        //List the product by Gender
        List<Products> products = ProductRepository.getProductByGenderList(gender);
        model.addAttribute("products", products);

        return "homepage";
    }

    @GetMapping("/home/category")
    public String homeCategoryPage(@RequestParam String category, Model model) throws SQLException {

        //List the product by Gender
        List<Products> products = ProductRepository.getProductByCategoryList(category);
        model.addAttribute("products", products);

        return "homepage";
    }

    @PostMapping("/addToCart")
    @ResponseBody
    public ResponseEntity<String> addToCart(@RequestParam("productId") int productId,
                                            @RequestParam("size") String size) throws SQLException {

        System.out.println("SIZE: " +size);
        if (size == null || size.isEmpty()) {
            return new ResponseEntity<>("Invalid product ID or size", HttpStatus.BAD_REQUEST);
        }

        // Retrieve product from the database
        Products product = ProductRepository.getProductById(productId);

        if (product == null) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        // Example: Add logic to handle adding the product to the cart
        // For example: cartService.addToCart(product, size);

        return new ResponseEntity<>("Product added to cart", HttpStatus.OK);
    }


    @PostMapping("/home/cart")
    public String productTry(@RequestParam("selectedSize") String size,
                             Model model) throws SQLException {

        System.out.println("SIZE:" +size);

        List<Products> products = ProductRepository.getProductList(); // You need to implement this method

        model.addAttribute("products", products);

        return "homepage";
    }

}
