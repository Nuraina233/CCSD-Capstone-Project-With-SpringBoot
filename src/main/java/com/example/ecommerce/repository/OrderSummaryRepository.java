package com.example.ecommerce.repository;

import com.example.ecommerce.models.OrderSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Repository
public class OrderSummaryRepository {

    private static final Logger logger = LoggerFactory.getLogger(OrderSummaryRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<OrderSummary> findAllOrderSummaries() {
        String sql = "SELECT o.order_id AS orderId, o.customer_id AS customerId, o.order_date AS orderDate, " +
                "SUM(op.product_qty * p.total_price) AS totalPrice " +
                "FROM orders o " +
                "JOIN orderproducts op ON o.order_id = op.order_id " +
                "JOIN payments p ON op.payment_id = p.payment_id " +
                "GROUP BY o.order_id, o.customer_id, o.order_date";

        List<OrderSummary> orderSummaries = jdbcTemplate.query(sql, (rs, rowNum) -> {
            OrderSummary orderSummary = new OrderSummary(
                    rs.getLong("orderId"),
                    rs.getLong("customerId"),
                    rs.getDate("orderDate"),
                    rs.getDouble("totalPrice")
            );
            logger.info("Fetched Order Summary: {}", orderSummary);
            return orderSummary;
        });

        logger.info("Total Order Summaries Fetched: {}", orderSummaries.size());
        return orderSummaries;
    }
}
