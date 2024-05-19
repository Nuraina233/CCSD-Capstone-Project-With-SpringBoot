package com.example.ecommerce.controller;

import com.example.ecommerce.models.Review;
import com.example.ecommerce.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public String getAllReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "review"; // Returns the review.html template
    }

    @PostMapping("/reviews")
    public String addReview(@RequestParam Long productId, @RequestParam String comment,
                            @RequestParam int rating, @RequestParam Long customerId) {
        Review review = new Review();
        review.setProductId(productId);
        review.setComment(comment);
        review.setRating(rating);
        review.setReviewDate(new Date());
        review.setCustomerId(customerId);

        reviewService.addReview(review);
        return "redirect:/reviews"; // Redirects to the review listing page
    }
}
