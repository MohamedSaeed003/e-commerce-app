package com.example.e_commerce.app.service;

import com.example.e_commerce.app.entity.Product;
import com.example.e_commerce.app.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProduct(){
        return productRepo.findAll();
    }

    public Product getProductById(Long id){
        return productRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Product with Id "+id+" not found!"));
    }

    public void createProduct(Product product){
        productRepo.save(product);
    }

    public void updateProduct(Product product){
        productRepo.findById(product.getId())
                .orElseThrow(()->new RuntimeException("Product with Id "+product.getId()+" not found!"));
        productRepo.save(product);
    }

    public void deleteProduct(Long id){
        productRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Product with Id "+id+" not found!"));
        productRepo.deleteById(id);
    }
    public Product findProductByName(String name){
        return productRepo.findByName(name);
    }
}
