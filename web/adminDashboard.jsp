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
    </head>
    <body>
        <s:if test="#attr.account != null">
            <h1>Welcome to The Admin Dashboard</h1>
        </s:if>
        <form action="logout">
            <input type="submit" value="Logout"/>
        </form>
        <s:if test="#attr.account == null">
            <c:redirect url="signin.jsp"/>
        </s:if>
            <form action ="viewbuses" method="post">
                <input type="submit" value="View Buses"/>
            </form>
            <form action="viewseats" method="post">
                <input type="submit" value="View Seats"/>
            </form>
    </body>
</html>
