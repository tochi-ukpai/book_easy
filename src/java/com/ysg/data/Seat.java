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
    private Trip trip;
    private Boolean availability;
    
    
    public Seat(int num, Trip tp, Boolean avail){
        seatNumber = num;
        trip = tp;
        availability = avail;
    }
    
    public void setSeatNumber(int num){
        seatNumber = num;
    }
    
    public int getSeatNumber(){
        return seatNumber;
    }
    
    public void setTrip(Trip tp){
        trip = tp;
    }
    
    public Trip getTrip(){
        return trip;
    }
    
    public void setAvailability(Boolean avail){
        availability = avail;
    }
    
    public boolean getAvailability(){
        return availability;
    }
}
