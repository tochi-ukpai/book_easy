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
        return Action.SUCCESS;
    }

    @Override
    public void addParam(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
