package com.praveenraam.SpringBoot.repository;

import com.praveenraam.SpringBoot.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.id IN (SELECT rs.student.id FROM RoomStudent rs)")
    List<Student> findStudentsWithRoom();

    @Query("SELECT s FROM Student s WHERE s.id NOT IN (SELECT rs.student.id FROM RoomStudent rs)")
    List<Student> findStudentsWithoutRoom();


}
