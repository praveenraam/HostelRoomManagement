package com.praveenraam.SpringBoot.service;

import com.praveenraam.SpringBoot.model.Hostel;
import com.praveenraam.SpringBoot.repository.HostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostelService {
    @Autowired
    private HostelRepository hostelRepository;

    public void occupiedRoomInHostel(Long hostelId, int bedCount){
        Optional<Hostel> hostel = hostelRepository.findById(hostelId);
        if(hostel.isPresent()) {
            hostel.get().decreaseVacancy(bedCount);
            hostelRepository.save(hostel.get());
        }
    }

    public void freeRoomInHostel(Long hostelId, int bedCount){
        Optional<Hostel> hostel = hostelRepository.findById(hostelId);
        if(hostel.isPresent()){
            hostel.get().increaseVacancy(bedCount);
            hostelRepository.save(hostel.get());
        }
    }

    public Hostel getHostelById(Long id){
        return hostelRepository.findById(id).get();
    }

    public Hostel createHostel(Hostel hostel) {
        return hostelRepository.save(hostel);
    }

    public Hostel updateHostel(Long id, Hostel updatedHostel){
        Hostel currHostel = this.getHostelById(id);

        if(currHostel == null) throw new RuntimeException("No hostel found");

        currHostel.setName(updatedHostel.getName());
        currHostel.setOneCart(updatedHostel.getOneCart());
        currHostel.setTwoCart(updatedHostel.getTwoCart());
        currHostel.setThreeCart(updatedHostel.getThreeCart());
        currHostel.setFourCart(updatedHostel.getFourCart());
        currHostel.setFiveCart(updatedHostel.getFiveCart());
        currHostel.setTotalVacancy(updatedHostel.getTotalVacancy());
        currHostel.setNoOfFloors(updatedHostel.getNoOfFloors());
        currHostel.setTotalRooms(updatedHostel.getTotalRooms());

        return hostelRepository.save(currHostel);
    }

    public List<Hostel> getAllHostel() {
        return hostelRepository.findAll();
    }

    public void deleteHostel(Long id) {
        Hostel hostel = this.getHostelById(id);
        if(hostel == null) throw new RuntimeException("No hostel found");
        hostelRepository.deleteById(id);
    }
}
