package com.example.e_commerce.app.service;

import com.example.e_commerce.app.entity.Admin;
import com.example.e_commerce.app.entity.User;
import com.example.e_commerce.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUser(){
        return userRepo.findAll();
    }

    public User getUserById(Long id){
        return userRepo.findById(id)
                .orElseThrow(()->new RuntimeException("User with Id "+id+" not found!"));
    }

    public User findUserByEmail(String email){
        return userRepo.findByEmail(email);
    }

    public void createUser(User user){
        userRepo.save(user);
    }

    public void updateUser(User user){
        userRepo.findById(user.getId())
                .orElseThrow(()->new RuntimeException("User with Id "+user.getId()+" not found!"));
        userRepo.save(user);
    }

    public void deleteUser(Long id){
        userRepo.findById(id)
                .orElseThrow(()->new RuntimeException("User with Id "+id+" not found!"));
        userRepo.deleteById(id);
    }

    public boolean verifyCredentials(String email, String password){
        User user = userRepo.findByEmail(email);
        if (user.getPassword() == password){
            return true;
        }
        return false;
    }
}
