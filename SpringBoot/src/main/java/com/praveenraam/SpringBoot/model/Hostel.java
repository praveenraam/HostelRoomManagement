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
    private int threeCart;
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
        this.totalRooms = totalRooms;
        this.oneCart = oneCart;
        this.twoCart = twoCart;
        this.threeCart = threeCart;
        this.fourCart = fourCart;
        this.fiveCart = fiveCart;
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
        this.oneCart = oneCart;
    }

    public int getTwoCart() {
        return twoCart;
    }

    public void setTwoCart(int twoCart) {
        this.twoCart = twoCart;
    }

    public int getThreeCart() {
        return threeCart;
    }

    public void setThreeCart(int threeCart) {
        this.threeCart = threeCart;
    }

    public int getFourCart() {
        return fourCart;
    }

    public void setFourCart(int fourCart) {
        this.fourCart = fourCart;
    }

    public int getFiveCart() {
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

    public void decreaseVacancy(int beds){
        if (totalVacancy >= beds) {
            totalVacancy -= beds;
        }
    }
    public void increaseVacancy(int beds){
        this.totalVacancy+=beds;
    }

}
