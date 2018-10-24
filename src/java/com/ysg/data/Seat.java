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
    private int number;
    private Bus bus;
    private double price;
    private Boolean availability;
    
    public void setNumber(int num){
        number = num;
    }
    
    public int getNumber(){
        return number;
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
