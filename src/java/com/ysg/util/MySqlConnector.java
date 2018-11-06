/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysg.util;

import com.ysg.data.Account;
import com.ysg.data.Bus;
import com.ysg.data.Seat;
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


/**
 *
 * @author tochukwu
 */
public class MySqlConnector {
    private static final String INSERT_USER = "INSERT INTO userInfo (firstname, lastname, emailAddress, phoneNumber, password) VALUES (?,?,?,?,?)";
    private static final String FETCH_EMAILS = "SELECT emailAddress FROM userInfo";
    private static final String FETCH_USER = "SELECT * FROM userInfo WHERE emailAddress =\"%s\"";
    private static final String INSERT_BUS = "INSERT INTO buses (route, type, capacity, available) VALUES (?,?,?,?)";
    private static final String INSERT_SEAT = "INSERT INTO seats (seatID, busID, price, availability) VALUES (?,?,?,?)";
    private static final String FETCH_BUSES = "SELECT * FROM buses";
    private static final String FETCH_SEATS = "SELECT * FROM seats";
    private static final String FETCH_BUS = "SELECT * FROM buses WHERE busID = %d";
    private static DataSource ds;
    private static final String DS_NAME = "jdbc/sen_301DS";
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
    
    public static void insertUser(String firstN, String lastN, String email, String phone, String pass) throws SQLException{
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_USER);
            stmt.setString(1, firstN);
            stmt.setString(2, lastN);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, pass); 
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
            stmt.execute(String.format(FETCH_USER, email));
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
                emails.add(resultSet.getString("emailAddress"));
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
            stmt.execute(String.format(FETCH_USER, email));
            ResultSet resultSet = stmt.getResultSet();
            Account user = null;
            
            while (resultSet.next()){
                String firstN = resultSet.getString("firstname");
                String lastN = resultSet.getString("lastname");
                String password = resultSet.getString("password");
                String phone = resultSet.getString("phoneNumber");
                user = new Account(firstN, lastN, email, password, phone);
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
    public static void insertBus(String route, String type, int capacity, int seats) throws SQLException{
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_BUS);
            stmt.setString(1,route);
            stmt.setString(2, type);
            stmt.setInt(3, capacity);
            stmt.setInt(4, seats);
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
    
     public static void insertSeat(int sID, int bID, int price, boolean avail) throws SQLException{
         Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_SEAT);
            stmt.setInt(1, sID);
            stmt.setInt(2, bID);
            stmt.setDouble(3, price);
            stmt.setBoolean(4, avail);
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
     
     public static List<Seat> fetchSeats() throws SQLException{
         Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(FETCH_SEATS);
            ResultSet resultSet = stmt.getResultSet();
            List<Seat> result = marshallSeats(resultSet);
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
            int id = result.getInt("busID");
            String route = result.getString("route");
            String type = result.getString("type");
            int capacity  = result.getInt("capacity");
            int available = result.getInt("available");
            Bus bus = new Bus(id, route, type, capacity, available);
            list.add(bus);
        }
        return list;
    }
     
      private static List<Seat> marshallSeats(ResultSet result) throws SQLException{
        List<Seat> list = new ArrayList<>();
        if (result == null) {
            return list;
        }
        while (result.next()) {
            int id = result.getInt("seatID");
            Bus bus = getBus(result.getInt("busID"));
            int price  = result.getInt("price");
            boolean availability = result.getBoolean("availability");
            Seat seat = new Seat(id, bus, price, availability);
            list.add(seat);
        }
        return list;
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

    private static Bus getBus(int aInt) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(String.format(FETCH_BUS, aInt));
            ResultSet resultSet = stmt.getResultSet();
            Bus bus = null;
            while (resultSet.next()){
                int id = resultSet.getInt("busID");
                String route = resultSet.getString("route");
                String type = resultSet.getString("type");
                int cap = resultSet.getInt("capacity");
                int avail = resultSet.getInt("available");
                bus = new Bus(id, route, type, cap, avail);
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
}
