<%-- 
    Document   : seatsAdmin
    Created on : Oct 30, 2018, 8:58:26 PM
    Author     : tochukwu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bus Seats</title>
    </head>
    <body>
        <a href="adminDashboard.jsp"><button>Dashboard</button></a>
        <table width = "100%">
            <tr>
                <td>Seat Number</td>
                <td>Bus Number</td>
                <td>Bus Type</td>
                <td>Route</td>
                <td>Price</td>
                
            </tr>
            <s:iterator value="#attr.seats">
                <tr>
                    <td><s:property value="getSeatNumber()"/></td>
                    <td><s:property value="getBus().getID()"/></td>
                    <td><s:property value="getBus().getRoute()"/></td>
                    <td><s:property value="getBus().getType()"/></td>
                    <td><s:property value="getPrice()"/></td>
                    <td>
                        <form action="addToCart">
                            <input type="submit" value="Add to cart"/>
                        </form>
                    </td>
                </tr>
            </s:iterator>
        </table>
        
    </body>
</html>
