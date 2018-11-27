<%-- 
    Document   : seats
    Created on : Nov 1, 2018, 9:36:47 PM
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
        <title>Available Seats</title>
    </head>
    <body>
        <%--<s:if test="#attr.seats == null">--%>
            <%--<c:redirect url="viewseats.action"></c:redirect>--%>
        <%--</s:if>--%>
        <s:if test="#attr.account != null">
            <a href="<s:if test="#attr.account.equalsIgnoreCase(\"admin\")">adminDashboard.jsp
               </s:if><s:else>dashBoard.jsp</s:else>">
                <button>Dashboard</button>
            </a>
        </s:if>
        <s:if test="#attr.account == null">
            <a href="signin.jsp"><button>Sign In</button></a>
            <a href="signup.jsp"><button>Register</button></a>
        </s:if>
        <a href="shoppingCart.jsp"><button>View Cart</button></a>
        <form method="post" action = "getTrip">
            <input type="date" id="inputDateOfBirth" class="form-control" onfocus="(this.type='date')" onblur="(this.type='text')" placeholder="Enter Trip Date" name = 'trip_date' required/>
            <select name = "route" default ="Choose Route" required>
                <s:iterator value="#attr.routes">
                    <option value="<s:property value = "getID()"/>"><s:property value = 'getRoute()'/></option>
                </s:iterator>
            </select>
            <input type="submit" value="View Available Seats">
        </form>
        <s:if test="#attr.seats != null">
        
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
                <s:iterator value="#attr.seats">
                    <tr>
                        <td><s:property value="getSeatNumber()"/></td>
                        <td><s:property value="getTrip().getDepartureDateString()"/></td>
                        <td><s:property value="getTrip().getDepartureTime()"/></td>
                        <td><s:property value="getTrip().getBus().getID()"/></td>
                        <td><s:property value="getTrip().getBus().getType()"/></td>
                        <td><s:property value="getTrip().getRoute()"/></td>
                        <td><s:property value="getTrip().getPrice()"/></td>
                        <td>
                            <form action="addToCart">
                                <input type="hidden" value="<s:property value="getSeatNumber()"/>" name="seat_id"/>
                                <input type="hidden" value="<s:property value="getTrip().getId()"/>" name="trip_id"/>
                                <input type="submit" value="Add to cart"/>
                            </form>
                        </td>
                    </tr>
                </s:iterator>
            </table>
        </s:if>
        
    </body>
</html>
