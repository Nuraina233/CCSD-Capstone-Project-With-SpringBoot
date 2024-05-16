package com.example.ecommerce.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dbconfig.DBConfig;
import com.example.ecommerce.models.Customers;

public class DisplayCustomer {
//    public static void main(String[] args) throws SQLException {
//        getCustomersList();
//    }

    public static List<Customers> getCustomersList() throws SQLException {
        List<Customers> customersList = new ArrayList<>();

        String jdbcUrl = DBConfig.JDBC_URL;
        String username = DBConfig.USERNAME;
        String password = DBConfig.PASSWORD;

        //Database table and column names
        String tableName = "customer";
        String idColumn = "customer_id";
        String fNameColumn = "first_name";
        String lNameColumn = "last_name";
        String emailColumn = "email";
        String addressColumn = "address";
        String phoneColumn = "phone";
        String passwordColumn = "password";

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
            System.out.println("Customer Table: ");

            //print data

            while(resultSet.next()){
                int id = Integer.parseInt(resultSet.getString(idColumn));
                String fName = resultSet.getString(fNameColumn);
                String lName = resultSet.getString(lNameColumn);
                String email = resultSet.getString(emailColumn);
                String address = resultSet.getString(addressColumn);
                String phone = resultSet.getString(phoneColumn);
                String pword = resultSet.getString(passwordColumn);
                Customers customers =  new Customers(id, fName, lName,email, address, phone, pword);
                customersList.add(customers);

                System.out.println("First Name: " +fName +", Last Name: " +lName);
            }

            System.out.println(customersList);

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
        return customersList;
    }
}
