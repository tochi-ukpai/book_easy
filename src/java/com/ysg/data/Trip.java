/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysg.data;

import java.util.Date;

/**
 *
 * @author tochukwu
 */
public class Trip {
    private int id;
    private Bus bus;
    private String route;
    private Date departure_time;
    
    public Trip(){
        
    }
    public Trip(int ID, Bus car, String rout, Date time){
        id = ID;
        bus = car;
        route = rout;
        departure_time = time;
    }
    
    public void setBus(Bus car){
        bus = car;
    }
    
    public Bus getBus(){
        return bus;
    }
    
    public void setRoute(String rout){
        route = rout;
    }
    
    public String getRoute(){
        return route;
    }
    
    public void setTime(Date time){
        departure_time = time;
    }
    public Date getTime(){
        return departure_time;
    }
    
    public void setId(int ID){
        id = ID;
    }
    
    public int getId(){
        return id;
    }

    
}
