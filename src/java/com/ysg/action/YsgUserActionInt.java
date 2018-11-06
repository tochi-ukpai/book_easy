/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysg.action;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.config.entities.Parameterizable;
import org.apache.struts2.interceptor.ServletRequestAware;
/**
 *
 * @author tochukwu
 */
public interface YsgUserActionInt extends Parameterizable, ServletRequestAware {
    public String signInUser() throws Exception;
    
    public String registerUser() throws Exception;
    
    public String viewSeats() throws Exception;
    
}
