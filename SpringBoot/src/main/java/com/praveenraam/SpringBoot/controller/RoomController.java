package com.praveenraam.SpringBoot.controller;

import com.praveenraam.SpringBoot.model.Room;
import com.praveenraam.SpringBoot.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/admin/getAllRoom")
    public List<Room> getAllRoom(){
        return roomService.getAllRooms();
    }

    @GetMapping("/admin/room/{id}")
    public Room getRoomWithId(@PathVariable Long id){
        return roomService.getRoomById(id);
    }

    @PostMapping("/admin/createRoom")
    public Room roomCreate(@RequestBody Room room){
        return roomService.createRoom(room);
    }

    @PostMapping("/admin/updateRoom/{id}")
    public Room roomUpdate(@PathVariable Long id,@RequestBody Room room){
        return roomService.updateRoom(id,room);
    }

    @DeleteMapping("/admin/deleteRoom")
    public void deleteRoom(@RequestBody Long id){
        roomService.deleteRoom(id);
    }

}
