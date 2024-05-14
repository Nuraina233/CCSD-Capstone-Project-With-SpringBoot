package com.example.ecommerce.view;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.ecommerce.models.Payments;
import com.example.ecommerce.models.Reviews;
import dbconfig.DBConfig;

public class DisplayReview {
    public static void main(String[] args) throws SQLException {
        getReviewsList();
    }

    public static List<Reviews> getReviewsList() throws SQLException {
        List<Reviews> reviewsList = new ArrayList<>();

        String jdbcUrl = DBConfig.JDBC_URL;
        String username = DBConfig.USERNAME;
        String password = DBConfig.PASSWORD;

        //Database table and column names
        String tableName = "reviews";
        String reviewIDColumn = "review_id";
        String ratingColumn = "rating";
        String customerIDColumn = "customer_id";
        String productIDColumn = "product_id";
        String reviewDateColumn = "review_date";
        String commentColumn = "comment";

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
            System.out.println("Review Table: ");

            //print data

            while(resultSet.next()){
                int review_id = Integer.parseInt(resultSet.getString(reviewIDColumn));
                int customer_id = Integer.parseInt(resultSet.getString(customerIDColumn));
                int product_id = Integer.parseInt(resultSet.getString(productIDColumn));
                int rating = Integer.parseInt(resultSet.getString(ratingColumn));
                String comment = resultSet.getString(commentColumn);;
                Reviews reviews = new Reviews(review_id,  rating, comment, customer_id, product_id);
                reviewsList.add(reviews);

                System.out.println(resultSet.getInt("review_id") +  "\t" +
                        resultSet.getInt("customer_id") + "\t" +
                        resultSet.getInt("product_id") + "\t" +
                        resultSet.getInt("rating") + "\t" +
                        resultSet.getString("comment"));
            }

            System.out.println(reviewsList);

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
        return reviewsList;
    }
}
