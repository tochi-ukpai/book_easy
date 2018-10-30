/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysg.util;

import com.ysg.data.Account;
import com.ysg.data.Bus;
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
    private static final String INSERT_BUS = "INSERT INTO buses (route, type, capacity) VALUES (?,?,?)";
    private static final String INSERT_SEAT = "INSERT INTO seats (route, type, capacity) VALUES (?,?,?)";
    private static DataSource ds;
    private static final String DS_NAME = "jdbc/sen301DS";
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
    public static void insertBus(String route, String type, int capacity) throws SQLException{
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_BUS);
            stmt.setString(1,route);
            stmt.setString(2, type);
            stmt.setInt(3, capacity);
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
