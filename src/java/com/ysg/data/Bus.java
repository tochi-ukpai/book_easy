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
public class Bus {
    private String type;
    private int capacity, busId;

    public Bus(int id, String kind, int cap) {
        type = kind;
        capacity = cap;
        busId = id;
    }
    
    public void setCapacity(int total){
        capacity = total;
    }
    
    public void setType(String ty){
        type = ty;
    }
    
    public int getCapacity(){
        return capacity;
    }
    
    
    public String getType(){
        return type;
    }
    
    public int getID(){
        return busId;
    }
    

}
