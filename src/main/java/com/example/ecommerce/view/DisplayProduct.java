package com.example.ecommerce.view;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dbconfig.DBConfig;
import com.example.ecommerce.models.Products;

public class DisplayProduct {
    public static void main(String[] args) throws SQLException {
        getProductList();
    }

    public static List<Products> getProductList() throws SQLException {
        List<Products> productsList = new ArrayList<>();

        String jdbcUrl = DBConfig.JDBC_URL;
        String username = DBConfig.USERNAME;
        String password = DBConfig.PASSWORD;

        //Database table and column names
        String tableName = "products";
        String idColumn = "product_id";
        String nameColumn = "product_name";
        String imageColumn = "product_image";
        String priceColumn = "price";
        String descColumn = "description";
        String colorColumn = "color";
        String categoryColumn = "category";
        String genderColumn = "gender";

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
                String name = resultSet.getString(nameColumn);
                String image = resultSet.getString(imageColumn);
                double price = Double.parseDouble(resultSet.getString(priceColumn));
                String desc = resultSet.getString(descColumn);
                String color = resultSet.getString(colorColumn);
                String category = resultSet.getString(categoryColumn);
                String gender = resultSet.getString(genderColumn);
                Products products =  new Products(id, name, image, price, desc, color, category, gender);
                productsList.add(products);

                System.out.println("Product Id " +id +", Name: " +name);
            }

            System.out.println(productsList);

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
        return productsList;
    }
}
