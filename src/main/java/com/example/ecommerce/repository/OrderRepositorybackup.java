//package com.example.ecommerce.repository;
//
//import com.example.ecommerce.models.Orders;
//import com.example.ecommerce.models.OrdersProduct;
//import com.example.ecommerce.models.Products;
//import dbconfig.DBConfig;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrderRepository {
//    public static void main(String[] args) throws SQLException {
//
//    }
//
//    //all orderproducts table
//    static List<Products> orderProductsList = new ArrayList<>();
//
//    static String jdbcUrl = DBConfig.JDBC_URL;
//    static String username = DBConfig.USERNAME;
//    static String password = DBConfig.PASSWORD;
//
//    //Database table and column names for orderproducts
//    static String tableName = "orderproducts";
//    static String orderIdColumn = "order_id";
//    static String productIdColumn = "product_id";
//    static String qtyColumn = "product_qty";
//    static String paymentColumn = "payment_id";
//
//    //Initialize
//    static Connection connection = null;
//    static Statement statement = null;
//    static PreparedStatement preparedStatement = null;
//    static ResultSet resultSet = null;
//
//    public static List<OrdersProduct> getOrderProducts(int orderId) {
//        orderProductsList.clear();
//        try{
//            //connect to database
//            connection = DriverManager.getConnection(jdbcUrl,username,password);
//
//            //create statement
//            String sql = "SELECT * FROM orderproducts WHERE orderId = ?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//
//            //set parameter to placeholder
//            stmt.setInt(1, orderId);
//
//            //execute query
//            ResultSet rs = stmt.executeQuery();
//
//            //print data
//            while (rs.next()) {
//                int prodId = rs.getInt("product_id");
//                int quantity = rs.getInt("product_qty");
//                int paymentId = rs.getInt("payment_id");
//                Products product = getProductById(prodId);
//                OrdersProduct orderProduct = new OrdersProduct(product, orderId, prodId, quantity, paymentId, product);
//                orderProductsList.add(orderProduct);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();;
//        }
//        return orderProductsList;
//    }
//
//    //get a single product by id
//    public static Products getProductById(int prodId) {
//        Products product = null;
//        try{
//            //connect to database
//            connection = DriverManager.getConnection(jdbcUrl,username,password);
//
//            //create statement
//            String sql = "SELECT * FROM products WHERE prodId = ?";
//            PreparedStatement stmt = connection.prepareStatement(sql);
//
//            //set parameter to placeholder
//            stmt.setInt(1, prodId);
//
//            //execute query
//            ResultSet rs = stmt.executeQuery();
//
//            //print data
//            while (rs.next()) {
//                product = new Products(
//                        rs.getInt("product_id"),
//                        rs.getString("product_name"),
//                        rs.getString("product_image"),
//                        rs.getDouble("price"),
//                        rs.getString("description"),
//                        rs.getString("color"),
//                        rs.getString("category"),
//                        rs.getString("gender")
//                );
//                System.out.println(product);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return product;
//    }
//}
//
//package com.example.ecommerce.controller;//package com.example.ecommerce.controller;
//
////import com.example.ecommerce.models.Orders;
////import com.example.ecommerce.models.OrdersProduct;
////import com.example.ecommerce.models.Products;
////import com.example.ecommerce.repository.DisplayOrders;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.web.bind.annotation.*;
////
////import java.sql.Connection;
////import java.sql.PreparedStatement;
////import java.sql.ResultSet;
////import java.sql.SQLException;
////import java.util.ArrayList;
////import java.util.List;
////
////@Controller
////public class OrderController {
////
////    @GetMapping("/cart")
////    public String showCart(Model model) throws SQLException {
////        // Retrieve the current order or cart
////        List<OrdersProduct> currentOrder = DisplayOrders.getOrdersList(); // You need to implement this method
////
////        System.out.println(currentOrder);
////        model.addAttribute("orderproducts", currentOrder);
////
////        return "cart";
////    }
////
////    @PostMapping("/cart/addProduct")
////    public String addProductToOrder(@RequestParam("orderId") int orderId,
////                                    @RequestParam("prodId") int prodId,
////                                    @RequestParam("quantity") int quantity) {
////        Orders order = getOrderById(orderId);
////        if (order != null) {
////            // Assuming you have a method in Orders class to add a product with quantity
////            order.addProduct(prodId, quantity);
////        }
////        return "redirect:/cart";
////    }
////
////    @PostMapping("/cart/updateQuantity")
////    public String updateQuantity(@RequestParam("orderId") int orderId,
////                                 @RequestParam("prodId") int prodId,
////                                 @RequestParam("quantity") int quantity) {
////        Orders order = getOrderById(orderId);
////        if (order != null) {
////            // Assuming you have a method in Orders class to update the quantity of a product
////            order.updateProductQuantity(prodId, quantity);
////        }
////        return "redirect:/cart";
////    }
////
////    @PostMapping("/cart/removeProduct")
////    public String removeProduct(@RequestParam("orderId") int orderId,
////                                @RequestParam("prodId") int prodId) {
////        Orders order = getOrderById(orderId);
////        if (order != null) {
////            // Assuming you have a method in Orders class to remove a product
////            order.removeProduct(prodId); // You need to implement this method
////        }
////        return "redirect:/cart";
////    }
////
////    // Helper method to get order by ID
////    private Orders getOrderById(int orderId) {
////        // Implement logic to retrieve order by ID from your data source
//////        return null; // Placeholder, replace with actual implementation
////        try (Connection conn = getConnection()) {
////            String sql = "SELECT * FROM Orders WHERE orderId = ?";
////            PreparedStatement stmt = conn.prepareStatement(sql);
////            stmt.setInt(1, orderId);
////            ResultSet rs = stmt.executeQuery();
////
////            if (rs.next()) {
////                Orders order = new Orders(orderId);
////                List<OrdersProduct> orderProducts = getOrderProducts(orderId);
////                for (OrdersProduct orderProduct : orderProducts) {
////                    order.getOrderProducts().add(orderProduct);
////                }
////                return order;
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return null;
////    }
////
////    private List<OrdersProduct> getOrderProducts(int orderId) {
////        List<OrdersProduct> orderProducts = new ArrayList<>();
////        try (Connection conn = getConnection()) {
////            String sql = "SELECT * FROM OrdersProduct WHERE orderId = ?";
////            PreparedStatement stmt = conn.prepareStatement(sql);
////            stmt.setInt(1, orderId);
////            ResultSet rs = stmt.executeQuery();
////
////            while (rs.next()) {
////                int prodId = rs.getInt("prodId");
////                int quantity = rs.getInt("prodQuantity");
////                int paymentId = rs.getInt("paymentId");
////                Products product = getProductById(prodId);
////                OrdersProduct orderProduct = new OrdersProduct(orderId, prodId, quantity, paymentId, product);
////                orderProducts.add(orderProduct);
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return orderProducts;
////    }
////
////    private Products getProductById(int prodId) {
////        try (Connection conn = getConnection()) {
////            String sql = "SELECT * FROM Products WHERE prodId = ?";
////            PreparedStatement stmt = conn.prepareStatement(sql);
////            stmt.setInt(1, prodId);
////            ResultSet rs = stmt.executeQuery();
////
////            if (rs.next()) {
////                return new Products(
////                        rs.getInt("prodId"),
////                        rs.getString("prodName"),
////                        rs.getString("prodImage"),
////                        rs.getDouble("prodPrice"),
////                        rs.getString("prodDesc"),
////                        rs.getString("prodColor"),
////                        rs.getString("prodCategory"),
////                        rs.getString("prodGender")
////                );
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return null;
////    }
////
////    private Connection getConnection() {
////        return null;
////    }
////
////    private void updateOrderInDatabase(Orders order) {
////        // Implement logic to update the order in the database
////        // For simplicity, this example does not implement this method
////        // You need to write SQL statements to update the order and its products in the database
////    }
////}
//
////import com.example.ecommerce.models.Orders;
////import com.example.ecommerce.models.OrdersProduct;
////import jakarta.persistence.EntityManager;
////import jakarta.persistence.PersistenceContext;
////import jakarta.transaction.Transactional;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.List;
////
////@RestController
////public class OrderController {
////
////    @PersistenceContext
////    private EntityManager entityManager;
////
////    @GetMapping("/cart")
////    public List<Orders> getAllOrders() {
////        return entityManager.createQuery("SELECT o FROM orderproducts o", Orders.class).getResultList();
////    }
////
////    @GetMapping("/cart/{orderId}")
////    public Orders getOrderById(@PathVariable int orderId) {
////        return entityManager.find(Orders.class, orderId);
////    }
////
////    @PostMapping("/cart")
////    @Transactional
////    public Orders createOrder(@RequestBody Orders order) {
////        entityManager.persist(order);
////        return order;
////    }
////
////    @PutMapping("/cart/{orderId}/addProduct")
////    @Transactional
////    public Orders addProductToOrder(@PathVariable int orderId, @RequestBody OrdersProduct ordersProduct) {
////        Orders order = entityManager.find(Orders.class, orderId);
////        order.addProduct(ordersProduct);
////        entityManager.merge(order);
////        return order;
////    }
////
////    @DeleteMapping("/cart/{orderId}/removeProduct")
////    @Transactional
////    public Orders removeProductFromOrder(@PathVariable int orderId, @RequestParam int prodId) {
////        Orders order = entityManager.find(Orders.class, orderId);
////        order.removeProduct(prodId);
////        entityManager.merge(order);
////        return order;
////    }
////}
//
//import org.slf4j.Logger;
//import com.example.ecommerce.models.Orders;
//import com.example.ecommerce.models.OrdersProduct;
//import com.example.ecommerce.models.Products;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//        import java.sql.*;
//        import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class OrderController {
//    static List<OrdersProduct> ordersProductList = new ArrayList<>();
//
//
//    public static void main(String[] args) {
//        getOrderProducts(1);
//    }
//
//    private static final Logger logger = LoggerFactory.getLogger(com.example.ecommerce.controller.OrderController.class);
//
//    private static Connection getConnection() throws SQLException {
//        String url = "jdbc:mysql://localhost:8080/ecommerce"; // Replace 'yourdatabase' with your actual database name
//        String username = "root"; // Replace with your database username
//        String password = ""; // Replace with your database password
//        return DriverManager.getConnection(url, username, password);
//    }
//
////    @GetMapping("/cart")
////    public String showCart(Model model) {
////        List<OrdersProduct> currentOrder = getOrdersList();
////        logger.info("Current Order Products: {}", currentOrder);
////        model.addAttribute("orderproducts", currentOrder);
////        return "cart";
////    }
////
////    @PostMapping("/cart/addProduct")
////    public String addProductToOrder(@RequestParam("orderId") int orderId,
////                                    @RequestParam("prodId") int prodId,
////                                    @RequestParam("quantity") int quantity) {
////        Orders order = getOrderById(orderId);
////        if (order != null) {
////            order.addProduct(prodId, quantity);
////            updateOrderInDatabase(order);
////        }
////        return "redirect:/cart";
////    }
//
////    @PostMapping("/cart/updateQuantity")
////    public String updateQuantity(@RequestParam("orderId") int orderId,
////                                 @RequestParam("prodId") int prodId,
////                                 @RequestParam("quantity") int quantity) {
////        Orders order = getOrderById(orderId);
////        if (order != null) {
////            order.updateProductQuantity(prodId, quantity);
////            updateOrderInDatabase(order);
////        }
////        return "redirect:/cart";
////    }
//
////    @PostMapping("/cart/removeProduct")
////    public String removeProduct(@RequestParam("orderId") int orderId,
////                                @RequestParam("prodId") int prodId) {
////        Orders order = getOrderById(orderId);
////        if (order != null) {
////            order.removeProduct(prodId);
////            updateOrderInDatabase(order);
////        }
////        return "redirect:/cart";
////    }
//
//
////
////    private Orders getOrderById(int orderId) {
////        try (Connection conn = getConnection()) {
////            String sql = "SELECT * FROM Orders WHERE orderId = ?";
////            PreparedStatement stmt = conn.prepareStatement(sql);
////            stmt.setInt(1, orderId);
////            ResultSet rs = stmt.executeQuery();
////
////            if (rs.next()) {
////                Orders order = new Orders(orderId);
////                List<OrdersProduct> orderProducts = getOrderProducts(orderId);
////                order.getOrderProducts().addAll(orderProducts);
////                logger.info("Order fetched: {}", order);
////                return order;
////            }
////        } catch (SQLException e) {
////            logger.error("Error fetching order by ID", e);
////        }
////        return null;
////    }
//
//    public static List<OrdersProduct> getOrderProducts(int orderId) {
//        ordersProductList.clear();
//        try (Connection conn = getConnection()) {
//            String sql = "SELECT * FROM orderproducts WHERE orderId = ?";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setInt(1, orderId);
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                int prodId = rs.getInt("product_id");
//                int quantity = rs.getInt("product_qty");
//                int paymentId = rs.getInt("payment_id");
//                Products product = getProductById(prodId);
//                OrdersProduct orderProduct = new OrdersProduct(orderId, prodId, quantity, paymentId, product);
//                ordersProductList.add(orderProduct);
//            }
//        } catch (SQLException e) {
//            logger.error("Error fetching order products", e);
//        }
//        return ordersProductList;
//    }
//
//    private static Products getProductById(int prodId) {
//        try (Connection conn = getConnection()) {
//            String sql = "SELECT * FROM Products WHERE prodId = ?";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setInt(1, prodId);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                Products product = new Products(
//                        rs.getInt("prodId"),
//                        rs.getString("prodName"),
//                        rs.getString("prodImage"),
//                        rs.getDouble("prodPrice"),
//                        rs.getString("prodDesc"),
//                        rs.getString("prodColor"),
//                        rs.getString("prodCategory"),
//                        rs.getString("prodGender")
//                );
//                logger.info("Product fetched: {}", product);
//                return product;
//            }
//        } catch (SQLException e) {
//            logger.error("Error fetching product by ID", e);
//        }
//        return null;
//    }
//
//    private void updateOrderInDatabase(Orders order) {
//        try (Connection conn = getConnection()) {
//            // Delete all current products in the order
//            String deleteSql = "DELETE FROM OrdersProduct WHERE orderId = ?";
//            PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
//            deleteStmt.setInt(1, order.getOrderId());
//            deleteStmt.executeUpdate();
//
//            // Insert updated products into the order
//            String insertSql = "INSERT INTO OrdersProduct (orderId, prodId, prodQuantity, paymentId) VALUES (?, ?, ?, ?)";
//            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
//            for (OrdersProduct op : order.getOrderProducts()) {
//                insertStmt.setInt(1, op.getOrderId());
//                insertStmt.setInt(2, op.getProdID());
//                insertStmt.setInt(3, op.getProdQuantity());
//                insertStmt.setInt(4, op.getPaymentID());
//                insertStmt.addBatch();
//            }
//            insertStmt.executeBatch();
//        } catch (SQLException e) {
//            logger.error("Error updating order in database", e);
//        }
//    }
//
//    private List<OrdersProduct> getOrdersList() {
//        List<OrdersProduct> orderProducts = new ArrayList<>();
//        try (Connection conn = getConnection()) {
//            String sql = "SELECT * FROM OrdersProduct";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                int orderId = rs.getInt("orderId");
//                int prodId = rs.getInt("prodId");
//                int quantity = rs.getInt("prodQuantity");
//                int paymentId = rs.getInt("paymentId");
//                Products product = getProductById(prodId);
//                OrdersProduct orderProduct = new OrdersProduct(orderId, prodId, quantity, paymentId, product);
//                orderProducts.add(orderProduct);
//            }
//            logger.info("Orders list fetched: {}", orderProducts);
//        } catch (SQLException e) {
//            logger.error("Error fetching orders list", e);
//        }
//        return orderProducts;
//    }
//}
//
//
//package com.example.ecommerce.controller;//package com.example.ecommerce.controller;
//
////import com.example.ecommerce.models.Orders;
////import com.example.ecommerce.models.OrdersProduct;
////import com.example.ecommerce.models.Products;
////import com.example.ecommerce.repository.DisplayOrders;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.web.bind.annotation.*;
////
////import java.sql.Connection;
////import java.sql.PreparedStatement;
////import java.sql.ResultSet;
////import java.sql.SQLException;
////import java.util.ArrayList;
////import java.util.List;
////
////@Controller
////public class OrderController {
////
////    @GetMapping("/cart")
////    public String showCart(Model model) throws SQLException {
////        // Retrieve the current order or cart
////        List<OrdersProduct> currentOrder = DisplayOrders.getOrdersList(); // You need to implement this method
////
////        System.out.println(currentOrder);
////        model.addAttribute("orderproducts", currentOrder);
////
////        return "cart";
////    }
////
////    @PostMapping("/cart/addProduct")
////    public String addProductToOrder(@RequestParam("orderId") int orderId,
////                                    @RequestParam("prodId") int prodId,
////                                    @RequestParam("quantity") int quantity) {
////        Orders order = getOrderById(orderId);
////        if (order != null) {
////            // Assuming you have a method in Orders class to add a product with quantity
////            order.addProduct(prodId, quantity);
////        }
////        return "redirect:/cart";
////    }
////
////    @PostMapping("/cart/updateQuantity")
////    public String updateQuantity(@RequestParam("orderId") int orderId,
////                                 @RequestParam("prodId") int prodId,
////                                 @RequestParam("quantity") int quantity) {
////        Orders order = getOrderById(orderId);
////        if (order != null) {
////            // Assuming you have a method in Orders class to update the quantity of a product
////            order.updateProductQuantity(prodId, quantity);
////        }
////        return "redirect:/cart";
////    }
////
////    @PostMapping("/cart/removeProduct")
////    public String removeProduct(@RequestParam("orderId") int orderId,
////                                @RequestParam("prodId") int prodId) {
////        Orders order = getOrderById(orderId);
////        if (order != null) {
////            // Assuming you have a method in Orders class to remove a product
////            order.removeProduct(prodId); // You need to implement this method
////        }
////        return "redirect:/cart";
////    }
////
////    // Helper method to get order by ID
////    private Orders getOrderById(int orderId) {
////        // Implement logic to retrieve order by ID from your data source
//////        return null; // Placeholder, replace with actual implementation
////        try (Connection conn = getConnection()) {
////            String sql = "SELECT * FROM Orders WHERE orderId = ?";
////            PreparedStatement stmt = conn.prepareStatement(sql);
////            stmt.setInt(1, orderId);
////            ResultSet rs = stmt.executeQuery();
////
////            if (rs.next()) {
////                Orders order = new Orders(orderId);
////                List<OrdersProduct> orderProducts = getOrderProducts(orderId);
////                for (OrdersProduct orderProduct : orderProducts) {
////                    order.getOrderProducts().add(orderProduct);
////                }
////                return order;
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return null;
////    }
////
////    private List<OrdersProduct> getOrderProducts(int orderId) {
////        List<OrdersProduct> orderProducts = new ArrayList<>();
////        try (Connection conn = getConnection()) {
////            String sql = "SELECT * FROM OrdersProduct WHERE orderId = ?";
////            PreparedStatement stmt = conn.prepareStatement(sql);
////            stmt.setInt(1, orderId);
////            ResultSet rs = stmt.executeQuery();
////
////            while (rs.next()) {
////                int prodId = rs.getInt("prodId");
////                int quantity = rs.getInt("prodQuantity");
////                int paymentId = rs.getInt("paymentId");
////                Products product = getProductById(prodId);
////                OrdersProduct orderProduct = new OrdersProduct(orderId, prodId, quantity, paymentId, product);
////                orderProducts.add(orderProduct);
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return orderProducts;
////    }
////
////    private Products getProductById(int prodId) {
////        try (Connection conn = getConnection()) {
////            String sql = "SELECT * FROM Products WHERE prodId = ?";
////            PreparedStatement stmt = conn.prepareStatement(sql);
////            stmt.setInt(1, prodId);
////            ResultSet rs = stmt.executeQuery();
////
////            if (rs.next()) {
////                return new Products(
////                        rs.getInt("prodId"),
////                        rs.getString("prodName"),
////                        rs.getString("prodImage"),
////                        rs.getDouble("prodPrice"),
////                        rs.getString("prodDesc"),
////                        rs.getString("prodColor"),
////                        rs.getString("prodCategory"),
////                        rs.getString("prodGender")
////                );
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return null;
////    }
////
////    private Connection getConnection() {
////        return null;
////    }
////
////    private void updateOrderInDatabase(Orders order) {
////        // Implement logic to update the order in the database
////        // For simplicity, this example does not implement this method
////        // You need to write SQL statements to update the order and its products in the database
////    }
////}
//
////import com.example.ecommerce.models.Orders;
////import com.example.ecommerce.models.OrdersProduct;
////import jakarta.persistence.EntityManager;
////import jakarta.persistence.PersistenceContext;
////import jakarta.transaction.Transactional;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.List;
////
////@RestController
////public class OrderController {
////
////    @PersistenceContext
////    private EntityManager entityManager;
////
////    @GetMapping("/cart")
////    public List<Orders> getAllOrders() {
////        return entityManager.createQuery("SELECT o FROM orderproducts o", Orders.class).getResultList();
////    }
////
////    @GetMapping("/cart/{orderId}")
////    public Orders getOrderById(@PathVariable int orderId) {
////        return entityManager.find(Orders.class, orderId);
////    }
////
////    @PostMapping("/cart")
////    @Transactional
////    public Orders createOrder(@RequestBody Orders order) {
////        entityManager.persist(order);
////        return order;
////    }
////
////    @PutMapping("/cart/{orderId}/addProduct")
////    @Transactional
////    public Orders addProductToOrder(@PathVariable int orderId, @RequestBody OrdersProduct ordersProduct) {
////        Orders order = entityManager.find(Orders.class, orderId);
////        order.addProduct(ordersProduct);
////        entityManager.merge(order);
////        return order;
////    }
////
////    @DeleteMapping("/cart/{orderId}/removeProduct")
////    @Transactional
////    public Orders removeProductFromOrder(@PathVariable int orderId, @RequestParam int prodId) {
////        Orders order = entityManager.find(Orders.class, orderId);
////        order.removeProduct(prodId);
////        entityManager.merge(order);
////        return order;
////    }
////}
//
//import org.slf4j.Logger;
//import com.example.ecommerce.models.Orders;
//import com.example.ecommerce.models.OrdersProduct;
//import com.example.ecommerce.models.Products;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//        import java.sql.*;
//        import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class OrderController {
//    static List<OrdersProduct> ordersProductList = new ArrayList<>();
//
//
//    public static void main(String[] args) {
//        getOrderProducts(1);
//    }
//
//    private static final Logger logger = LoggerFactory.getLogger(com.example.ecommerce.controller.OrderController.class);
//
//    private static Connection getConnection() throws SQLException {
//        String url = "jdbc:mysql://localhost:8080/ecommerce"; // Replace 'yourdatabase' with your actual database name
//        String username = "root"; // Replace with your database username
//        String password = ""; // Replace with your database password
//        return DriverManager.getConnection(url, username, password);
//    }
//
////    @GetMapping("/cart")
////    public String showCart(Model model) {
////        List<OrdersProduct> currentOrder = getOrdersList();
////        logger.info("Current Order Products: {}", currentOrder);
////        model.addAttribute("orderproducts", currentOrder);
////        return "cart";
////    }
////
////    @PostMapping("/cart/addProduct")
////    public String addProductToOrder(@RequestParam("orderId") int orderId,
////                                    @RequestParam("prodId") int prodId,
////                                    @RequestParam("quantity") int quantity) {
////        Orders order = getOrderById(orderId);
////        if (order != null) {
////            order.addProduct(prodId, quantity);
////            updateOrderInDatabase(order);
////        }
////        return "redirect:/cart";
////    }
//
////    @PostMapping("/cart/updateQuantity")
////    public String updateQuantity(@RequestParam("orderId") int orderId,
////                                 @RequestParam("prodId") int prodId,
////                                 @RequestParam("quantity") int quantity) {
////        Orders order = getOrderById(orderId);
////        if (order != null) {
////            order.updateProductQuantity(prodId, quantity);
////            updateOrderInDatabase(order);
////        }
////        return "redirect:/cart";
////    }
//
////    @PostMapping("/cart/removeProduct")
////    public String removeProduct(@RequestParam("orderId") int orderId,
////                                @RequestParam("prodId") int prodId) {
////        Orders order = getOrderById(orderId);
////        if (order != null) {
////            order.removeProduct(prodId);
////            updateOrderInDatabase(order);
////        }
////        return "redirect:/cart";
////    }
//
//
////
////    private Orders getOrderById(int orderId) {
////        try (Connection conn = getConnection()) {
////            String sql = "SELECT * FROM Orders WHERE orderId = ?";
////            PreparedStatement stmt = conn.prepareStatement(sql);
////            stmt.setInt(1, orderId);
////            ResultSet rs = stmt.executeQuery();
////
////            if (rs.next()) {
////                Orders order = new Orders(orderId);
////                List<OrdersProduct> orderProducts = getOrderProducts(orderId);
////                order.getOrderProducts().addAll(orderProducts);
////                logger.info("Order fetched: {}", order);
////                return order;
////            }
////        } catch (SQLException e) {
////            logger.error("Error fetching order by ID", e);
////        }
////        return null;
////    }
//
//    public static List<OrdersProduct> getOrderProducts(int orderId) {
//        ordersProductList.clear();
//        try (Connection conn = getConnection()) {
//            String sql = "SELECT * FROM orderproducts WHERE orderId = ?";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setInt(1, orderId);
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                int prodId = rs.getInt("product_id");
//                int quantity = rs.getInt("product_qty");
//                int paymentId = rs.getInt("payment_id");
//                Products product = getProductById(prodId);
//                OrdersProduct orderProduct = new OrdersProduct(orderId, prodId, quantity, paymentId, product);
//                ordersProductList.add(orderProduct);
//            }
//        } catch (SQLException e) {
//            logger.error("Error fetching order products", e);
//        }
//        return ordersProductList;
//    }
//
//    private static Products getProductById(int prodId) {
//        try (Connection conn = getConnection()) {
//            String sql = "SELECT * FROM Products WHERE prodId = ?";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setInt(1, prodId);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                Products product = new Products(
//                        rs.getInt("prodId"),
//                        rs.getString("prodName"),
//                        rs.getString("prodImage"),
//                        rs.getDouble("prodPrice"),
//                        rs.getString("prodDesc"),
//                        rs.getString("prodColor"),
//                        rs.getString("prodCategory"),
//                        rs.getString("prodGender")
//                );
//                logger.info("Product fetched: {}", product);
//                return product;
//            }
//        } catch (SQLException e) {
//            logger.error("Error fetching product by ID", e);
//        }
//        return null;
//    }
//
//    private void updateOrderInDatabase(Orders order) {
//        try (Connection conn = getConnection()) {
//            // Delete all current products in the order
//            String deleteSql = "DELETE FROM OrdersProduct WHERE orderId = ?";
//            PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
//            deleteStmt.setInt(1, order.getOrderId());
//            deleteStmt.executeUpdate();
//
//            // Insert updated products into the order
//            String insertSql = "INSERT INTO OrdersProduct (orderId, prodId, prodQuantity, paymentId) VALUES (?, ?, ?, ?)";
//            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
//            for (OrdersProduct op : order.getOrderProducts()) {
//                insertStmt.setInt(1, op.getOrderId());
//                insertStmt.setInt(2, op.getProdID());
//                insertStmt.setInt(3, op.getProdQuantity());
//                insertStmt.setInt(4, op.getPaymentID());
//                insertStmt.addBatch();
//            }
//            insertStmt.executeBatch();
//        } catch (SQLException e) {
//            logger.error("Error updating order in database", e);
//        }
//    }
//
//    private List<OrdersProduct> getOrdersList() {
//        List<OrdersProduct> orderProducts = new ArrayList<>();
//        try (Connection conn = getConnection()) {
//            String sql = "SELECT * FROM OrdersProduct";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                int orderId = rs.getInt("orderId");
//                int prodId = rs.getInt("prodId");
//                int quantity = rs.getInt("prodQuantity");
//                int paymentId = rs.getInt("paymentId");
//                Products product = getProductById(prodId);
//                OrdersProduct orderProduct = new OrdersProduct(orderId, prodId, quantity, paymentId, product);
//                orderProducts.add(orderProduct);
//            }
//            logger.info("Orders list fetched: {}", orderProducts);
//        } catch (SQLException e) {
//            logger.error("Error fetching orders list", e);
//        }
//        return orderProducts;
//    }
//}
//
//
//
