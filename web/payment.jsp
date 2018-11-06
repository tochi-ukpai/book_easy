<%-- 
    Document   : payment
    Created on : Nov 5, 2018, 12:11:54 PM
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
        <title>Payment Details</title>
    </head>
    <body>
        <s:iterator value="#attr.tickets">
            <s:property value="getName()"/> 
        </s:iterator>
    </body>
</html>
