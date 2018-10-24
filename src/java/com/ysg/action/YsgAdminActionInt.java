/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysg.action;

/**
 *
 * @author tochukwu
 */

import com.opensymphony.xwork2.config.entities.Parameterizable;
import org.apache.struts2.interceptor.ServletRequestAware;


public interface YsgAdminActionInt extends Parameterizable, ServletRequestAware {
    public String viewBuses() throws Exception;
    
    public String viewSeats() throws Exception;
    
    public String addSeat() throws Exception;
    
    public String addBus() throws Exception;

}




