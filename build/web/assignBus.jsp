<%-- 
    Document   : assignBus
    Created on : Nov 15, 2018, 3:42:04 PM
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
        <title></title>
        
    <link href="https://getbootstrap.com/docs/4.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/4.1/examples/sign-in/signin.css" rel="stylesheet">
    </head>
    <body>
        <form method="post" action="assignBus">
            <input type="date" id="inputDateOfBirth" class="form-control" onfocus="(this.type='date')" onblur="(this.type='text')" placeholder="Enter Trip Date" name = 'trip_date' required/>
            <input type="time" id="time" class="form-control" onfocus="(this.type='time')" onblur="(this.type='text')" placeholder="Enter Trip Time" name = 'trip_time' required/>
            <label>Bus:</label>
            <select name = "bus" default="Choose Bus" default="hey" required>
                <s:iterator value="#attr.buses">
                    <option value="<s:property value = "getID()"/>">Bus <s:property value = 'getID()'/></option>
                </s:iterator>
            </select>
            <input type="number" name ="price" placeholder ="Enter Price"/>
            <select name = "route" default ="Choose Route">
                <s:iterator value="#attr.routes">
                    <option value="<s:property value = "getID()"/>"><s:property value = 'getRoute()'/></option>
                </s:iterator>
            </select>
            <input type="submit" value="Create Trip"/>
        </form>
    </body>
</html>
