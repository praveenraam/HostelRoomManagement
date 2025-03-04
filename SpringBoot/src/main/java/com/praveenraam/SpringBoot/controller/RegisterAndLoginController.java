package com.praveenraam.SpringBoot.controller;

import com.praveenraam.SpringBoot.model.Student;
import com.praveenraam.SpringBoot.service.StudentLoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class RegisterAndLoginController {

    private StudentLoginService studentLoginService;

    public RegisterAndLoginController(StudentLoginService studentLoginService){
        this.studentLoginService = studentLoginService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerStudent(@RequestBody Student student){
        studentLoginService.registerStudent(student);
        return ResponseEntity.ok("Account created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Student student){
        Optional<Student> authStudent = studentLoginService.authenticate(student.getEmail(),student.getPassword());

        if(authStudent.isPresent()) return ResponseEntity.ok("Login successful!");
        return ResponseEntity.status(401).body("Invalid email or password");
    }

}
