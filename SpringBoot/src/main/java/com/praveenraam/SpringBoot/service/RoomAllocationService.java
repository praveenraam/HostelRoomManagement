package com.praveenraam.SpringBoot.service;

import com.praveenraam.SpringBoot.model.Room;
import com.praveenraam.SpringBoot.model.RoomStudent;
import com.praveenraam.SpringBoot.model.Student;
import com.praveenraam.SpringBoot.repository.RoomRepository;
import com.praveenraam.SpringBoot.repository.RoomStudentRepository;
import com.praveenraam.SpringBoot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomAllocationService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RoomStudentRepository roomStudentRepository;


    public List<Room> getAllVacantRooms(Long hostelId) {
        List<Room> listOfRooms = roomRepository.findAll();
        List<Room> listOfRoomInHostel = listOfRooms.stream().filter(
                room -> room.getHostel().getId().equals(hostelId)
        ).toList();

        List<Room> listOfRoomWithVacancies = new ArrayList<>();

        for(Room room : listOfRoomInHostel){
            if(room.getAvailableBeds() > 0)
                listOfRoomWithVacancies.add(room);
        }

        return listOfRoomWithVacancies;
    }

    public String bookRoom(Long roomId, Long studentId) {

        Room room = roomRepository.findById(roomId).orElse(null);

        if(room == null) return "Room not found";
        if(room.getAvailableBeds() <= 0){
            return "No vacancy in room";
        }

        Optional<RoomStudent> excistingRoomStudent = roomStudentRepository.findByStudentId(studentId);

        if(excistingRoomStudent.isPresent()) this.deleteCurrRoom(studentId);

        Student student = studentRepository.findById(studentId).orElse(null);
        RoomStudent roomStudent = new RoomStudent(room,student);

        roomStudentRepository.save(roomStudent);

        room.setAvailableBeds(room.getAvailableBeds()-1);
        roomRepository.save(room);

        return "Room booked successfully";
    }

    public String deleteCurrRoom(Long studentId){

        RoomStudent roomStudent = roomStudentRepository.findByStudentId(studentId).orElse(null);

        if(roomStudent == null) return "Student current not in any rooms";

        Room room = roomStudent.getRoom();
        room.setAvailableBeds(room.getAvailableBeds()+1);
        roomRepository.save(room);

        roomStudentRepository.delete(roomStudent);

        return "Successfully removed";
    }

}
