package com.praveenraam.SpringBoot.service;

import com.praveenraam.SpringBoot.model.Student;
import com.praveenraam.SpringBoot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentLoginService {

    private StudentRepository studentRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public StudentLoginService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Student registerStudent(Student student){
        // Hashing the password
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    public Optional<Student> authenticate(String email, String password){
        Optional<Student> student = studentRepository.findByEmail(email);
        if(student.isPresent() && passwordEncoder.matches(password,student.get().getPassword())){
            return student;
        }
        return Optional.empty();
    }

}
