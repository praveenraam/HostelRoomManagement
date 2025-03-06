package com.praveenraam.SpringBoot.service;

import com.praveenraam.SpringBoot.model.Room;
import com.praveenraam.SpringBoot.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public Room createRoom(Room room){
        return roomRepository.save(room);
    }
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }
    public Optional<Room> getRoomById(Long id){
        return roomRepository.findById(id);
    }

    public Room updateRoom(Long id,Room updateRoom){
        Optional<Room> room = this.getRoomById(id);

        if(room.isEmpty()) throw new RuntimeException("No room Found");

        room.get().setRoomNo(updateRoom.getRoomNo());
        room.get().setRoomType(updateRoom.getRoomType());
        room.get().setTotalBeds(updateRoom.getTotalBeds());
        room.get().setAvailableBeds(updateRoom.getAvailableBeds());
        room.get().setHostel(updateRoom.getHostel());

        roomRepository.save(room.get());
        return room.get();
    }

    public Room deleteRoom(Long id){
        Optional<Room> room = this.getRoomById(id);

        if(room.isEmpty()) throw new RuntimeException("No room found to delete");

        roomRepository.deleteById(id);
        return room.get();
    }

}
