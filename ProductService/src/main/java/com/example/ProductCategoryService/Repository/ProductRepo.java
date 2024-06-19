package com.example.ProductCategoryService.Repository;

import com.example.ProductCategoryService.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    Page<Product> findByTitleEquals(String query, Pageable pageable);
}
