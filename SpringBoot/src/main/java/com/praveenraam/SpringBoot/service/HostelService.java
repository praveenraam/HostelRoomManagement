package com.praveenraam.SpringBoot.service;

import com.praveenraam.SpringBoot.model.Hostel;
import com.praveenraam.SpringBoot.model.Room;
import com.praveenraam.SpringBoot.repository.HostelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HostelService {
    @Autowired
    private HostelRepository hostelRepository;

    @Autowired
    private RoomService roomService;

    public Hostel getHostelById(Long id){
        return hostelRepository.findById(id).get();
    }

    public Hostel createHostel(Hostel hostel) {
        return hostelRepository.save(hostel);
    }

    public boolean updateHostel(Long id, Hostel updatedHostel){
        Hostel currHostel = this.getHostelById(id);

        if(currHostel == null) return false;

        currHostel.setName(updatedHostel.getName());
        currHostel.setOneCart(updatedHostel.getOneCart());
        currHostel.setTwoCart(updatedHostel.getTwoCart());
        currHostel.setFourCart(updatedHostel.getFourCart());
        currHostel.setFiveCart(updatedHostel.getFiveCart());
        currHostel.setTotalVacancy(updatedHostel.getTotalVacancy());
        currHostel.setNoOfFloors(updatedHostel.getNoOfFloors());
        currHostel.setTotalRooms(updatedHostel.getTotalRooms());

        return true;
    }

    public List<Hostel> getAllHostel() {
        return hostelRepository.findAll();
    }

    public boolean deleteHostel(Long id) {
        Hostel hostel = this.getHostelById(id);
        if(hostel == null) return false;

        List<Room> listOfRooms = roomService.getAllRoomsInHostel(id);

        for(Room room : listOfRooms){
            roomService.deleteRoom(room.getId());
        }

        hostelRepository.deleteById(id);
        return true;
    }

    public Page<Hostel> getPaginated(Pageable pageable) {
        return hostelRepository.findAll(pageable);
    }
}
