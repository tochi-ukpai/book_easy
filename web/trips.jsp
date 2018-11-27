<%-- 
    Document   : trips
    Created on : Nov 26, 2018, 1:31:59 AM
    Author     : tochukwu
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trips</title>
    </head>
    <body>
        <table>
            <tr>
                <td>Trip ID</td>
                <td>Bus</td>
                <td>Route</td>
                <td>Departure Date</td>
                <td>Departure Time</td>
                <td>Price</td>
            </tr>
            <s:iterator value ="#attr.trips">
                <tr>
                    <td><s:property value="getId()"/></td>
                    <td><s:property value="getBus().getID()"/></td>
                    <td><s:property value="getRoute()"/></td>
                    <td><s:property value="getDepartureDateString()"/></td>
                    <td><s:property value="getDepartureTime()"/></td>
                    <td><s:property value="getPrice()"/></td>
                </tr>
            </s:iterator>
        </table>
        <%Date date = new Date();%>
        <p>Today's date: <%= (new java.util.Date()).toLocaleString()%></p>
        
    </body>
</html>
