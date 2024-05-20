package com.example.ecommerce.repository;

import com.example.ecommerce.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ReviewRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Review> findAll() {
        String sql = "SELECT * FROM reviews";
        return jdbcTemplate.query(sql, this::mapRowToReview);
    }

    public Review save(Review review) {
        String sql = "INSERT INTO reviews (product_id, comment, rating, review_date, customer_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, review.getProductId(), review.getComment(), review.getRating(), review.getReviewDate(), review.getCustomerId());
        return review;
    }

    public Optional<Review> findById(Long reviewId) {
        String sql = "SELECT * FROM reviews WHERE review_id = ?";
        return jdbcTemplate.query(sql, new Object[]{reviewId}, this::mapRowToReview).stream().findFirst();
    }

    private Review mapRowToReview(ResultSet rs, int rowNum) throws SQLException {
        Review review = new Review();
        review.setReviewId(rs.getLong("review_id"));
        review.setProductId(rs.getLong("product_id"));
        review.setComment(rs.getString("comment"));
        review.setRating(rs.getInt("rating"));
        review.setReviewDate(rs.getDate("review_date"));
        review.setCustomerId(rs.getLong("customer_id"));
        return review;
    }
}
