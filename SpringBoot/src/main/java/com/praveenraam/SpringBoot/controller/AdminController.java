package com.praveenraam.SpringBoot.controller;

import com.praveenraam.SpringBoot.model.Admin;
import com.praveenraam.SpringBoot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Admin>> findAllAdmin(){
        return new ResponseEntity<>(adminService.findAllAdmin(), HttpStatus.OK);
    }

    @PostMapping("/adminLogin")
    public ResponseEntity<String> adminLogin(@RequestBody Admin admin){
        String token = adminService.verify(admin);
        if(token.isEmpty()) return new ResponseEntity<>("Enter the correct credentials",HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(token,HttpStatus.OK);
    }

}
