/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysg.action;
import com.ysg.data.Account;
import com.opensymphony.xwork2.Action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.ysg.util.Encryption;
import com.ysg.util.MySqlConnector;
import static java.lang.Integer.parseInt;
/**
 *
 * @author tochukwu
 */
public class YsgUserAction implements YsgUserActionInt {
    private Map<String, String> params = new HashMap<String, String>();
    private Account user ;
    private HttpServletRequest request;
    @Override
    public String registerUser() throws Exception{
//       System.out.println(request.getSession(true).getAttribute("firstN"));
       String firstN = request.getParameter("firstN");
       String lastN = request.getParameter("lastN");
       String email = request.getParameter("email");
       String phone = request.getParameter("phone");
        System.out.println(phone);
       String password  = Encryption.generateHashedPassword(request.getParameter("pass"));
       List<String> emails = MySqlConnector.getEmails();
       for (String emailA : emails){
           if (emailA.equalsIgnoreCase(email)){
               return Action.ERROR;
           }
       }
       this.user = new Account(firstN, lastN, email, password, phone);
       System.out.println(user.getPassword());
       MySqlConnector.insertUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), user.getPassword());
       request.getSession(true).setAttribute("account", user);
       return Action.SUCCESS;
    }
    
    @Override
    public String signInUser() throws Exception{
        String errorMessage = "Wrong email address or password!";
        this.user = (Account) request.getSession().getAttribute("account");
        request.getSession(true).setAttribute("error", false);
        request.getSession().setAttribute("errorMessage", errorMessage);
        String email = request.getParameter("email");
        String password  = request.getParameter("pass");
        this.user = MySqlConnector.getUser(email);
        if (this.user != null){
            if (Encryption.verifyUserPassword(password, user.getPassword())){
                request.getSession(true).setAttribute("account", user);
                request.getSession(true).setAttribute("error", false);                
                if ("admin@YSG.com".equalsIgnoreCase(user.getEmail())){
                    return Action.LOGIN;
                }else{
                    return Action.SUCCESS;
                }
            }
        }
        request.getSession().setAttribute("error", true);
        return Action.ERROR;
        
    }
    
    public String logOut() throws Exception {
        if (request.getSession().getAttribute("account") != null) {
            request.getSession().setAttribute("account", null);
            return Action.SUCCESS;
        }
        
        return Action.ERROR;
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
