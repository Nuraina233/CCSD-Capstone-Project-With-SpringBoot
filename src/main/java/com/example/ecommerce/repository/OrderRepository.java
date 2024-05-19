//package com.example.ecommerce.repository;
//
//import com.example.ecommerce.models.Products;
//import com.example.ecommerce.models.OrdersProduct;
//import dbconfig.DBConfig;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.sql.*;
//
//public class OrderRepository {
//    static List<OrdersProduct> ordersProductList = new ArrayList<>();
//
//    static String jdbcUrl = DBConfig.JDBC_URL;
//    static String username = DBConfig.USERNAME;
//    static String password = DBConfig.PASSWORD;
//
//    //Initialize
//    static Connection connection = null;
//    static Statement statement = null;
//    static ResultSet resultSet = null;
//    static PreparedStatement preparedStatement = null;
//
//    public static void main(String[] args) throws SQLException {
//        //createOrder(3, Date.valueOf("2024-05-19"));
//        //insertOrder(1,5, 5, 1);
//        //getOrderProductList(1);
//        removeOrderProduct(4); //maybe kene tukar
//    }
//
//    public static void createOrder(int cust_id, Date date) throws SQLException{
//
//        try {
//            //connect to database
//            connection = DriverManager.getConnection(jdbcUrl, username, password);
//
//            //create a statement
//            String sql = "INSERT INTO orders(`customer_id`, `order_date`) VALUES (?,?)";
//
//            preparedStatement = connection.prepareStatement(sql);
//
//            //set parameter
//            preparedStatement.setInt(1,cust_id);
//            preparedStatement.setDate(2, date);
//
//            // Execute the INSERT statement
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            // Check if any rows were inserted
//            if (rowsAffected > 0) {
//                System.out.println("Order successfully created!");
//            } else {
//                System.out.println("No records inserted!");
//            }
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }finally {
//            // Close resources
//            if (resultSet != null) {
//                resultSet.close();
//            }
//            if (statement != null) {
//                statement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//    }
//
//    public static void insertOrder (int passOrderId, int passProdId, int passQty, int passPaymentId) throws SQLException {
//
//        try {
//            //connect to database
//            connection = DriverManager.getConnection(jdbcUrl, username, password);
//
//            //create a statement
//            String sql = "INSERT INTO orderproducts(`order_id`, `product_id`, `product_qty`, `payment_id`) " +
//                    "VALUES (?,?,?,?)";
//
//            preparedStatement = connection.prepareStatement(sql);
//
//            //set parameter
//            preparedStatement.setInt(1,passOrderId);
//            preparedStatement.setInt(2,passProdId);
//            preparedStatement.setInt(3,passQty);
//            preparedStatement.setInt(4,passPaymentId);
//
//            // Execute the INSERT statement
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            // Check if any rows were inserted
//            if (rowsAffected > 0) {
//                System.out.println("Order item inserted successfully!");
//            } else {
//                System.out.println("No records inserted!");
//            }
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }finally {
//            // Close resources
//            if (resultSet != null) {
//                resultSet.close();
//            }
//            if (statement != null) {
//                statement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//    }
//
//    public static List<OrdersProduct> getOrderProductList(int passOrderId) throws SQLException{
//        ordersProductList.clear();
//
//        try{
//            //connect to database
//            connection = DriverManager.getConnection(jdbcUrl,username,password);
//
//            //create a statement
//            String sql = "SELECT \n" +
//                    "    op.orderproduct_id,\n" +
//                    "    op.order_id,\n" +
//                    "    op.product_id,\n" +
//                    "    p.product_name,\n" +
//                    "    p.product_image,\n" +
//                    "    p.price,\n" +
//                    "    op.product_qty,\n" +
//                    "    (op.product_qty * p.price) AS total\n" +
//                    "FROM \n" +
//                    "    orderproducts op\n" +
//                    "INNER JOIN \n" +
//                    "    products p ON op.product_id = p.product_id\n" +
//                    "WHERE \n" +
//                    "    op.order_id = ?;\n";
//
//
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1,passOrderId);
//
//            //execute the query to retrieve data
//            resultSet = preparedStatement.executeQuery();
//
//            double totalSubtotal = 0;
//
//            //print data
//            while(resultSet.next()){
//                int orderproductId = resultSet.getInt("orderproduct_id");
//                int orderId = resultSet.getInt("order_id");
//                int productId = resultSet.getInt("product_id");
//                String productName = resultSet.getString("product_name");
//                String productImage = resultSet.getString("product_image");
//                double price = resultSet.getDouble("price");
//                int qty = resultSet.getInt("product_qty");
//                double total = resultSet.getDouble("total");
//                Products product = new Products(productId, productName, productImage, price);
//                OrdersProduct products =  new OrdersProduct(orderproductId, orderId, productId, qty, product, total);
//                ordersProductList.add(products);
//
//                totalSubtotal += total;
//            }
//
//            System.out.println("Retrieve a list of products");
//            System.out.println(ordersProductList);
//
//            System.out.println("Total subtotal: " + totalSubtotal);
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }finally {
//            // Close resources
//            if (resultSet != null) {
//                resultSet.close();
//            }
//            if (statement != null) {
//                statement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//
//        return ordersProductList;
//    }
//
//    //remove order
//    public static void removeOrderProduct(int passId) throws SQLException {
//        try {
//            //connect to database
//            connection = DriverManager.getConnection(jdbcUrl, username, password);
//
//            //create a statement
//            String sql = "DELETE FROM orderproducts WHERE orderproduct_id = ?";
//            preparedStatement = connection.prepareStatement(sql);
//
//            // Set the parameter value for the product_id placeholder
//            preparedStatement.setInt(1, passId);
//
//            // Execute the DELETE statement
//            int rowsAffected = preparedStatement.executeUpdate();
//
//            // Check if any rows were deleted
//            if (rowsAffected > 0) {
//                System.out.println("A product removed from order successfully!");
//            } else {
//                System.out.println("No records deleted!");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            // Close resources
//            if (resultSet != null) {
//                resultSet.close();
//            }
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//    }
//}

package com.example.ecommerce.repository;

import com.example.ecommerce.models.Products;
import com.example.ecommerce.models.OrdersProduct;
import dbconfig.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    static List<OrdersProduct> ordersProductList = new ArrayList<>();

    static String jdbcUrl = DBConfig.JDBC_URL;
    static String username = DBConfig.USERNAME;
    static String password = DBConfig.PASSWORD;

    // Initialize
    static Connection connection = null;
    static Statement statement = null;
    static ResultSet resultSet = null;
    static PreparedStatement preparedStatement = null;

    public static void main(String[] args) throws SQLException {
        //createOrder(3, Date.valueOf("2024-05-19"));
        //insertOrder(1, 5, 5, 1);
        //getOrderProductList(1);
        //removeOrderProduct(4); //maybe kene tukar
    }

    public static void createOrder(int cust_id, Date date) throws SQLException {
        try {
            // Connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Create a statement
            String sql = "INSERT INTO orders(`customer_id`, `order_date`) VALUES (?, ?)";

            preparedStatement = connection.prepareStatement(sql);

            // Set parameter
            preparedStatement.setInt(1, cust_id);
            preparedStatement.setDate(2, date);

            // Execute the INSERT statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if any rows were inserted
            if (rowsAffected > 0) {
                System.out.println("Order successfully created!");
            } else {
                System.out.println("No records inserted!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            closeResources();
        }
    }

    public static void insertOrder(int passOrderId, int passProdId, int passQty, int passPaymentId) throws SQLException {
        try {
            // Connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Create a statement
            String sql = "INSERT INTO orderproducts(`order_id`, `product_id`, `product_qty`, `payment_id`) VALUES (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);

            // Set parameter
            preparedStatement.setInt(1, passOrderId);
            preparedStatement.setInt(2, passProdId);
            preparedStatement.setInt(3, passQty);
            preparedStatement.setInt(4, passPaymentId);

            // Execute the INSERT statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if any rows were inserted
            if (rowsAffected > 0) {
                System.out.println("Order item inserted successfully!");
            } else {
                System.out.println("No records inserted!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            closeResources();
        }
    }

    public static List<OrdersProduct> getOrderProductList(int passOrderId) throws SQLException {
        ordersProductList.clear();

        try {
            // Connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Create a statement
            String sql = "SELECT op.orderproduct_id, op.order_id, op.product_id, p.product_name, p.product_image, p.price, op.product_qty, (op.product_qty * p.price) AS total " +
                    "FROM orderproducts op " +
                    "INNER JOIN products p ON op.product_id = p.product_id " +
                    "WHERE op.order_id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, passOrderId);

            // Execute the query to retrieve data
            resultSet = preparedStatement.executeQuery();

            double totalSubtotal = 0;

            // Retrieve data
            while (resultSet.next()) {
                int orderproductId = resultSet.getInt("orderproduct_id");
                int orderId = resultSet.getInt("order_id");
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                String productImage = resultSet.getString("product_image");
                double price = resultSet.getDouble("price");
                int qty = resultSet.getInt("product_qty");
                double total = resultSet.getDouble("total");
                Products product = new Products(productId, productName, productImage, price);
                OrdersProduct ordersProduct = new OrdersProduct(orderproductId, orderId, productId, qty, product, total);
                ordersProductList.add(ordersProduct);

                totalSubtotal += total;
            }

            System.out.println("Retrieve a list of products");
            System.out.println(ordersProductList);

            System.out.println("Total subtotal: " + totalSubtotal);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            closeResources();
        }

        return ordersProductList;
    }

    public static void updateProductQuantity(int orderId, int prodId, int quantity) throws SQLException {
        try {
            // Connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Create a statement
            String sql = "UPDATE orderproducts SET product_qty = ? WHERE order_id = ? AND product_id = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set parameters
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, orderId);
            preparedStatement.setInt(3, prodId);

            // Execute the UPDATE statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if any rows were updated
            if (rowsAffected > 0) {
                System.out.println("Quantity updated successfully!");
            } else {
                System.out.println("No records updated!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            closeResources();
        }
    }

    public static void removeOrderProduct(int passId) throws SQLException {
        try {
            // Connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Create a statement
            String sql = "DELETE FROM orderproducts WHERE orderproduct_id = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameter value for the product_id placeholder
            preparedStatement.setInt(1, passId);

            // Execute the DELETE statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if any rows were deleted
            if (rowsAffected > 0) {
                System.out.println("A product removed from order successfully!");
            } else {
                System.out.println("No records deleted!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            closeResources();
        }
    }

    // Helper method to close resources
    private static void closeResources() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}


