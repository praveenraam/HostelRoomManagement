package com.praveenraam.SpringBoot.repository;

import com.praveenraam.SpringBoot.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByEmail(String email);
}
