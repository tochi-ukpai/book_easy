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
public class Route {
    private int id;
    private String route;
    
    public Route(int Id, String trip){
        id = Id;
        route = trip;
    }
    
    public int getID(){
        return id;
    }
    
    public String getRoute(){
        return route;
    }
    
    public void setID(int Id){
        id = Id;
    }
    
    public void setRoute(String trip){
        route = trip;
    }
}
