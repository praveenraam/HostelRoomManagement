package com.praveenraam.SpringBoot.service;

import com.praveenraam.SpringBoot.model.Student;
import com.praveenraam.SpringBoot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    public Student studentRegister(Student student){
        student.setPassword(encoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    public String verify(Student student){
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(student.getEmail(),student.getPassword()));

        if(authentication.isAuthenticated()) return jwtService.generateToken(student.getEmail());
        return "Not successful, check credentials";
    }

}
