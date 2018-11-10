/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysg.data;

import java.io.Serializable;

/**
 *
 * @author tochukwu
 */
public class Account implements Serializable {
    private String firstName, lastName, emailAddress, password, phoneNumber, username;

    public Account(){
        
    }
    public Account(String userN, String firstN, String lastN, String email, String pass, String phone){
        username = userN;
        firstName = firstN;
        lastName = lastN;
        emailAddress = email;
        password = pass;
        phoneNumber = phone;
    }
    public void setName(String firstN, String lastN){
        firstName = firstN;
        lastName = lastN;
    }
    
    public String getName(){
        return String.format("%s %s", firstName, lastName);
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setEmail(String email){
        emailAddress = email;
    }
    
    public String getEmail(){
        return emailAddress;
    }
    
    public void setPhone(String pNumber){
        phoneNumber = pNumber;
    }
    
    public String getPhone(){
        return phoneNumber;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String pass){
        password = pass;
    }

    public String getUsername() {
        return username;
    }
    
    public void setUsername(String userN){
        username = userN;
    }
}
