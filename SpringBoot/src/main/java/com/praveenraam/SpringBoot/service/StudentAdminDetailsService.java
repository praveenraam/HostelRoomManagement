package com.praveenraam.SpringBoot.service;

import com.praveenraam.SpringBoot.model.Admin;
import com.praveenraam.SpringBoot.model.AdminPrinciple;
import com.praveenraam.SpringBoot.model.Student;
import com.praveenraam.SpringBoot.model.StudentPrinciple;
import com.praveenraam.SpringBoot.repository.AdminRepository;
import com.praveenraam.SpringBoot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentAdminDetailsService implements UserDetailsService
{
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Student student = studentRepository.findByEmail(email);
        Admin admin = adminRepository.findByEmail(email);

        if(student == null && admin == null){
            System.out.println("Account not Found");
            throw new UsernameNotFoundException("Account not Found");
        }

        if(admin != null) return new AdminPrinciple(admin);
        return new StudentPrinciple(student);
    }

}
