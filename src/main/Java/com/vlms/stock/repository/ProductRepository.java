package com.vlms.stock.repository;

import com.vlms.stock.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Developers can define methods in repository interfaces with custom query keywords,
    // and Spring Data JPA automatically translates them into appropriate SQL queries.
    List<Product> findAll();
    Product findById(Long id);

    Product findByProductName(String productName);

    List<Product>findAllByProductNameContains(String productName);
    List<Product> findAllByBrandName(String brandName);

    Product updateProductById(Long id,String comments);

}
