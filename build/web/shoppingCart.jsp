<%-- 
    Document   : shoppingCart
    Created on : Nov 2, 2018, 2:57:55 PM
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
        <title>Cart</title>
    </head>
    <body>
        <s:if test="#attr.cart.getNumberOfItems() <=0 || #attr.cart == null">
            <h1 align="center">Your Cart is empty</h1>
        </s:if>
        <s:if test="#attr.cart.getNumberOfItems() >0">
            <table width ="100%">
                <tr>
                    <td>Seat Number</td>
                    <td>Departure Date</td>
                    <td>Departure Time</td>
                    <td>Bus Number</td>
                    <td>Bus Type</td>
                    <td>Route</td>
                    <td>Price</td>

                </tr>
                <s:iterator value="#attr.cart.getItems()">
                    <tr>
                        <td><s:property value="getSeatNumber()"/></td>
                        <td><s:property value="getTrip().getDepartureDateString()"/></td>
                        <td><s:property value="getTrip().getDepartureTime()"/></td>
                        <td><s:property value="getTrip().getBus().getID()"/></td>
                        <td><s:property value="getTrip().getBus().getType()"/></td>
                        <td><s:property value="getTrip().getRoute()"/></td>
                        <td><s:property value="getTrip().getPrice()"/></td>
                        <td>
                            <form action="removeFromCart">
                                <input type="hidden" value="<s:property value="getSeatNumber()"/>" name="seat_id"/>
                                <input type="hidden" value="<s:property value="getTrip().getId()"/>" name="trip_id"/>
                                <input type="submit" value="Remove from cart"/>
                            </form>
                        </td>
                    </tr>
                </s:iterator>
                <tr>
                    <td colspan="6">Grand Total</td>
                    <td><s:property value="#attr.cart.getGrandTotal()"/></td>
                    <td><a href="confirmation.jsp"><button>Proceed to Checkout</button></a></td>
                </tr>
            </table>
       
        </s:if>
    </body>
</html>
