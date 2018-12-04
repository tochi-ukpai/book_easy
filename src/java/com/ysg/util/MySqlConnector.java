/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysg.util;

import com.ysg.data.Account;
import com.ysg.data.Bus;
import com.ysg.data.Departure;
import com.ysg.data.Route;
import com.ysg.data.Seat;
import com.ysg.data.Ticket;
import com.ysg.data.Trip;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.ysg.exception.RepositoryException;
import java.sql.Date;
import java.sql.Time;


/**
 *
 * @author tochukwu
 */
public class MySqlConnector {
    private static final String INSERT_USER = "INSERT INTO users (username, first_name, last_name, email, phone, password) VALUES (?,?,?,?,?,?)";
    private static final String INSERT_BUS = "INSERT INTO buses (type, capacity) VALUES (?,?)";
    private static final String INSERT_SEAT = "INSERT INTO seats (seat_no, trip_id, available) VALUES (?,?,?)";
    private static final String INSERT_TIME = "INSERT INTO departure_time(date, time) value(?, ?)";
    private static final String INSERT_TRIP = "INSERT INTO trips(bus_id, route_id, departure_id, price) value(?, ?, ?, ?)";
    private static final String INSERT_TICKETS = "INSERT INTO tickets (passenger_name, passenger_phone, user, seat_no, trip_id, options) values (?,?,?,?,?,?)";
    private static final String INSERT_ROUTE = "INSERT INTO route(route) VALUE (?)";
    private static final String FETCH_EMAILS = "SELECT email FROM users";
    private static final String FETCH_USER = "SELECT * FROM users WHERE email =\"%s\" or username = \"%s\" ";
    private static final String FETCH_BUSES = "SELECT * FROM buses";
    private static final String FETCH_SEATS = "SELECT * FROM seats where available = 1 and trip_id = %d";
    private static final String FETCH_SEAT = "SELECT * FROM seats WHERE seat_no = ? AND trip_id = ?";
    private static final String FETCH_BUS = "SELECT * FROM buses WHERE bus_id = %d";
    private static final String FETCH_ROUTES = "SELECT * FROM route";
    private static final String FETCH_TIME = "SELECT * FROM departure_time WHERE date = ? and time = ?";
    private static final String FETCH_DEPARTURE = "SELECT * FROM departure_time WHERE departure_id = %d";
    private static final String FETCH_TRIPS = "SELECT * FROM trips";
    private static final String FETCH_TRIP = "SELECT * FROM trips where trip_id = %d";
    private static final String FETCH_ROUTE = "SELECT * FROM route WHERE route_id = %d";
    private static final String FETCH_TICKETS = "SELECT * FROM tickets WHERE user = \"%s\"";
    static final String UPDATE_SEAT = "UPDATE seats SET available = ? WHERE seat_no = ? and trip_id = ?";
    private static DataSource ds;
    private static final String DS_NAME = "jdbc/ysgDS";
    private static Context envCtx;
    
