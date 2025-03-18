package com.praveenraam.SpringBoot.controller;

import com.praveenraam.SpringBoot.model.Hostel;
import com.praveenraam.SpringBoot.repository.HostelRepository;
import com.praveenraam.SpringBoot.service.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HostelController {

    @Autowired
    private HostelService hostelService;

    @GetMapping("/admin/getAllHostel")
    public ResponseEntity<List<Hostel>> getAllHostel(){
        return new ResponseEntity<>(hostelService.getAllHostel(), HttpStatus.OK);
    }

    @GetMapping("/admin/hostel/{id}")
    public ResponseEntity<Hostel> getHostelById(@PathVariable Long id){
        Hostel hostel = hostelService.getHostelById(id);
        if(hostel == null) return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(hostel,HttpStatus.OK);
    }

    @PostMapping("/admin/createHostel")
    public ResponseEntity<Hostel> createHostel(@RequestBody Hostel hostel){
        return new ResponseEntity<>(hostelService.createHostel(hostel),HttpStatus.CREATED);
    }

    @PostMapping("/admin/updateHostel/{id}")
    public ResponseEntity<Hostel> updateHostel(@RequestBody Hostel hostel, @PathVariable Long id){
        if(hostelService.updateHostel(id,hostel)){
            Hostel updatedHostel = hostelService.getHostelById(id);
            return new ResponseEntity<>(updatedHostel,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/admin/deleteHostel/{id}")
    public ResponseEntity<Boolean> deleteHostel(@PathVariable Long id){
        if(hostelService.deleteHostel(id)) return new ResponseEntity<>(true,HttpStatus.OK);
        return new ResponseEntity<>(false,HttpStatus.NO_CONTENT);
    }

}
