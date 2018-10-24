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
    private String route, departureTime, type;
    private int capacity;
    
    public void setCapacity(int total){
        capacity = total;
    }
    
    public void setRoute(String rout){
        route = rout;
    }
    
    public void setType(String ty){
        type = ty;
    }
    
    public void setDepartureTime(String departure){
        departureTime = departure;
    }
    
    public int getCapacity(){
        return capacity;
    }
    
    public String getRoute(){
        return route;
    }
    
    public String getType(){
        return type;
    }
    
    public String getDepartureTime(){
        return departureTime;
    }
}
