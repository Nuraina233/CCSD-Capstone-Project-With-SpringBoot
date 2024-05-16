package com.example.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @GetMapping("/cart")
    public String order(){

        return "cartt";
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
//        return "cart2"; // Return the name of the Thymeleaf template (cartt.html)
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
