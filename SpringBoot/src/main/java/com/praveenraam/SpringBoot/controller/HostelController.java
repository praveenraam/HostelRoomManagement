package com.praveenraam.SpringBoot.controller;

import com.praveenraam.SpringBoot.model.Hostel;
import com.praveenraam.SpringBoot.repository.HostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HostelController {

    @Autowired
    private HostelRepository hostelRepository;

    @PostMapping("/admin/createHostel")
    public Hostel createHostel(@RequestBody Hostel hostel){
        return hostelRepository.save(hostel);
    }

}
