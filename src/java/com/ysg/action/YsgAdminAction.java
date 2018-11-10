/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysg.action;

import com.opensymphony.xwork2.Action;
import com.ysg.data.Account;
import com.ysg.data.Bus;
import com.ysg.data.Seat;
import com.ysg.util.Encryption;
import com.ysg.util.MySqlConnector;
import static java.lang.Integer.parseInt;
import java.util.Date;
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
    private List<Bus> busList;
    private List<Seat> seatList;
    private static final String PASSWORD = "C75607CC43904991C629279C9144E3E2954E9D3B";
    
    
    public String login() throws Exception {
        String password  = request.getParameter("pass");
        if (Encryption.verifyUserPassword(password, PASSWORD)){
            request.getSession(true).setAttribute("account", "admin");
            return Action.SUCCESS;
        }
        return Action.NONE;
        
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
    public String addBus() throws Exception {
        int route = parseInt(request.getParameter("route"));
        String type = request.getParameter("type");
        int capacity = parseInt(request.getParameter("capacity"));
        int price = parseInt(request.getParameter("price"));
        MySqlConnector.insertBus(type, capacity, capacity);
        this.busList = MySqlConnector.fetchBuses();
        Bus newbus = busList.get(busList.size()-1);
        
        for (int i = 1, max = newbus.getCapacity(); i<= max;i++){
            MySqlConnector.insertSeat(i, newbus.getID(), price, true);
        }
        seatList = MySqlConnector.fetchSeats();
        request.getSession(true).setAttribute("seats", seatList);
        request.getSession(true).setAttribute("buses", busList);
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
