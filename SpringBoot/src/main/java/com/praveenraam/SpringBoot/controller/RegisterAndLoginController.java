package com.praveenraam.SpringBoot.controller;

import com.praveenraam.SpringBoot.model.Admin;
import com.praveenraam.SpringBoot.model.Student;
import com.praveenraam.SpringBoot.service.RegisterAndLoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class RegisterAndLoginController {

    private RegisterAndLoginService registerAndLoginService;

    public RegisterAndLoginController(RegisterAndLoginService registerAndLoginService){
        this.registerAndLoginService = registerAndLoginService;
    }

    @PostMapping("/student/register")
    public ResponseEntity<String> registerStudent(@Valid @RequestBody Student student){
        registerAndLoginService.registerStudent(student);
        return ResponseEntity.ok("Account created successfully");
    }

    @PostMapping("/student/login")
    public ResponseEntity<String> loginStudent(@RequestBody Student student){
        Optional<Student> authStudent = registerAndLoginService.authenticateStudent(student.getEmail(),student.getPassword());

        if(authStudent.isPresent()) return ResponseEntity.ok("Login successful!");
        return ResponseEntity.status(401).body("Invalid email or password");
    }

    @PostMapping("/admin/login")
    public ResponseEntity<String> loginAdmin(@RequestBody Admin admin){
        Optional<Admin> authAdmin = registerAndLoginService.authenticateAdmin(admin.getEmail(), admin.getPassword());

        if(authAdmin.isPresent()){
            return ResponseEntity.ok("Admin login successfully");
        }
        return ResponseEntity.status(401).body("Invalid password or password");
    }

}
