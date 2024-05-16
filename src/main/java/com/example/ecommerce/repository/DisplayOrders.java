package com.example.ecommerce.repository;

import com.example.ecommerce.models.OrdersProduct;
import com.example.ecommerce.models.Products;
import dbconfig.DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import static com.example.ecommerce.repository.ProductRepository.getEditProduct;

public class DisplayOrders {
    public static void main(String[] args) throws SQLException{
        getOrdersList();
    }

    public static List<OrdersProduct> getOrdersList() throws SQLException{
        List<OrdersProduct> ordersProductList = new ArrayList<>();

        String jdbcUrl = DBConfig.JDBC_URL;
        String username = DBConfig.USERNAME;
        String password = DBConfig.PASSWORD;

        //Database table and column names
        String tableName = "orderproducts";
        String orderIDColumn = "order_id";
        String productIDColumn = "product_id";
        String productQTYColumn = "product_qty";
        String paymentIDColumn = "payment_id";

        //Initialize
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            //connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            //create a statement
            statement = connection.createStatement();

            //execute the query to retrieve data
            resultSet = statement.executeQuery("SELECT * FROM " + tableName);

            //print the header
            System.out.println("Orders Table: ");

            //print data

            OrdersProduct ordersProduct = null;
            while (resultSet.next()) {
                int order_id = Integer.parseInt(resultSet.getString(orderIDColumn));
                int product_id = Integer.parseInt(resultSet.getString(productIDColumn));
                int product_qty = Integer.parseInt(resultSet.getString(productQTYColumn));
                int payment_id = Integer.parseInt(resultSet.getString(paymentIDColumn));

                Products products = getEditProduct(product_id);

                ordersProduct = new OrdersProduct(order_id, product_id, product_qty, payment_id, products);
                ordersProductList.add(ordersProduct);

                System.out.println(resultSet.getInt("order_id") + "\t" +
                        resultSet.getInt("product_id") + "\t" +
                        resultSet.getInt("product_qty") + "\t" +
                        resultSet.getInt("payment_id"));

//                System.out.println("ID:" +ordersProduct.getProducts().getProdId());
//                System.out.println("NAME:" +ordersProduct.getProducts().getProdName());
            }



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
}