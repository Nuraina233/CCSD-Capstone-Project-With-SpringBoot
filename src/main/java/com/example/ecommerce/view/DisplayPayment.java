package com.example.ecommerce.view;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.ecommerce.models.Payments;
import dbconfig.DBConfig;

public class DisplayPayment {
    public static void main(String[] args) {
        try {
            List<Payments> paymentsList = getPaymentList();
            // Print the header
            System.out.println("Payment Table: ");
            // Print data
            for (Payments payment : paymentsList) {
                System.out.println(payment.getPayment_id() + "\t" +
                        payment.getOrder_id() + "\t" +
                        payment.getPayment_method() + "\t" +
                        payment.getTotal_price());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Payments> getPaymentList() throws SQLException {
        List<Payments> paymentsList = new ArrayList<>();
        String jdbcUrl = DBConfig.JDBC_URL;
        String username = DBConfig.USERNAME;
        String password = DBConfig.PASSWORD;

        // Database table and column names
        String tableName = "payments";
        String paymentIDColumn = "payment_id";
        String payMethodColumn = "payment_method";
        String totalPriceColumn = "total_price";
        String orderIDColumn = "order_id";

        // Initialize
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Connect to database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Create a statement
            statement = connection.createStatement();

            // Execute the query to retrieve data
            resultSet = statement.executeQuery("SELECT * FROM " + tableName);

            // Process data
            while (resultSet.next()) {
                int payment_id = resultSet.getInt(paymentIDColumn);
                int order_id = resultSet.getInt(orderIDColumn);
                String payment_method = resultSet.getString(payMethodColumn);
                double total_price = resultSet.getDouble(totalPriceColumn);

                Payments payment = new Payments(payment_id, order_id, payment_method, total_price);
                paymentsList.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return paymentsList;
    }
}

/*import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.ecommerce.models.Payments;
import dbconfig.DBConfig;

public class DisplayPayment {
    public static void main(String[] args) throws SQLException {
        getPaymentList();
    }

    public static List<Payments> getPaymentList() throws SQLException {
        List<Payments> paymentsList = new ArrayList<>();

        String jdbcUrl = DBConfig.JDBC_URL;
        String username = DBConfig.USERNAME;
        String password = DBConfig.PASSWORD;

        //Database table and column names
        String tableName = "payments";
        String paymentIDColumn = "payment_id";
        String payMethodColum = "payment_method";
        String totalPriceColumn = "total_price";
        String orderIDColumn = "order_id";

        //Initialize
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //connect to database
            connection = DriverManager.getConnection(jdbcUrl,username,password);

            //create a statement
            statement = connection.createStatement();

            //execute the query to retrieve data
            resultSet = statement.executeQuery("SELECT * FROM "+tableName);

            //print the header
            System.out.println("Payment Table: ");

            //print data

            while(resultSet.next()){
                int payment_id = Integer.parseInt(resultSet.getString(paymentIDColumn));
                int order_id = Integer.parseInt(resultSet.getString(orderIDColumn));
                String payment_method = resultSet.getString(payMethodColum);;
                double total_price = Double.parseDouble(resultSet.getString(totalPriceColumn));

                Payments payments =  new Payments (payment_id, order_id, payment_method, total_price);
                paymentsList.add(payments);

                System.out.println(resultSet.getInt("payment_id") +  "\t" +
                        resultSet.getInt("order_id") + "\t" +
                        resultSet.getString("payment_method") + "\t" +
                        resultSet.getDouble("total_price"));
            }

            System.out.println(paymentsList);

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
        return paymentsList;
    }
}*/
