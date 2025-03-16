package com.praveenraam.SpringBoot.controller;

import com.praveenraam.SpringBoot.model.Admin;
import com.praveenraam.SpringBoot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/allAdmins")
    public List<Admin> findAllAdmin(){
        return adminService.findAllAdmin();
    }

    @PostMapping("/adminLogin")
    public String adminLogin(@RequestBody Admin admin){
        return adminService.verify(admin);
    }

}
