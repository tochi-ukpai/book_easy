<%-- 
    Document   : signin
    Created on : Oct 15, 2018, 1:06:05 AM
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
        <title>Sign In</title>
    </head>
    
        <h1>Enter details to Sign In</h1>
        <form action="login" method="post">
            <s:if test="#attr.error == true">
                <p><s:property value="#attr.errorMessage"/></p>
            </s:if>
            <input type="email" name="email" placeholder="Enter Email Address"/><br/>
            <input type="password" name="pass" placeholder="Enter Password"/><br/>
            <input type="submit" value="Login"/>
        </form>
        <a href="signup.jsp"><button>Register</button></a>
    
</html>
