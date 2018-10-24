<%-- 
    Document   : signup
    Created on : Oct 13, 2018, 4:37:19 PM
    Author     : tochukwu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <style>
            input{
                 margin: 2px;
            }
        </style>
    </head>
    <body>
        <h1>Complete the form below to register:</h1>
        <form action="register" method="post">
            <input type="text" placeholder="First Name" name="firstN"/><br/>
            <input type="text" placeholder="last Name" name="lastN"/><br/>
            <input type="text" placeholder="Phone Number" name="phone"/><br/>
            <input type="email" placeholder="Email Address" name="email"/><br/>
            <input type="password" placeholder="Password" name="pass"/><br/>
            <input type="password" placeholder="Re-enter password"/><br/>
            <input type="submit" value = "Register"/>
        </form>
        <a href="signin.jsp"><input type="button" value="Login"/></a>
    </body>
</html>
