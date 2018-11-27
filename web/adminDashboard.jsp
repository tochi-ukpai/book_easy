<%-- 
    Document   : adminDashboard
    Created on : Oct 16, 2018, 4:38:36 PM
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
        <title>Admin Dashboard</title>
        <link rel="stylesheet" type = "text/css" href = "style.css"></link>
    </head>
    <body>
        <s:if test="#attr.account != null && #attr.account.equalsIgnoreCase(\"admin\")">
            <h1>Welcome to The Admin Dashboard</h1>
            <form action="logout" class="right">
                <input type="submit" value="Logout"/>
            </form>

            <form action ="viewbuses" class="right">
                <input type="submit" value="View Buses"/>
            </form>
            <form action="bookseats" class="right">
                <input type="submit" value="View Seats"/>
            </form>
            <a href="./addBus.jsp"><button class="right">Add New Bus</button></a>
            <a href="./assignBus.jsp"><button class="right">Assign Bus</button></a>
            <table width = "100%">
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
        </s:if>
        <s:if test="#attr.account == null">
            <c:redirect url="admin"/>
        </s:if>
        
    </body>
</html>
