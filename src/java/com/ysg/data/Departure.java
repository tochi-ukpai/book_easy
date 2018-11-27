/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysg.data;

import java.sql.Time;
import java.sql.Date;

/**
 *
 * @author tochukwu
 */
public class Departure {
    private int id;
    private Time time;
    private Date date;
    

    public Departure(int Id, Time t, Date d) {
        id = Id;
        time = t;
        date = d;
    }
    
    public void setId(int Id){
        id = Id;
    }
    
    public int getId(){
        return id;
    }
    
    public Time getTime(){
        return time;
    }
    
    public Date getDate(){
        return date;
    }
    
    public void setTime(Time t){
        time = t;
    }
    
    public void setDate(Date d){
        date = d;
    }
}

