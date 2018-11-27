/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysg.action;

import com.opensymphony.xwork2.Action;
import com.ysg.data.Account;
import com.ysg.data.Bus;
import com.ysg.data.Route;
import com.ysg.data.Seat;
import com.ysg.data.Trip;
import com.ysg.util.Encryption;
import com.ysg.util.MySqlConnector;
import static java.lang.Integer.parseInt;
import java.text.DateFormat;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author tochukwu
 */
public class YsgAdminAction implements YsgAdminActionInt {
    
    private Map<String, String> params = new HashMap<String, String>();
    private HttpServletRequest request;
    private Bus bus;
    private Seat seat;
    private List<Bus> buses;
    private List<Bus> busList;
    private List<Seat> seatList;
    private static final String PASSWORD = "C75607CC43904991C629279C9144E3E2954E9D3B";
    private List<Route> routes;
    private List<Trip> trips;
    
    
    public String login() throws Exception {
        String password  = request.getParameter("pass");
        if (Encryption.verifyUserPassword(password, PASSWORD)){
            this.buses = MySqlConnector.fetchBuses();
            this.routes = MySqlConnector.fetchRoutes();
            this.trips = MySqlConnector.fetchTrips();
            System.out.println("Trip1: " + trips);
            System.out.println("routes1: " + routes);
            System.out.println("buses1: " + buses);
            request.getSession(true).setAttribute("account", "admin");
            request.getSession(true).setAttribute("buses", buses);
            request.getSession(true).setAttribute("routes", routes);
            request.getSession(true).setAttribute("trips", trips);
//            System.out.println("trip 2: "+ this.trips.get(0));
            return Action.SUCCESS;
        }
        return Action.NONE;
        
    }

    
    @Override
    public String addBus() throws Exception {
        String type = request.getParameter("type");
        int capacity = parseInt(request.getParameter("capacity"));
        MySqlConnector.insertBus(type, capacity);
        this.busList = MySqlConnector.fetchBuses();
        request.getSession(true).setAttribute("buses", busList);
        return Action.SUCCESS;
    }
    
    
    public String assignBus() throws Exception{
        this.trips = (List<Trip>) request.getSession(true).getAttribute("trips");
        System.out.println("Trip: " + trips);
        String date = request.getParameter("trip_date");
        String time = request.getParameter("trip_time");
        int price = parseInt(request.getParameter("price"));
        int route = parseInt(request.getParameter("route"));
        int bus = parseInt(request.getParameter("bus"));
        int year = parseInt(date.substring(0, 4))-1900;
        int month = parseInt(date.substring(5, 7))-1;
        int day = parseInt(date.substring(8, 10))+1;
        int hour = parseInt(time.substring(0, 2))+1;
        int min = parseInt(time.substring(3, 5));
        Date trip_date = new Date(year, month, day);
        Time trip_time = new Time(hour, min, 0);
        for (Trip trip:trips){
            if (trip.getDepartureDate().equals(trip_date) && 
                    trip.getDepartureTime().equals(trip_time)){
                return Action.ERROR;
            }
        }
        MySqlConnector.insertTrip(trip_date, trip_time, price, route, bus);
        this.trips =  MySqlConnector.fetchTrips();
        Trip new_trip = trips.get(trips.size()-1);
        for (int i = 1, max = new_trip.getBus().getCapacity(); i<= max;i++){
            MySqlConnector.insertSeat(i, new_trip.getId(), true);
        }
        request.getSession(true).setAttribute("trips", trips);
        return Action.SUCCESS;
    }
    
    @Override
    public String viewBuses() throws Exception {
        
        return Action.SUCCESS;
    }

    @Override
    public String viewSeats() throws Exception {
        return Action.SUCCESS;
    }

    @Override
    public String addSeat() throws Exception {
        return Action.SUCCESS;
    }

    @Override
    public void addParam(String string, String string1) {
       params.putIfAbsent(string, string1);
    }

    @Override
    public void setParams(Map<String, String> map) {
        this.params = map;
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

}
