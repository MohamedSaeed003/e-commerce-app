package com.example.e_commerce.app.repo;


import com.example.e_commerce.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.e_commerce.app.entity.Order;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    public List<Order> findByUser(User user);
}
