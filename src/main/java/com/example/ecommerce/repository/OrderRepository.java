package com.example.ecommerce.repository;

import com.example.ecommerce.models.Products;
import com.example.ecommerce.models.OrdersProduct;
import dbconfig.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class OrderRepository {
    static List<OrdersProduct> ordersProductList = new ArrayList<>();

    static String jdbcUrl = DBConfig.JDBC_URL;
    static String username = DBConfig.USERNAME;
    static String password = DBConfig.PASSWORD;

    //Initialize
    static Connection connection = null;
    static Statement statement = null;
    static ResultSet resultSet = null;
    static PreparedStatement preparedStatement = null;

    public static void main(String[] args) throws SQLException {
        //createOrder(3, Date.valueOf("2024-05-19"));
        //insertOrder(1,5, 5, 1);
        getOrderProductList(1);
    }

//    public static void main(String[] args) {
//        String jdbcUrl = DBConfig.JDBC_URL;
//        String username = DBConfig.USERNAME;
//        String password = DBConfig.PASSWORD;
//
//
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        int order_id = 1; // Example cart ID to checkout
//        int orderId;
//
//        try {
//            // Establish connection
//            conn = DriverManager.getConnection(jdbcUrl, username, password);
//            conn.setAutoCommit(false); // Start transaction
//
//            // Insert a new order
//            String insertOrderSQL = "INSERT INTO orders (customer_id, order_date) " +
//                    "SELECT user_id, CURRENT_DATE FROM orders WHERE order_id = ?";
//            ps = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, order_id);
//            ps.executeUpdate();
//
//            // Get the generated order_id
//            rs = ps.getGeneratedKeys();
//            if (rs.next()) {
//                orderId = rs.getInt(1);
//            } else {
//                throw new SQLException("Creating order failed, no ID obtained.");
//            }
//
//            // Transfer cart items to orderproducts
//            String transferItemsSQL = "INSERT INTO orderproducts (order_id, product_id, product_qty, payment_id) " +
//                    "SELECT ?, product_id, quantity, NULL FROM cart_items WHERE cart_id = ?";
//            ps = conn.prepareStatement(transferItemsSQL);
//            ps.setInt(1, orderId);
//            ps.setInt(2, cartId);
//            ps.executeUpdate();
//
//            // Clear the cart
//            String deleteCartItemsSQL = "DELETE FROM cart_items WHERE cart_id = ?";
//            ps = conn.prepareStatement(deleteCartItemsSQL);
//            ps.setInt(1, cartId);
//            ps.executeUpdate();
//
//            String deleteCartSQL = "DELETE FROM cart WHERE cart_id = ?";
//            ps = conn.prepareStatement(deleteCartSQL);
//            ps.setInt(1, cartId);
//            ps.executeUpdate();
//
//            conn.commit(); // Commit transaction
//            System.out.println("Checkout complete. Order ID: " + orderId);
//
//        } catch (SQLException e) {
//            if (conn != null) {
//                try {
//                    conn.rollback(); // Rollback transaction on error
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            e.printStackTrace();
//        } finally {
//            // Close resources
//            try {
//                if (rs != null) rs.close();
//                if (ps != null) ps.close();
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static void createOrder(int cust_id, Date date) throws SQLException{

        try {
            //connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            //create a statement
            String sql = "INSERT INTO orders(`customer_id`, `order_date`) VALUES (?,?)";

            preparedStatement = connection.prepareStatement(sql);

            //set parameter
            preparedStatement.setInt(1,cust_id);
            preparedStatement.setDate(2, date);

            // Execute the INSERT statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if any rows were inserted
            if (rowsAffected > 0) {
                System.out.println("Order successfully created!");
            } else {
                System.out.println("No records inserted!");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void insertOrder (int passOrderId, int passProdId, int passQty, int passPaymentId) throws SQLException {

        try {
            //connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            //create a statement
            String sql = "INSERT INTO orderproducts(`order_id`, `product_id`, `product_qty`, `payment_id`) " +
                    "VALUES (?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql);

            //set parameter
            preparedStatement.setInt(1,passOrderId);
            preparedStatement.setInt(2,passProdId);
            preparedStatement.setInt(3,passQty);
            preparedStatement.setInt(4,passPaymentId);

            // Execute the INSERT statement
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if any rows were inserted
            if (rowsAffected > 0) {
                System.out.println("Order item inserted successfully!");
            } else {
                System.out.println("No records inserted!");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static List<OrdersProduct> getOrderProductList(int passOrderId) throws SQLException{
        ordersProductList.clear();

        try{
            //connect to database
            connection = DriverManager.getConnection(jdbcUrl,username,password);

            //create a statement
            String sql = "SELECT \n" +
                    "    op.orderproduct_id,\n" +
                    "    op.order_id,\n" +
                    "    op.product_id,\n" +
                    "    p.product_name,\n" +
                    "    p.product_image,\n" +
                    "    p.price,\n" +
                    "    op.product_qty,\n" +
                    "    (op.product_qty * p.price) AS total\n" +
                    "FROM \n" +
                    "    orderproducts op\n" +
                    "INNER JOIN \n" +
                    "    products p ON op.product_id = p.product_id\n" +
                    "WHERE \n" +
                    "    op.order_id = ?;\n";


            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,passOrderId);

            //execute the query to retrieve data
            resultSet = preparedStatement.executeQuery();

            //print data
            while(resultSet.next()){
                int orderproductId = resultSet.getInt("orderproduct_id");
                int orderId = resultSet.getInt("order_id");
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                String productImage = resultSet.getString("product_image");
                double price = resultSet.getDouble("price");
                int qty = resultSet.getInt("product_qty");
                double total = resultSet.getDouble("total");
                Products product = new Products(productId, productName, productImage, price);
                OrdersProduct products =  new OrdersProduct(orderproductId, orderId, productId, qty, product, total);
                ordersProductList.add(products);
            }

            System.out.println("Retrieve a list of products");
            System.out.println(ordersProductList);

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }


        return ordersProductList;
    }


    //remove order
}