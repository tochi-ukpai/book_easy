/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysg.data;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author tochukwu
 */
public class Trip {
    private int id;
    private Bus bus;
    private Route route;
    private Departure departure_time;
    private double price;
    
    public Trip(){
        
    }
    public Trip(int ID, Bus car, Route rout, Departure time, Double p){
        id = ID;
        bus = car;
        route = rout;
        departure_time = time;
        price = p;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int Id){
        id = Id;
    }
    
    public void setBus(Bus car){
        bus = car;
    }
    
    public Bus getBus(){
        return bus;
    }
    
    public void setRoute(Route rout){
        route = rout;
    }
    
    public String getRoute(){
        return route.getRoute();
    }
    public int getRouteID(){
        return route.getID();
    }
    
    public void setDeparture(Departure time){
        departure_time = time;
    }
    public Date getDepartureDate(){
        return departure_time.getDate();
    }
    public Time getDepartureTime(){
        return departure_time.getTime();
    }
    
    public void setPrice(double p){
        price = p;
    }
    
    public double getPrice(){
        return price;
    }
    
    public String getDepartureDateString(){
        return departure_time.getDate().toString();
    }
    
}
