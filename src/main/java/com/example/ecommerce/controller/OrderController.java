package com.example.ecommerce.controller;

import com.example.ecommerce.models.Orders;
import com.example.ecommerce.models.OrdersProduct;
import com.example.ecommerce.models.Products;
import jakarta.annotation.PostConstruct;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class OrderController {
    @GetMapping("/cart")
    public String order(){

        return "cart";
    }
//    @Autowired
//    private DataSource dataSource;
//
//    private Orders orders;

//    @PostConstruct
//    public void init() {
//        orders = new Orders();
//        // Add initial items to cart for demonstration purposes
//        addToOrders(1, 2); // Add 2 items of product with ID 1
//        addToOrders(2, 1); // Add 1 item of product with ID 2
//    }

//    @GetMapping("/cart")
//    public String displayCart(Model model) {
//        List<OrdersProduct> items = orders.getOrders();
//        double total = orders.calcTotal();
//        model.addAttribute("items", items);
//        model.addAttribute("total", total);
//        return "cart2"; // Return the name of the Thymeleaf template (cart.html)
//    }
//
//    private Products getProductById(int productId) {
//        String query = "SELECT * FROM products WHERE id = ?";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, productId);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return new Products(
//                        resultSet.getInt("id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("image"),
//                        resultSet.getDouble("price"),
//                        resultSet.getString("description"),
//                        resultSet.getString("color"),
//                        resultSet.getString("category"),
//                        resultSet.getString("gender")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public void addToOrders(int orderId, int prodID, int prodQuantity, int paymentID) {
//        Products products = products.getProdId(prodID);
//        if (products != null) {
//            OrdersProduct order = new OrdersProduct(orderId, prodID, prodQuantity, paymentID);
//            orders.addOrder(order);
//        } else {
//            System.out.println("Product not found");
//        }
//    }
}
