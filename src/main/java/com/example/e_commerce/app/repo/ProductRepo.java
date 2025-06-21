package com.example.e_commerce.app.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.e_commerce.app.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    public Product findByName(String name);
}
