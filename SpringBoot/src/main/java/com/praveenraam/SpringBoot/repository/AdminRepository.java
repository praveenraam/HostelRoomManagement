package com.praveenraam.SpringBoot.repository;

import com.praveenraam.SpringBoot.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findByEmail(String email);
}
