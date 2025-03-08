package com.praveenraam.SpringBoot.repository;

import com.praveenraam.SpringBoot.model.RoomStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomStudentRepository extends JpaRepository<RoomStudent,Long> {
    List<RoomStudent> findByRoomId(Long roomId);
    Optional<RoomStudent> findByStudentId(Long studentId);
}
