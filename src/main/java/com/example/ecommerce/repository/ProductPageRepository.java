package com.example.ecommerce.repository;

import com.example.ecommerce.models.ProductPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPageRepository extends JpaRepository<ProductPage, Integer> {
}
