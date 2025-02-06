package com.example.ssia.repository;

import com.example.ssia.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:text% AND p.owner=?#{authentication.name}")
    List<Product> findProductByNameContains(String text);
}
