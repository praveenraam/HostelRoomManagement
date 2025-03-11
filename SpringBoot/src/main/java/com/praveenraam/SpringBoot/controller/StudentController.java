package com.praveenraam.SpringBoot.controller;

import com.praveenraam.SpringBoot.model.Student;
import com.praveenraam.SpringBoot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/allStudents")
    public List<Student> findAllStudents(){
        return studentService.findAllStudents();
    }

    @PostMapping("/register")
    public Student registerStudent(@RequestBody Student student){
        return studentService.studentRegister(student);
    }

}
