<%-- 
    Document   : dashBoard
    Created on : Oct 15, 2018, 2:15:03 AM
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
        <title>User Dashboard</title>
    </head>
    <body>
        <s:if test="#attr.account != null">
            <h1>Welcome <s:property value="#attr.account.getName()"/></h1>
            <form action="logout">
                <input type="submit" value="Logout"/>
            </form>
            <p>Email: <s:property value ="#attr.account.getEmail()"/></p>
            <p>First Name: <s:property value = "#attr.account.getFirstName()"/></p>
            <p>Last Name: <s:property value = "#attr.account.getLastName()"/></p>
            <p>Phone Number: <s:property value ="#attr.account.getPhone()"/></p>
            <p>Password: <s:property value ="#attr.account.getPassword()"/></p>
        </s:if>
        <s:if test="#attr.account == null">
            <c:redirect url="signin.jsp"/>
        </s:if>
            
    </body>
</html>
