package com.praveenraam.SpringBoot.controller;

import com.praveenraam.SpringBoot.model.Hostel;
import com.praveenraam.SpringBoot.repository.HostelRepository;
import com.praveenraam.SpringBoot.service.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HostelController {

    @Autowired
    private HostelService hostelService;

    @GetMapping("/admin/getAllHostel")
    public List<Hostel> getAllHostel(){
        return hostelService.getAllHostel();
    }

    @GetMapping("/admin/hostel/{id}")
    public Hostel getHostelById(@PathVariable Long id){
        return hostelService.getHostelById(id);
    }

    @PostMapping("/admin/createHostel")
    public Hostel createHostel(@RequestBody Hostel hostel){
        return hostelService.createHostel(hostel);
    }

    @PostMapping("/admin/updateHostel/{id}")
    public Hostel updateHostel(@RequestBody Hostel hostel, @PathVariable Long id){
        return hostelService.updateHostel(id,hostel);
    }

    @DeleteMapping("/admin/deleteHostel/{id}")
    public Hostel deleteRoom(@PathVariable Long id){
        return hostelService.deleteHostel(id);
    }



}
