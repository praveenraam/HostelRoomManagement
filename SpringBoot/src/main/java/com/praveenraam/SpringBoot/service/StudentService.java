package com.praveenraam.SpringBoot.service;

import com.praveenraam.SpringBoot.model.Student;
import com.praveenraam.SpringBoot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    private AuthenticationManager authManager;
    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    public List<Student> findStudentsWithRoom(){
        List<Student> allStdWithRoom = studentRepository.findStudentsWithRoom();
        return allStdWithRoom;
    }

    public List<Student> findAllStudentsWithoutRoom(){
        List<Student> allStdWithoutRoom = studentRepository.findStudentsWithoutRoom();
        return  allStdWithoutRoom;
    }

    public Student findStudentById(Long id){
        return studentRepository.findById(id).orElse(null);
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

    public Page<Student> findAllStudentsPaginated(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public Page<Student> findStudentsWithRoomPaginated(Pageable pageable) {

        List<Student> list = findStudentsWithRoom();

        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();
        int start = page*size;

        List<Student> paginatedList;

        if(start >= list.size()){
            paginatedList = List.of();
        }
        else {
            int to = Math.min(list.size(),start+size);
            paginatedList = list.subList(start,to);
        }

        return new PageImpl<>(paginatedList,pageable,list.size());
    }

    public Page<Student> findStudentsWithoutRoomPaginated(Pageable pageable){
        List<Student> list = findAllStudentsWithoutRoom();

        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();
        int start = page*size;

        List<Student> paginatedList;

        if(start >= list.size()){
            paginatedList = List.of();
        }
        else {
            int to = Math.min(list.size(),start+size);
            paginatedList = list.subList(start,to);
        }

        return new PageImpl<>(paginatedList,pageable,list.size());
    }

}
