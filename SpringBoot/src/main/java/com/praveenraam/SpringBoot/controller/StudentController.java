package com.praveenraam.SpringBoot.controller;

import com.praveenraam.SpringBoot.model.Student;
import com.praveenraam.SpringBoot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/admin/allStudents")
    public ResponseEntity<List<Student>> findAllStudents(){
        return new ResponseEntity<>(studentService.findAllStudents(), HttpStatus.OK);
    }
    @GetMapping("/admin/allStudentsPaginated")
    public ResponseEntity<Page<Student>> findAllStudentsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page,size);
        Page<Student> students = studentService.findAllStudentsPaginated(pageable);

        if(students.isEmpty()) return new ResponseEntity<>(students,HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(students,HttpStatus.OK);
    }

    @GetMapping("/admin/studentsWithRoom")
    public ResponseEntity<List<Student>> findStudentsWithRoom(){
        return new ResponseEntity<>(studentService.findStudentsWithRoom(),HttpStatus.OK);
    }
    @GetMapping("/admin/studentsWithRoomPaginated")
    public ResponseEntity<Page<Student>> findStudentsWithRoomPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page,size);
        Page<Student> students = studentService.findStudentsWithRoomPaginated(pageable);

        if(students.isEmpty()) return new ResponseEntity<>(students,HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(students,HttpStatus.OK);
    }

    @GetMapping("/admin/studentsWithoutRoom")
    public ResponseEntity<List<Student>> findStudentsWithoutRoom(){
        return new ResponseEntity<>(studentService.findAllStudentsWithoutRoom(),HttpStatus.OK);
    }
    @GetMapping("/admin/studentsWithoutRoomPaginated")
    public ResponseEntity<Page<Student>> findStudentsWithoutRoomPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page,size);
        Page<Student> students = studentService.findStudentsWithoutRoomPaginated(pageable);

        if(students.isEmpty()) return new ResponseEntity<>(students,HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(students,HttpStatus.OK);
    }

    @GetMapping("/admin/student/{id}")
    public ResponseEntity<Student> findStudentWithId(@PathVariable Long id){
        return new ResponseEntity<>(studentService.findStudentById(id),HttpStatus.OK);
    }

    @GetMapping("/student/profile")
    public ResponseEntity<Student> findStudent(@RequestBody Long id){
        return new ResponseEntity<>(studentService.findStudentById(id),HttpStatus.OK);
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
