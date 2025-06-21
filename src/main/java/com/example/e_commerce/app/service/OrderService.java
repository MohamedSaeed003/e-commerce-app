package com.example.e_commerce.app.service;

import com.example.e_commerce.app.entity.Order;
import com.example.e_commerce.app.entity.User;
import com.example.e_commerce.app.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public List<Order> getAllOrder(){
        return orderRepo.findAll();
    }

    public Order getOrderById(Long id){
        return orderRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Order with Id "+id+" not found!"));
    }

    public void createOrder(Order order){
        orderRepo.save(order);
    }

    public void updateOrder(Order order){
        orderRepo.findById(order.getId())
                .orElseThrow(()->new RuntimeException("Order with Id "+order.getId()+" not found!"));
        orderRepo.save(order);
    }

    private void deleteOrder(Long id){
        orderRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Order with Id "+id+" not found!"));
        orderRepo.deleteById(id);
    }
    public List<Order> findOrdersByUserId(User user){
        return orderRepo.findByUser(user);
    }
}
