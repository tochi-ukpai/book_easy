/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysg.action;
import com.ysg.data.Account;
import com.opensymphony.xwork2.Action;
import com.ysg.data.Bus;
import com.ysg.data.Seat;
import com.ysg.data.Ticket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.ysg.util.Encryption;
import com.ysg.util.MySqlConnector;
import com.ysg.util.ShoppingCart;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
/**
 *
 * @author tochukwu
 */
public class YsgUserAction implements YsgUserActionInt {
    private Map<String, String> params = new HashMap<String, String>();
    private Account user ;
    private HttpServletRequest request;
    private List<Bus> buses;
    private List<Seat> seatList;
    private List<Ticket> ticketList = new ArrayList<>();
    private ShoppingCart cart;
    
    
    @Override
    public String viewSeats() throws Exception {
        this.buses = MySqlConnector.fetchBuses();
        this.seatList = MySqlConnector.fetchSeats();
        this.cart = new ShoppingCart();
        request.getSession(true).setAttribute("buses", buses);
        request.getSession(true).setAttribute("seats", seatList);
        request.getSession(true).setAttribute("cart", cart);
        return Action.SUCCESS;
    }
    
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
                this.buses = MySqlConnector.fetchBuses();
                this.seatList = MySqlConnector.fetchSeats();
                this.cart = new ShoppingCart();
                request.getSession(true).setAttribute("account", user);
                request.getSession(true).setAttribute("error", false);
                request.getSession(true).setAttribute("buses", buses);
                request.getSession(true).setAttribute("seats", seatList);
                request.getSession(true).setAttribute("cart", cart);
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
    
    public String addToCart() throws Exception{
        System.out.println(request.getSession().getAttribute("seats"));
        this.cart = (ShoppingCart) request.getSession().getAttribute("cart");
        System.out.println(this.cart);
        List<Seat> list = (List<Seat>) request.getSession().getAttribute("seats");
        System.out.println(this.cart);
        int seatID = parseInt(request.getParameter("seatID"));
        int busID = parseInt(request.getParameter("busID"));
        for (Seat seat: list){
            if (seat.getBus().getID()==busID && seat.getSeatNumber() == seatID){
                this.cart.add(seat);
                break;
            }
        }
        request.getSession().setAttribute("cart", cart);
        return Action.SUCCESS;
    }
    
    public String removeFromCart() throws Exception{
        System.out.println(request.getSession().getAttribute("seats"));
        this.cart = (ShoppingCart) request.getSession().getAttribute("cart");
        System.out.println(this.cart);
//        List<Seat> list = (List<Seat>) request.getSession().getAttribute("seats");
//        System.out.println(list);
        int seatID = parseInt(request.getParameter("seatID"));
        int busID = parseInt(request.getParameter("busID"));
        for (Seat seat: cart.getItems()){
            if (seat.getBus().getID()==busID && seat.getSeatNumber() == seatID){
                this.cart.remove(seat);
                break;
            }
        }
        request.getSession(true).setAttribute("cart", cart);
        return Action.SUCCESS;
    }
    
    public String addTickets() throws Exception{
        this.cart = (ShoppingCart) request.getSession().getAttribute("cart");
        for (Seat seat: cart.getItems()){
            String firstN = request.getParameter("fName " + seat.getSeatNumber());
            String lastN = request.getParameter("lName " + seat.getSeatNumber());
            String phone = request.getParameter("phone " + seat.getSeatNumber());
            String opt = request.getParameter("options " + seat.getSeatNumber());
            Boolean options = ("true".equals(opt));
            Ticket ticket = new Ticket(firstN, lastN, phone, seat, options);
            System.out.println(ticket.getName() + " " + ticket.getOptions());
            this.ticketList.add(ticket);
        }
        
        request.getSession(true).setAttribute("tickets", this.ticketList);
        
        return Action.SUCCESS;
    }

    @Override
    public void addParam(String string, String string1) {
        this.params.put(string, string1);
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
