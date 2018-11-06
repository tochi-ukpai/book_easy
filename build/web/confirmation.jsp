<%-- 
    Document   : confirmation
    Created on : Nov 5, 2018, 11:03:55 AM
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
        <title>Confirm Tickets</title>
    </head>
    <body>
        <h2>Complete form below</h2>
        <form action="addTickets" method="post">
            <table>
                <tr>
                    <td>Seat</td>
                    <td>Bus</td>
                    <td>Passenger First Name</td>
                    <td>Passenger Last Name</td>
                    <td>Passenger Phone Number</td>
                    <td>Include option packages</td>
                </tr>
                <s:iterator value="#attr.cart.getItems()">
                    <tr>
                        <td><s:property value="getSeatNumber()"/></td>
                        <td><s:property value="getBus().getID()"/></td>
                        <td><input type="text" name="fName <s:property value="getSeatNumber()"/>" placeholder="First Name" required="true"/></td>
                        <td><input type="text" name="lName <s:property value="getSeatNumber()"/>" placeholder="Last Name" required="true"/></td>
                        <td><input type="text" name="phone <s:property value="getSeatNumber()"/>" placeholder="Phone Number" required="true"/></td>
                        <td><input type="checkbox" name="options <s:property value="getSeatNumber()"/>" checked value = "true"/></td>
                    </tr>
                </s:iterator>
                <tr>
                    <td>
                        <input type="submit" value="Submit"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
