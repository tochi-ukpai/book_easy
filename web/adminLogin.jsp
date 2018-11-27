<%-- 
    Document   : adminLogin
    Created on : Nov 10, 2018, 7:00:36 AM
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
    </head>
    <body>
        <s:if test = "#attr.account == \"admin\"">
            <c:redirect url="./adminDashboard.jsp"></c:redirect>
        </s:if>
        <form action="adminLogin" method="post">
            <legend>Enter administrative password</legend><br/>
            <input type="password" placeholder="Password" name="pass"/><br/>
            <input type="submit" value = "Login"/>
        </form>
    </body>
</html>
