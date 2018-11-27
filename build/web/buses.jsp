<%-- 
    Document   : buses
    Created on : Nov 1, 2018, 3:49:10 PM
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
        <title>Company Buses</title>
    </head>
    <body>
        <a href="dashBoard.jsp"><button>Dashboard</button></a>
        <table width = "100%">
            <tr>
                <td>Bus ID</td>
                <td>Type</td>
                <td>Capacity</td>
            </tr>
            <s:iterator value="#attr.buses">
                <tr>
                    <td><s:property value="getID()"/></td>
                    <td><s:property value="getType()"/></td>
                    <td><s:property value="getCapacity()"/></td>
                </tr>
            </s:iterator>
        </table>
    </body>
</html>
