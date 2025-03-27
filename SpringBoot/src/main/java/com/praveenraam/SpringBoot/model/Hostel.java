package com.praveenraam.SpringBoot.model;

import jakarta.persistence.*;


@Entity
@Table(name = "hostels")
public class Hostel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private int noOfFloors;
    @Column(nullable = false)
    private int totalRooms;
    @Column(nullable = false)
    private int oneCart;
    @Column(nullable = false)
    private int twoCart;
    @Column(nullable = false)
    private int fourCart;
    @Column(nullable = false)
    private int fiveCart;
    @Column(nullable = false)
    private int totalVacancy;

    public Hostel(){}

    public Hostel(Long id, String name, int noOfFloors, int totalRooms, int oneCart, int twoCart, int threeCart, int fourCart, int fiveCart) {
        this.id = id;
        this.name = name;
        this.noOfFloors = noOfFloors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoOfFloors() {
        return noOfFloors;
    }

    public void setNoOfFloors(int noOfFloors) {
        this.noOfFloors = noOfFloors;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }

    public int getOneCart() {
        return oneCart;
    }

    public void setOneCart(int oneCart) {
        totalVacancy-= this.oneCart;
        totalVacancy+=oneCart;

        this.oneCart = oneCart;
    }

    public int getTwoCart() {
        return twoCart;
    }

    public void setTwoCart(int twoCart) {
        totalVacancy-= this.twoCart*2;
        totalVacancy+=twoCart*2;

        this.twoCart = twoCart;
    }

    public int getFourCart() {
        return fourCart;
    }

    public void setFourCart(int fourCart) {
        totalVacancy-= this.fourCart*4;
        totalVacancy+= fourCart*4;

        this.fourCart = fourCart;
    }

    public int getFiveCart() {
        totalVacancy-= this.fiveCart*5;
        totalVacancy+= fiveCart*5;

        return fiveCart;
    }

    public void setFiveCart(int fiveCart) {
        this.fiveCart = fiveCart;
    }

    public int getTotalVacancy() {
        return totalVacancy;
    }

    public void setTotalVacancy(int totalVacancy) {
        this.totalVacancy = totalVacancy;
    }

}
