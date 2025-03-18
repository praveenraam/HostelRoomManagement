package com.praveenraam.SpringBoot.controller;

import com.praveenraam.SpringBoot.model.Student;
import com.praveenraam.SpringBoot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Student>> findAllStudents(){
        return new ResponseEntity<>(studentService.findAllStudents(), HttpStatus.OK);
    }

    @PostMapping("/studentRegister")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentService.studentRegister(student),HttpStatus.OK);
    }

    @PostMapping("/studentLogin")
    public ResponseEntity<String> studentLogin(@RequestBody Student student){

        String token = studentService.verify(student);

        if(token.isEmpty()) return new ResponseEntity<>("Enter the correct credentials",HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(token,HttpStatus.OK);
    }

}
