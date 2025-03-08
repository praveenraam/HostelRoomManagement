package com.praveenraam.SpringBoot.service;

import com.praveenraam.SpringBoot.model.Room;
import com.praveenraam.SpringBoot.model.RoomStudent;
import com.praveenraam.SpringBoot.model.Student;
import com.praveenraam.SpringBoot.repository.RoomStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomStudentService {

    @Autowired
    private RoomStudentRepository roomStudentRepository;

    public Optional<RoomStudent> getAssignedRoom(Long studentId){
        return roomStudentRepository.findByStudentId(studentId);
    }
    public List<Student> getStudentsInRoom(Long roomId){
        List<RoomStudent> roomStudents = roomStudentRepository.findByRoomId(roomId);
        return roomStudents.stream().map(RoomStudent::getStudent).collect(Collectors.toList());
    }

    public RoomStudent assignedStudentToRoom(Room room, Student student){
        Optional<RoomStudent> existingAssigned = roomStudentRepository.findByStudentId(student.getId());

        existingAssigned.ifPresent(roomStudent -> roomStudentRepository.delete(roomStudent));

        RoomStudent roomStudent = new RoomStudent(room,student);
        return roomStudentRepository.save(roomStudent);
    }

    public void romoveStudentFromRoom(Long studentId){
        Optional<RoomStudent> existingAccount = roomStudentRepository.findByStudentId(studentId);
        if(existingAccount.isPresent()) roomStudentRepository.delete(existingAccount.get());
        else throw new RuntimeException("Student not existing");
    }

    public void removeStudentsFromRoom(Long roomId){
        List<RoomStudent> roomStudents = roomStudentRepository.findByRoomId(roomId);

        if(roomStudents.isEmpty()){
            throw new RuntimeException("No students found on the room");
        }

        roomStudentRepository.deleteAll(roomStudents);
    }

}
