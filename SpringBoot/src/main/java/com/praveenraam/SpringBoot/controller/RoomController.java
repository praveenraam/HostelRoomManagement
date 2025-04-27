package com.praveenraam.SpringBoot.controller;

import com.praveenraam.SpringBoot.model.Room;
import com.praveenraam.SpringBoot.service.RoomService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/admin/getAllRoom")
    public ResponseEntity<List<Room>> getAllRoom(){
        return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);
    }

    @GetMapping("/admin/getAllRoomPaginated")
    public ResponseEntity<Page<Room>> getAllRoomPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Room> rooms = roomService.getAllRoomPaginated(pageable);

        if(rooms.isEmpty()) return new ResponseEntity<>(rooms,HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(rooms,HttpStatus.OK);
    }

    @GetMapping("/admin/room/{id}")
    public ResponseEntity<Room> getRoomWithId(@PathVariable Long id){
        Room room = roomService.getRoomById(id);
        if(room == null) return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(room,HttpStatus.OK);
    }

    @PostMapping("/admin/createRoom")
    public ResponseEntity<Room> roomCreate(@RequestBody Room room){
        return new ResponseEntity<>(roomService.createRoom(room),HttpStatus.CREATED);
    }

    @PostMapping("/admin/updateRoom/{id}")
    public ResponseEntity<Room> roomUpdate(@PathVariable Long id,@RequestBody Room room){
        if(roomService.updateRoom(id,room)){
            Room updatedRoom = roomService.getRoomById(id);
            return new ResponseEntity<>(updatedRoom,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/admin/deleteRoom/{id}")
    public ResponseEntity<Boolean> deleteRoom(@PathVariable Long id){
        if(roomService.deleteRoom(id)) return new ResponseEntity<>(true,HttpStatus.OK);
        return new ResponseEntity<>(false,HttpStatus.NO_CONTENT);
    }

}
