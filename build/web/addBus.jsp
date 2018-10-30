<%-- 
    Document   : addBus
    Created on : Oct 30, 2018, 9:01:10 PM
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
        <title>Add Bus</title>
    </head>
    <body>
        <s:if test="#attr.account != null && #attr.account.getEmail() == \"admin@YSG.com\"">
            <form action="logout" style="float: right;">
                <input type="submit" value="Logout"/>
            </form>
            <form action="addbus" method="post">
                <legend>Enter bus details</legend>
                <label>Route:</label>
                <select name="route" default="Choose Route">
                    <option value="Lagos to Yola">Lagos to Yola</option>
                    <option value="Yola to Lagos">Yola to Lagos</option>
                </select>
                <br/>
                
                <label>Type:</label>
                <select name="type" default="Choose Bus Type">
                    <option value="luxury">Luxury</option>
                    <option value="Hummer">Hummer</option>
                </select>    
                <br/>
                Capacity:<input type="number" name ="capacity"/><br/>
                <input type="submit" value="Add"/>            
            </form>
        </s:if>
        <s:if test="#attr.account.getEmail() != \"admin@YSG.com\"">
            <c:redirect url="dashboard.jsp"/>
        </s:if>
        <s:if test="#attr.account == null">
            <c:redirect url="signin.jsp"/>
        </s:if>
    </body>
</html>
