/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysg.action;
import com.ysg.data.Account;
import com.opensymphony.xwork2.Action;
import com.ysg.data.Bus;
import com.ysg.data.Route;
import com.ysg.data.Seat;
import com.ysg.data.Ticket;
import com.ysg.data.Trip;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.ysg.util.Encryption;
import com.ysg.util.MySqlConnector;
import com.ysg.util.ShoppingCart;
import static java.lang.Integer.parseInt;
import java.sql.Date;
import java.sql.Time;
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
    private List<Route> routes;
    private List<Trip> trips;
    
    
    @Override
    public String bookSeats() throws Exception {
        this.buses = MySqlConnector.fetchBuses();
//        this.seatList = MySqlConnector.fetchSeats();
        this.cart = new ShoppingCart();
        request.getSession(true).setAttribute("buses", buses);
        request.getSession(true).setAttribute("seats", seatList);
        request.getSession(true).setAttribute("cart", cart);
        return Action.SUCCESS;
    }
    
    @Override
    public String registerUser() throws Exception{
       String username = request.getParameter("username");
       String firstN = request.getParameter("firstN");
       String lastN = request.getParameter("lastN");
       String email = request.getParameter("email");
       String phone = request.getParameter("phone");
       String password  = Encryption.generateHashedPassword(request.getParameter("pass"));
       List<String> emails = MySqlConnector.getEmails();
       for (String emailA : emails){
           if (emailA.equalsIgnoreCase(email)){
               return Action.ERROR;
           }
       }
       this.user = new Account(username, firstN, lastN, email, password, phone);
       System.out.println(user.getPassword());
       MySqlConnector.insertUser(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), user.getPassword());
       return Action.SUCCESS;
    }
    
    @Override
    public String signInUser() throws Exception{
        String errorMessage = "Wrong email address or password!";
        request.getSession(true).setAttribute("error", false);
        request.getSession().setAttribute("errorMessage", errorMessage);
        String email = request.getParameter("email");
        String password  = request.getParameter("pass");
        this.user = MySqlConnector.getUser(email);
        
        if (this.user != null){
            if (Encryption.verifyUserPassword(password, user.getPassword())){
                this.buses = MySqlConnector.fetchBuses();
                this.routes = MySqlConnector.fetchRoutes();
                this.cart = new ShoppingCart();
                request.getSession(true).setAttribute("account", user);
                request.getSession(true).setAttribute("error", false);
                request.getSession(true).setAttribute("buses", buses);
                request.getSession(true).setAttribute("cart", cart);
                request.getSession(true).setAttribute("routes", routes);
                return Action.SUCCESS;
                
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
        this.seatList = (List<Seat>) request.getSession().getAttribute("seats");
        System.out.println(this.cart);
        int seatID = parseInt(request.getParameter("seat_id"));
        System.out.println("seat "+request.getParameter("seat_id"));
        int trip_id = parseInt(request.getParameter("trip_id"));
        System.out.println("trip "+request.getParameter("trip_id"));
        for (Seat seat: seatList){
            if (seat.getTrip().getId() == trip_id && seat.getSeatNumber() == seatID){
                this.cart.add(seat);
                this.seatList.remove(seat);
                break;
            }
        }
        request.getSession().setAttribute("cart", cart);
        request.getSession().setAttribute("seats", seatList);
        return Action.SUCCESS;
    }
    
    public String removeFromCart() throws Exception{
        System.out.println(request.getSession().getAttribute("seats"));
        this.cart = (ShoppingCart) request.getSession().getAttribute("cart");
        this.seatList = (List<Seat>) request.getSession().getAttribute("seats");
        System.out.println(this.cart);
        int seatID = parseInt(request.getParameter("seat_id"));
        int trip_id = parseInt(request.getParameter("trip_id"));
        for (Seat seat: cart.getItems()){
            if (seat.getTrip().getId()== trip_id && seat.getSeatNumber() == seatID){
                this.cart.remove(seat);
                this.seatList.add(seat);
                break;
            }
        }
        request.getSession(true).setAttribute("cart", cart);
        request.getSession().setAttribute("seats", seatList);
        return Action.SUCCESS;
    }
    
    public String addTickets() throws Exception{
        this.cart = (ShoppingCart) request.getSession().getAttribute("cart");
        this.user = (Account) request.getSession().getAttribute("account");
        for (Seat seat: cart.getItems()){
            String firstN = request.getParameter("fName " + seat.getSeatNumber());
            String lastN = request.getParameter("lName " + seat.getSeatNumber());
            String phone = request.getParameter("phone " + seat.getSeatNumber());
            String opt = request.getParameter("options " + seat.getSeatNumber());
            Boolean options = ("true".equals(opt));
            Ticket ticket = new Ticket(firstN, lastN, phone, seat, user, options);
            System.out.println(ticket.getName() + " " + ticket.getOptions());
            this.ticketList.add(ticket);
        }
        
        request.getSession(true).setAttribute("tickets", this.ticketList);
        
        return Action.SUCCESS;
    }
    
    public String getTrips() throws Exception{
        String date = request.getParameter("trip_date");
        int route = parseInt(request.getParameter("route"));
        int year = parseInt(date.substring(0, 4))-1900;
        int month = parseInt(date.substring(5, 7))-1;
        int day = parseInt(date.substring(8, 10));
        Date trip_date = new Date(year, month, day);
        System.out.println("date1: "+ trip_date);
        this.seatList = MySqlConnector.fetchSeats(trip_date, route);
        System.out.println("seats1 : " + seatList.size());
        request.getSession(true).setAttribute("seats", this.seatList);
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
