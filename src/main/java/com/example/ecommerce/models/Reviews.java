package com.example.ecommerce.models;

import java.sql.*;

public class Reviews {
    private int review_id;
    private int rating;
    private String comment;
    private int customer_id;
    private int product_id;

    // Constructor
    public Reviews(int review_id, int rating, String comment, int customer_id, int product_id) {
        this.review_id = review_id;
        this.rating = rating;
        this.comment = comment;
        this.customer_id = customer_id;
        this.product_id = product_id;
    }

    // Getters and Setters
    // Setter methods
    public void setReviewId(int review_id) {
        this.review_id = review_id;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCustomerId(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    // Getter methods
    public int getReviewId() {
        return review_id;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public int getCustomerId() {
        return customer_id;
    }

    public int getProductId() {
        return product_id;
    }

    // Method to retrieve review data from the database
    /*public static Reviews getReviewById(int reviewId) {
        Reviews review = null;
        String sql = "SELECT * FROM reviews WHERE review_id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:your_database_url", "username", "password");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reviewId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                review = new Reviews(
                        rs.getInt("review_id"),
                        rs.getInt("rating"),
                        rs.getString("comment"),
                        rs.getInt("customer_id"),
                        rs.getInt("product_id")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return review;
    }

    // Method to save review data to the database
    public void saveReview() {
        String sql = "INSERT INTO reviews (review_id, rating, comment, customer_id, product_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:your_database_url", "username", "password");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, this.review_id);
            pstmt.setInt(2, this.rating);
            pstmt.setString(3, this.comment);
            pstmt.setInt(4, this.customer_id);
            pstmt.setInt(5, this.product_id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }*/

}
