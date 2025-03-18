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
    public Room getRoomById(Long id){
        return roomRepository.findById(id).get();
    }

    public boolean updateRoom(Long id,Room updateRoom){
        Room room = this.getRoomById(id);

        if(room == null) return false;

        room.setRoomNo(updateRoom.getRoomNo());
        room.setRoomType(updateRoom.getRoomType());
        room.setTotalBeds(updateRoom.getTotalBeds());
        room.setAvailableBeds(updateRoom.getAvailableBeds());
        room.setHostel(updateRoom.getHostel());

        roomRepository.save(room);
        return true;
    }

    public boolean deleteRoom(Long id){
        Room room = this.getRoomById(id);
        if(room == null) return false;
        roomRepository.deleteById(id);

        return true;
    }

}
