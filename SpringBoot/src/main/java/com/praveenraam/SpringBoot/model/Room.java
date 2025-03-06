package com.praveenraam.SpringBoot.model;


import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    int roomNo;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomType roomType;
    private int totalBeds;
    @Column(nullable = false)
    private int availableBeds;
    @ManyToOne
    @JoinColumn(name = "hostel_id", nullable = false)
    private Hostel hostel;
    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Student> students = new ArrayList<>();

    public Room(){}

    public Room(int roomNo, RoomType roomType, int totalBeds, int availableBeds, Hostel hostel) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.totalBeds = totalBeds;
        this.availableBeds = availableBeds;
        this.hostel = hostel;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getTotalBeds() {
        return totalBeds;
    }

    public void setTotalBeds(int totalBeds) {
        this.totalBeds = totalBeds;
    }

    public int getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(int availableBeds) {
        this.availableBeds = availableBeds;
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
