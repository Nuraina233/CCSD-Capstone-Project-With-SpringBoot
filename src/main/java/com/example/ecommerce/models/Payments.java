package com.example.ecommerce.models;

import java.sql.*;

public class Payments {

    private int payment_id;
    private int order_id;
    private String payment_method;
    private double total_price;

    public Payments(int payment_id, int order_id, String payment_method, double total_price) {
        this.payment_id = payment_id;
        this.order_id = order_id;
        this.payment_method = payment_method;
        this.total_price = total_price;
    }

    //getter methods
    public int getPayment_id() {
        return payment_id;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public int getOrder_id() {
        return order_id;
    }

    public double getTotal_price() {
        return total_price;
    }

   /* private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:path_to_your_database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void getPayments() {
        String sql = "SELECT p.payment_id, o.order_id, p.payment_method, o.total_price "
                + "FROM products p INNER JOIN orders o ON p.order_id = o.id";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("payment_id") +  "\t" +
                        rs.getInt("order_id") + "\t" +
                        rs.getString("payment_method") + "\t" +
                        rs.getDouble("total_price"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }*/

    public static void main(String[] args) {
    }
}
