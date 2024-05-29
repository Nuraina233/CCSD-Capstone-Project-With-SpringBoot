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

import com.example.ecommerce.controller.OrderController;
import com.example.ecommerce.models.OrderSummary;
import com.example.ecommerce.models.Products;
import com.example.ecommerce.models.OrdersProduct;
import dbconfig.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.ecommerce.controller.OrderController.*;

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

    //Get product for cart
    public static Products getProductById(int passId, String size, int stock) throws SQLException {
        Products products = null;
        try {
            //connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            //create a statement
            String sql = "SELECT * FROM products WHERE product_id = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameter value for the product_id placeholder
            preparedStatement.setInt(1, passId);

            //execute the query to retrieve data
            resultSet = preparedStatement.executeQuery();

            //print data
            while (resultSet.next()) {
                int id = passId;
                String name = resultSet.getString("product_name");
                String image = resultSet.getString("product_image");
                double price = resultSet.getDouble("price");
                products = new Products(id, name, image, price, 1, (1*price), size, stock);
            }

            System.out.println("Retrieve Product by product id " +passId);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return products;
    }

    //Create order and insert products into database
    public static void createOrder(List<Products> productsList, OrderSummary orders, String invoice) throws SQLException {
        try {
            // Connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Create a statement
            String sqlOrders = "INSERT INTO orders(`order_invoice`, `customer_id`, `order_date`) VALUES (?, ?, ?)";
            
            preparedStatement = connection.prepareStatement(sqlOrders);

            // Set parameter
            preparedStatement.setString(1, invoice);
            preparedStatement.setInt(2, orders.getCustomerId());
            preparedStatement.setDate(3, orders.getOrderDate());

            int rowsOrders = preparedStatement.executeUpdate();

            System.out.println("PAYMENT METHOD:" +orders.getPayment_method());

            int payment_id;
            if(orders.getPayment_method().equalsIgnoreCase("credit-card"))
                payment_id = 1;
            else if (orders.getPayment_method().equalsIgnoreCase("cash-on-delivery"))
                payment_id = 2;
            else
                payment_id = 3;

            int rowsAffected = 0;
            String sqlOrdersProduct = "INSERT INTO orderproducts(`order_invoice`, `product_id`, `product_size`, `product_qty`, `payment_id`) VALUES (?, ?, ?, ?, ?)";;
            for(Products product : productsList){
                preparedStatement = connection.prepareStatement(sqlOrdersProduct);

                // Set parameter
                preparedStatement.setString(1, invoice);
                preparedStatement.setInt(2, product.getProdId());
                preparedStatement.setString(3, product.getProdSize());
                preparedStatement.setInt(4, product.getProdQty());
                preparedStatement.setInt(5, payment_id);

                //updateStock(product);

                rowsAffected = preparedStatement.executeUpdate();
            }

            System.out.println("ROW AFFECT FOR CREATE ORDER: " +rowsAffected);

            // Check if any rows were inserted
            if (rowsAffected > 0 && rowsOrders > 0) {
                System.out.println("Order successfully created!");
                OrderController.productsList.clear();
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

    public static void updateStock(Products product) throws SQLException {

        System.out.println("READ UPDATESTOCK");
        try {
            //connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            //create a statement
            String sql = "UPDATE stocks SET stock = ? WHERE product_id = ? AND size = ?;";
            System.out.println(sql);

            int rowsUpdate = 0;

            preparedStatement = connection.prepareStatement(sql);

            // Set the parameter value for all columns' placeholder
            preparedStatement.setInt(1, (product.getProdStock() - product.getProdQty()));
            preparedStatement.setInt(2, product.getProdId());
            preparedStatement.setString(3, product.getProdSize());

            rowsUpdate = preparedStatement.executeUpdate();


            System.out.println("ROW AFFECT TO UPDATE:" + rowsUpdate);

            // Check if any rows were updated
            if (rowsUpdate > 0) {
                System.out.println("Record updated successfully!");
            } else {
                System.out.println("No records updated!");
            }

            int rowsAffected = 0;
            //Delete the stock if stock = 0
            if (product.getProdStock() - product.getProdQty() == 0) {
                try {
                    //connect to database
                    connection = DriverManager.getConnection(jdbcUrl, username, password);

                    //create a statement
                    sql = "DELETE FROM stocks WHERE product_id = ? AND size = ?;";
                    preparedStatement = connection.prepareStatement(sql);

                    // Set the parameter value for the product_id placeholder
                    preparedStatement.setInt(1, product.getProdId());
                    preparedStatement.setDouble(2, Double.parseDouble(product.getProdSize()));

                    // Execute the DELETE statement
                    rowsAffected = preparedStatement.executeUpdate();

                    // Check if any rows were deleted
                    if (rowsAffected > 0) {
                        System.out.println("Stock updated: Out of Stock!");
                    } else {
                        System.out.println("No records deleted!");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
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


