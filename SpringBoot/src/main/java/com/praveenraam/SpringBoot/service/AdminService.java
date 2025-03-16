package com.praveenraam.SpringBoot.service;

import com.praveenraam.SpringBoot.model.Admin;
import com.praveenraam.SpringBoot.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public List<Admin> findAllAdmin(){
        return adminRepository.findAll();
    }

    public Admin adminRegister(Admin admin){
        admin.setPassword(encoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public String verify(Admin admin){
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(admin.getEmail(),admin.getPassword()));

        if(authentication.isAuthenticated()) return jwtService.generateToken(admin.getEmail());
        return "Not successful, check credentials";
    }
}
