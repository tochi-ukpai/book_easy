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
public class Ticket {
    private String firstName, lastName, route;
    private Seat ticketSeat;
    private Boolean options;
    
    public void setName(String firstN, String lastN){
        firstName = firstN;
        lastName = lastN;
    }
    
    public String getName(){
        return String.format("%s %s", firstName, lastName);
    }
     
    public void setSeat(Seat seat){
        ticketSeat = seat;
    } 
    
    public Seat getSeat(){
        return ticketSeat;
    }
    
    public void setOptions(Boolean value){
        options = value;
    }
    
    public Boolean getOptions(){
        return options;
    }
}
