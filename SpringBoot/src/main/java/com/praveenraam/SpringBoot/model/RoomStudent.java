package com.praveenraam.SpringBoot.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "room_student",uniqueConstraints = {
        @UniqueConstraint(columnNames = "student_id")
})
public class RoomStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id",nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "student_id",nullable = false,unique = true)
    private Student student;

    @Column(nullable = false)
    private LocalDate assignedDate;

    public RoomStudent(){}

    public RoomStudent(Room room, Student student) {
        this.room = room;
        this.student = student;
        assignedDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }
}
