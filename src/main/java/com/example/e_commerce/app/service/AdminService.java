package com.example.e_commerce.app.service;

import com.example.e_commerce.app.entity.Admin;
import com.example.e_commerce.app.entity.User;
import com.example.e_commerce.app.repo.AdminRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;

    public List<Admin> getAllAdmin(){
        return adminRepo.findAll();
    }

    public Admin getAdminById(Long id){
        return adminRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Admin with Id "+id+" not found!"));
    }

    public void createAdmin(Admin admin){
        adminRepo.save(admin);
    }

    public void updateAdmin(Admin admin){
        adminRepo.findById(admin.getId())
                .orElseThrow(()->new RuntimeException("Admin with Id "+admin.getId()+" not found!"));
        adminRepo.save(admin);
    }

    public void deleteAdmin(Long id){
        adminRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Admin with Id "+id+" not found!"));
        adminRepo.deleteById(id);
    }

    public boolean verifyCredentials(String email, String password){
        Admin admin = adminRepo.findByEmail(email);
        if (admin.getPassword() == password){
            return true;
        }
        return false;
    }
}
