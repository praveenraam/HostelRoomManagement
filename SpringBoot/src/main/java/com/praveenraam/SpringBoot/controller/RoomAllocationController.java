package com.praveenraam.SpringBoot.controller;

import com.praveenraam.SpringBoot.model.Hostel;
import com.praveenraam.SpringBoot.model.Room;
import com.praveenraam.SpringBoot.model.RoomStudent;
import com.praveenraam.SpringBoot.service.HostelService;
import com.praveenraam.SpringBoot.service.RoomAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomAllocationController {

    @Autowired
    private HostelService hostelService;
    @Autowired
    private RoomAllocationService roomAllocationService;

    @GetMapping("/student/hostels")
    public ResponseEntity<List<Hostel>> getAllHostels(){
        return new ResponseEntity<>(hostelService.getAllHostel(), HttpStatus.OK);
    }

    @GetMapping({"/student/hostels/{hostelId}/availableRooms","/admin/hostels/{hostelId}/availableRooms"})
    public ResponseEntity<List<Room>> getAllVacantRooms(@PathVariable Long hostelId){
        List<Room> listOfRooms = roomAllocationService.getAllVacantRooms(hostelId);

        if(listOfRooms.isEmpty()) return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(listOfRooms,HttpStatus.OK);
    }

    @PostMapping({"/student/hostels/{hostelId}/availableRooms/book","/admin/studentRoomChange/hostel/{hostelId}/availableRoom/book"})
    public ResponseEntity<String> bookRoom(@RequestBody RoomStudent roomStudent){
        String message = roomAllocationService.bookRoom(roomStudent);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping({"/student/removeRoom/{studentId}","/admin/removeRoom/{studentId}"})
    public ResponseEntity<String> removeRoom(@PathVariable Long studentId){
        return new ResponseEntity<>(roomAllocationService.deleteStudentCurrentRoom(studentId),HttpStatus.OK);
    }
}
