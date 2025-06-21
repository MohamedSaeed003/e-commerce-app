package com.example.e_commerce.app.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.e_commerce.app.entity.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {

    public Admin findByEmail(String email);
}
