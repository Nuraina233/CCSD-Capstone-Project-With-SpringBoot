package com.example.ecommerce.repository;

import com.example.ecommerce.models.OrderSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderSummaryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<OrderSummary> findAllOrderSummaries() {
        String sql = "SELECT o.id AS orderId, o.customer_id AS customerId, o.order_date AS orderDate, SUM(op.product_quantity * p.total_price) AS totalPrice " +
                "FROM orders o " +
                "JOIN order_product op ON o.id = op.order_id " +
                "JOIN payments p ON o.id = p.order_id " +
                "GROUP BY o.id, o.customer_id, o.order_date";

        return null;

//        return jdbcTemplate.query(sql, (rs, rowNum) ->
//                new OrderSummary(
////                        rs.getLong("orderId"),
//                        rs.getInt("customerId"),
//                        rs.getD("orderDate"),
//                        rs.getDouble("totalPrice")
//                )
//        );
    }
}
