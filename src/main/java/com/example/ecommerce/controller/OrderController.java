package com.example.ecommerce.controller;

//import com.example.ecommerce.models.Orders;
//import com.example.ecommerce.models.OrdersProduct;
//import com.example.ecommerce.models.Products;
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//
////import javax.annotation.PostConstruct;
//import javax.sql.DataSource;
//import java.sql.*;
//import java.util.List;
//
//@Controller
//public class OrderController {
////    @GetMapping("/cart")
////    public String order(){
////
////        return "cart";
////    }
//    @Autowired
//    private DataSource dataSource;
//
//    private Orders orders;
//
//    @PostConstruct
//    public void init() {
//        orders = new Orders();
//        // Add initial items to cart for demonstration purposes
//        addToOrders(1, 2); // Add 2 items of product with ID 1
//        addToOrders(2, 1); // Add 1 item of product with ID 2
//    }
//
//    @GetMapping("/cart")
//    public String displayOrders(Model model) {
//        List<OrdersProduct> items = orders.getOrders();
//        double total = orders.calcTotal();
//        model.addAttribute("items", items);
//        model.addAttribute("total", total);
//        return "cart"; // Return the name of the Thymeleaf template (cart.html)
//    }
//
//    private Products getProdId(int productId) {
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
//                        resultSet.getString("desc"),
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
//
//    public void addToOrders(int productId, int quantity) {
//        Products products = getProdId(productId);
//        if (products != null) {
//            int orderId=0;
//            int paymentID=0;
//            OrdersProduct prod = new OrdersProduct(orderId, productId, quantity, paymentID);
//            orders.addOrder(prod);
//        } else {
//            System.out.println("Product not found");
//        }
//    }
//}
import com.example.ecommerce.models.Orders;
import com.example.ecommerce.models.OrdersProduct;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class OrderController {

//    @GetMapping("/cart")
//    public ModelAndView showCart() {
//        // Retrieve the current order or cart
//        Orders currentOrder = getCurrentOrder(); // You need to implement this method
//
//        ModelAndView modelAndView = new ModelAndView("Cart");
//        modelAndView.addObject("order", currentOrder);
//        return modelAndView;
//    }

    @GetMapping("/cart")
    public String showCart(Model model) throws SQLException {
        // Retrieve the current order or cart
        List<OrdersProduct> currentOrder = OrderRepository.getOrderProductList(1); // You need to implement this method

        model.addAttribute("orderproducts", currentOrder);

        return "cart";
    }

    @PostMapping("/cart/addProduct")
    public String addProductToOrder(@RequestParam("orderId") int orderId,
                                    @RequestParam("prodId") int prodId,
                                    @RequestParam("quantity") int quantity) {
        Orders order = getOrderById(orderId);
        if (order != null) {
            // Assuming you have a method in Orders class to add a product with quantity
            order.addProduct(prodId, quantity); // You need to implement this method
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/updateQuantity")
    public String updateQuantity(@RequestParam("orderId") int orderId,
                                 @RequestParam("prodId") int prodId,
                                 @RequestParam("quantity") int quantity) {
        Orders order = getOrderById(orderId);
        if (order != null) {
            // Assuming you have a method in Orders class to update the quantity of a product
            order.updateProductQuantity(prodId, quantity); // You need to implement this method
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/removeProduct")
    public String removeProduct(@RequestParam("orderId") int orderId,
                                @RequestParam("prodId") int prodId) {
        Orders order = getOrderById(orderId);
        if (order != null) {
            // Assuming you have a method in Orders class to remove a product
            order.removeProduct(prodId); // You need to implement this method
        }
        return "redirect:/cart";
    }

    // Helper method to get order by ID
    private Orders getOrderById(int orderId) {
        // Implement logic to retrieve order by ID from your data source
        return null; // Placeholder, replace with actual implementation
    }


    // Other methods for managing orders
}