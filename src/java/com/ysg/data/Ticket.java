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
    private int id;
    private String firstName, lastName, phoneNumber;
    private Seat ticketSeat;
    private Boolean options;
    
    public Ticket(int ID, String firstN, String lastN, String number, Seat seat, Boolean opt){
        id = ID;
        firstName = firstN;
        lastName = lastN;
        phoneNumber = number;
        ticketSeat = seat;
        options = opt;
    }

    public Ticket(String firstN, String lastN, String number, Seat seat, Boolean opt) {
        firstName = firstN;
        lastName = lastN;
        phoneNumber = number;
        ticketSeat = seat;
        options = opt;
    }
    
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
    
    public String getNumber(){
        return phoneNumber;
    }
}
