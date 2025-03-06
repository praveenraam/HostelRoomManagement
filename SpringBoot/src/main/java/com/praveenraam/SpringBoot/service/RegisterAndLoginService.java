package com.praveenraam.SpringBoot.service;

import com.praveenraam.SpringBoot.model.Admin;
import com.praveenraam.SpringBoot.model.Student;
import com.praveenraam.SpringBoot.repository.AdminRepository;
import com.praveenraam.SpringBoot.repository.StudentRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterAndLoginService {

    private StudentRepository studentRepository;
    private AdminRepository adminRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public RegisterAndLoginService(StudentRepository studentRepository, AdminRepository adminRepository){
        this.studentRepository = studentRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Student registerStudent(Student student){
        // Hashing the password
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    public Optional<Student> authenticateStudent(String email, String password){
        Optional<Student> student = studentRepository.findByEmail(email);
        if(student.isPresent() && passwordEncoder.matches(password,student.get().getPassword())){
            return student;
        }
        return Optional.empty();
    }

    public Optional<Admin> authenticateAdmin(String email,String password){

        Optional<Admin> admin = adminRepository.findByEmail(email);
        if(admin.isPresent() && passwordEncoder.matches(password,admin.get().getPassword())){
            return admin;
        }
        if(email.equals("admin@bitsathy.ac.in")) {
            Admin ad = createAdmin(email,password);
            adminRepository.save(ad);
            return admin;
        }
        return Optional.empty();
    }

    public Admin createAdmin(String email, String password){
        if(adminRepository.count() == 0){
            Admin admin = new Admin();
            admin.setEmail(email);
            admin.setPassword(passwordEncoder.encode(password));
            return admin;
        }
        else{
            throw new RuntimeException("Admin registration is closed");
        }
    }

}
