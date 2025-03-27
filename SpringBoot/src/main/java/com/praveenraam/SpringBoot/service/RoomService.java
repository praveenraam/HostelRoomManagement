package com.praveenraam.SpringBoot.service;

import com.praveenraam.SpringBoot.model.Hostel;
import com.praveenraam.SpringBoot.model.Room;
import com.praveenraam.SpringBoot.model.RoomType;
import com.praveenraam.SpringBoot.repository.HostelRepository;
import com.praveenraam.SpringBoot.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HostelRepository hostelRepository;
    @Autowired
    private RoomAllocationService roomAllocationService;

    public Room createRoom(Room room) {

        if (room.getHostel() == null) {
            throw new IllegalArgumentException("Hostel cannot be null");
        }

        // Fetch the latest hostel record
        Hostel hostel = hostelRepository.findById(room.getHostel().getId())
                .orElseThrow(() -> new IllegalArgumentException("Hostel not found"));

        RoomType roomType = room.getRoomType();

        if (roomType == RoomType.ONE_CART) {
            hostel.setOneCart(hostel.getOneCart() + 1);
            System.out.println(1);
        } else if (roomType == RoomType.TWO_CART) {
            hostel.setTwoCart(hostel.getTwoCart() + 1);
            System.out.println(2);
        } else if (roomType == RoomType.FOUR_CART) {
            hostel.setFourCart(hostel.getFourCart() + 1);
            System.out.println(3);
        } else if (roomType == RoomType.FIVE_CART) {
            hostel.setFiveCart(hostel.getFiveCart() + 1);
            System.out.println(4);
        }

        hostelRepository.save(hostel);
        return roomRepository.save(room);
    }

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }
    public Room getRoomById(Long id){
        return roomRepository.findById(id).get();
    }
    public List<Room> getAllRoomsInHostel(Long hostelId){
        return roomRepository.findAll().stream().filter(
                room -> room.getHostel().getId().equals(hostelId)
        ).toList();
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

        Hostel hostel = room.getHostel();
        RoomType roomType = room.getRoomType();

        switch (roomType) {
            case ONE_CART -> hostel.setOneCart(hostel.getOneCart() - 1);
            case TWO_CART -> hostel.setTwoCart(hostel.getTwoCart() - 1);
            case FOUR_CART -> hostel.setFourCart(hostel.getFourCart() - 1);
            case FIVE_CART -> hostel.setFiveCart(hostel.getFiveCart() - 1);
        }
        roomAllocationService.deleteStudentFromRoom(id);
        roomRepository.deleteById(id);
        hostelRepository.save(hostel);

        return true;
    }

}