    static {
        try {
            Context ctx = new InitialContext();
            envCtx = (Context) ctx.lookup("java:/comp/env");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);
            throw new RepositoryException(ex);
        }
    }
    
    public static void insertRoute(String route) throws SQLException{
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_ROUTE);
            stmt.setString(1, route);
            stmt.execute();
            closeStatement(stmt);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
    private static void insertDeparture(Date date, Time time) throws SQLException{
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_TIME);
            stmt.setDate(1, (java.sql.Date) date);
            stmt.setTime(2, (java.sql.Time) time);
            stmt.execute();
            closeStatement(stmt);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
    
    private static void updateSeat(Seat seat) throws SQLException{
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(UPDATE_SEAT);
            stmt.setBoolean(1, !seat.getAvailability());
            stmt.setInt(2, seat.getSeatNumber());
            stmt.setInt(3, seat.getTrip().getId());
            stmt.execute();
            closeStatement(stmt);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
    public static Departure fetchDeparture(Date date, Time time) throws SQLException{
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(FETCH_TIME);
            stmt.setDate(1, (java.sql.Date) date);
            stmt.setTime(2, (java.sql.Time) time);
            stmt.execute();
            ResultSet resultSet = stmt.getResultSet();
            System.out.println("result: " + resultSet);
            Departure departure = null;
            while (resultSet.next()){
                    int id = resultSet.getInt("departure_id");
                    Date trip_date = resultSet.getDate("date");
                    Time trip_time = resultSet.getTime("time");
                    departure = new Departure(id, trip_time, trip_date);
            }
            closeStatement(stmt);
            return departure;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
    
    public static void insertTrip(Date date, Time time, int price, int route_id, int bus_id) throws SQLException {
            Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_TRIP);
            stmt.setInt(1, bus_id);
            stmt.setInt(2, route_id);
            Departure departure = fetchDeparture(date,time);
            if (departure == null){
                insertDeparture(date, time);
                departure = fetchDeparture(date, time);
            }
            System.out.println("departure: " + departure);
            stmt.setInt(3, departure.getId());
            stmt.setInt(4, price);
            stmt.execute();
            closeStatement(stmt);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
    
    public static void insertUser(String username, String firstN, String lastN, String email, String phone, String pass) throws SQLException{
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_USER);
            stmt.setString(2, firstN);
            stmt.setString(3, lastN);
            stmt.setString(4, email);
            stmt.setString(5, phone);
            stmt.setString(6, pass); 
            stmt.setString(1, username);
            stmt.execute();
            closeStatement(stmt);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
    
    public static String getPassword(String email) throws SQLException{
        Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(String.format(FETCH_USER, email, email));
            ResultSet resultSet = stmt.getResultSet();
            String password = null;
            while (resultSet.next()){
                password = resultSet.getString("password");
            }
            closeStatement(stmt);
            return password;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
    
    
    public static List<String> getEmails() throws SQLException{
        Connection conn = null;
        List<String> emails = new ArrayList<String>();
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(FETCH_EMAILS);
            ResultSet resultSet = stmt.getResultSet();
            
            while (resultSet.next()){
                emails.add(resultSet.getString("email"));
            }
            closeStatement(stmt);
            return emails;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
    
    public static Account getUser(String email) throws SQLException{
        Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(String.format(FETCH_USER, email, email));
            ResultSet resultSet = stmt.getResultSet();
            Account user = null;
            
            while (resultSet.next()){
                String username = resultSet.getString("username");
                String firstN = resultSet.getString("first_name");
                String lastN = resultSet.getString("last_name");
                String password = resultSet.getString("password");
                String phone = resultSet.getString("phone");
                String emailAddress = resultSet.getString("email");
                user = new Account(username, firstN, lastN, emailAddress, password, phone);
            }
            closeStatement(stmt);
            return user;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
    public static void insertBus(String type, int capacity) throws SQLException{
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_BUS);
            stmt.setString(1, type);
            stmt.setInt(2, capacity);
            stmt.execute();
            closeStatement(stmt);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
    
    
     public static List<Bus> fetchBuses() throws SQLException{
        Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(FETCH_BUSES);
            ResultSet resultSet = stmt.getResultSet();
            List<Bus> result = marshallBus(resultSet);
            closeStatement(stmt);
            return result;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
     
     public static List<Trip> fetchTrips() throws SQLException{
        Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(FETCH_TRIPS);
            ResultSet resultSet = stmt.getResultSet();
            List<Trip> result = marshallTrips(resultSet);
            closeStatement(stmt);
            return result;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
    
     public static void insertSeat(int seatID, int tripID, boolean avail) throws SQLException{
         Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_SEAT);
            stmt.setInt(1, seatID);
            stmt.setInt(2, tripID);
            stmt.setBoolean(3, avail);
            stmt.execute();
            closeStatement(stmt);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
     }
     
     public static Seat fetchSeat(int seat_no, int trip_id) throws SQLException {
         Connection conn = null;
        try {
            conn = getConnection();
           PreparedStatement stmt = conn.prepareStatement(FETCH_SEAT);
            stmt.setInt(1, seat_no);
            stmt.setInt(2, trip_id);
            stmt.execute();
            ResultSet result = stmt.getResultSet();
            Seat seat = null;
            while (result.next()) {
                int id = result.getInt("seat_no");
                Trip trip = fetchTrip(result.getInt("trip_id"));
                boolean availability = result.getBoolean("available");
                seat = new Seat(id, trip, availability); 
            }
            closeStatement(stmt);
            return seat;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
     }
     
     public static List<Seat> fetchSeats(Date trip_date, int route) throws SQLException{
         Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            List<Trip> trips = fetchTrips(trip_date, route);
            System.out.println("trips3: " + trips.size());
            List<Seat> seats = new ArrayList<>();
            for (Trip trip:trips){
                stmt.execute(String.format(FETCH_SEATS, trip.getId()));
                ResultSet resultSet = stmt.getResultSet();
                List<Seat> result = marshallSeats(resultSet);
                System.out.println("result1: " + result.size());
                if (result != null){
                    for (Seat seat:result){
                        seats.add(seat);
                    }
                }
            }
            closeStatement(stmt);
            return seats;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
     }
     
     public static List<Route> fetchRoutes() throws SQLException{
         Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(FETCH_ROUTES);
            ResultSet resultSet = stmt.getResultSet();
            List<Route> result = marshallRoutes(resultSet);
            closeStatement(stmt);
            return result;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
     }
    
     private static List<Bus> marshallBus(ResultSet result) throws SQLException{
        List<Bus> list = new ArrayList<>();
        if (result == null) {
            return list;
        }
        while (result.next()) {
            int id = result.getInt("bus_id");
            String type = result.getString("type");
            int capacity  = result.getInt("capacity");
            Bus bus = new Bus(id, type, capacity);
            list.add(bus);
        }
        return list;
    }
     
     private static List<Trip> marshallTrips(ResultSet result) throws SQLException{
        List<Trip> list = new ArrayList<>();
        if (result == null) {
            return list;
        }
        while (result.next()) {
            int tripId = result.getInt("trip_id");
            Bus bus = getBus(result.getInt("bus_id"));
            Route route = fetchRoute(result.getInt("route_id"));
            Departure departure = fetchDeparture(result.getInt("departure_id"));
            Double price = result.getDouble("price");
            Trip trip = new Trip(tripId, bus, route, departure, price);
            list.add(trip);
        }
        return list;
    }
     
      private static List<Seat> marshallSeats(ResultSet result) throws SQLException{
        List<Seat> list = new ArrayList<>();
        if (result == null) {
            return list;
        }
        while (result.next()) {
            int id = result.getInt("seat_no");
            Trip trip = fetchTrip(result.getInt("trip_id"));
            boolean availability = result.getBoolean("available");
            Seat seat = new Seat(id, trip, availability);
            list.add(seat);
        }
        return list;
    }
      private static List<Route> marshallRoutes(ResultSet result) throws SQLException{
        List<Route> list = new ArrayList<>();
        if (result == null) {
            return list;
        }
        while (result.next()) {
            int id = result.getInt("route_id");
            String route = result.getString("route");
            Route route_obj = new Route(id, route);
            list.add(route_obj);
        }
        return list;
    }
      
    
      
    public static void insertTickets(List<Ticket> tickets) throws SQLException{
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_TICKETS);
            for (Ticket ticket:tickets){
                stmt.setString(1, ticket.getName());
                stmt.setString(2, ticket.getNumber());
                stmt.setString(3, ticket.getUser().getUsername());
                stmt.setInt(4, ticket.getSeat().getSeatNumber());
                stmt.setInt(5, ticket.getSeat().getTrip().getId());
                stmt.setBoolean(6, ticket.getOptions());
                updateSeat(ticket.getSeat());
                stmt.execute();
            }
            
            closeStatement(stmt);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
    
    public static List<Ticket> fetchTickets(Account user) throws SQLException {
         Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
           
            stmt.execute(String.format(FETCH_TICKETS, user.getUsername()));
            ResultSet resultSet = stmt.getResultSet();
            List<Ticket> result = marshallTickets(resultSet);
            closeStatement(stmt);
            return result;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
    
    private static List<Ticket> marshallTickets(ResultSet result) throws SQLException{
        List<Ticket> list = new ArrayList<>();
        if (result == null) {
            return list;
        }
        while (result.next()) {
            int id = result.getInt("ticket_id");
            String passenger = result.getString("passenger_name");
            String passenger_phone = result.getString("passenger_phone");
            String names[] = passenger.split(" ");
            Seat seat = fetchSeat(result.getInt("seat_no"),result.getInt("trip_id"));
            Account user = getUser(result.getString("user"));
            Boolean option = result.getBoolean("option");
            Ticket ticket = new Ticket(id, names[0], names[1], passenger_phone, seat, user, option);
            list.add(ticket);
        }
        return list;
    }
    

    private static Bus getBus(int aInt) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(String.format(FETCH_BUS, aInt));
            ResultSet resultSet = stmt.getResultSet();
            Bus bus = null;
            while (resultSet.next()){
                int id = resultSet.getInt("bus_id");
                String type = resultSet.getString("type");
                int cap = resultSet.getInt("capacity");
                bus = new Bus(id, type, cap);
            }
            closeStatement(stmt);
            return bus;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
    
    private static Trip fetchTrip(int aInt) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(String.format(FETCH_TRIP, aInt));
            ResultSet result = stmt.getResultSet();
            Trip trip = null;
            while (result.next()){
                int tripId = result.getInt("trip_id");
                Bus bus = getBus(result.getInt("bus_id"));
                Route route = fetchRoute(result.getInt("route_id"));
                Departure departure = fetchDeparture(result.getInt("departure_id"));
                Double price = result.getDouble("price");
                trip = new Trip(tripId, bus, route, departure, price);
            }
            closeStatement(stmt);
            return trip;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
    
    private static Route fetchRoute(int aInt) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(String.format(FETCH_ROUTE, aInt));
            ResultSet resultSet = stmt.getResultSet();
            Route route = null;
            while (resultSet.next()){
                int id = resultSet.getInt("route_id");
                String rout = resultSet.getString("route");
                route = new Route(id, rout);
            }
            closeStatement(stmt);
            return route;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
    
    public static List<Trip> fetchTrips(Date date, int routeID) throws SQLException{
        Connection conn = null;
        try {
            List<Trip> trips = new ArrayList<>();
            conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(FETCH_TRIPS);
            ResultSet resultSet = stmt.getResultSet();
            List<Trip> result = marshallTrips(resultSet);
            System.out.println("trips4 :" + result.size());
            closeStatement(stmt);
            for (Trip tp:result){
                System.out.println(String.format("test1: %s", tp.getDepartureDate().toString().equals(date.toString())));
                System.out.println("test2:"+(tp.getRouteID() == routeID));
                System.out.println("trip date: "+tp.getDepartureDate());
                if (tp.getDepartureDateString().equals(date.toString()) 
                        && tp.getRouteID() == routeID){
                    trips.add(tp);
                }
            }
            return trips;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
    
    
    private static Departure fetchDeparture(int aInt) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(String.format(FETCH_DEPARTURE, aInt));
            ResultSet resultSet = stmt.getResultSet();
            Departure departure = null;

            while (resultSet.next()){
                int id = resultSet.getInt("departure_id");
                Date trip_date = resultSet.getDate("date");
                Time trip_time = resultSet.getTime("time");
                departure = new Departure(id, trip_time, trip_date);
            }
            closeStatement(stmt);
            return departure;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        finally {
            closeConnection(conn);
        }
    }
    
    private static DataSource getDataSource(){
        DataSource ds = null;
        try {
            ds = (DataSource) envCtx.lookup(DS_NAME);
        }
        catch (NamingException ex) {
            ex.printStackTrace();
            throw new RepositoryException("Failed to get DataSource");
        }
        return ds;
    }

    private static Connection getConnection(){
        Connection con = null;
        try {
            con = getDataSource().getConnection();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RepositoryException("Failed to get connection to database server");
        }
        return con;
    }

    private static void closeStatement(Statement stmt){
        if (stmt != null) {
            try {
                stmt.close();
                stmt = null;
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void closeConnection(Connection conn){
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}