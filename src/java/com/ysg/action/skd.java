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
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import com.ysg.data.Account;
import com.opensymphony.xwork2.Action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.ysg.util.Encryption;
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Shows all the parameters sent to the servlet via either
 *  GET or POST. Specially marks parameters that have no values or
 *  multiple values.
 *  


 *  Part of tutorial on servlets and JSP that appears at
 *  http://www.apl.jhu.edu/~hall/java/Servlet-Tutorial/
 *  1999 Marty Hall; may be freely used or adapted.
 */

public class skd extends HttpServlet {
    private Account user;
  
    @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
       System.out.println(request.getSession(true).getAttribute("firstN"));
       String firstN = request.getParameter("firstN");
       String lastN = request.getParameter("lastN");
       String email = request.getParameter("email");
       String phone = request.getParameter("phone");
       String password = null;
        try {
            password = Encryption.generateHashedPassword(request.getParameter("pass"));
        } catch (Exception ex) {
            Logger.getLogger(skd.class.getName()).log(Level.SEVERE, null, ex);
        }
       user = new Account(firstN, lastN, email, password, phone);
//       return Action.SUCCESS;
      }
    

    @Override
  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
