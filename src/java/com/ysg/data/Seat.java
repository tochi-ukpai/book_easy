/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysg.data;

/**
 *
 * @author tochukwu
 */
public class Seat {
    private int seatNumber;
    private Bus bus;
    private double price;
    private Boolean availability;
    
    public Seat(int num, Bus car, double p, Boolean avail){
        seatNumber = num;
        bus = car;
        price = p;
        availability = avail;
    }
    
    public void setSeatNumber(int num){
        seatNumber = num;
    }
    
    public int getSeatNumber(){
        return seatNumber;
    }
    
    public void setBus(Bus car){
        bus = car;
    }
    
    public Bus getBus(){
        return bus;
    }
    
    public void setPrice(double p){
        price = p;
    }
    
    public double getPrice(){
        return price;
    }
    
    public void setAvailability(Boolean avail){
        availability = avail;
    }
    
    public boolean getAvailability(){
        return availability;
    }
}
