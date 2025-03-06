package com.praveenraam.SpringBoot.service;

import com.praveenraam.SpringBoot.model.Hostel;
import com.praveenraam.SpringBoot.repository.HostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
