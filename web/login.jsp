<%-- 
    Document   : login
    Created on : Nov 22, 2018, 3:37:36 PM
    Author     : tochukwu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Login Page">
    <meta name="author" content="YSG">
    <!--<link rel="icon" href="C:\Users\Kishi\Desktop\SEN project\LOGO\icon.png" width="100" height="100">-->

    <title>BookEasy Online Ticket Reservation Portal</title>

   
    <link href="https://getbootstrap.com/docs/4.1/dist/css/bootstrap.min.css" rel="stylesheet">

    <link href="https://getbootstrap.com/docs/4.1/examples/sign-in/signin.css" rel="stylesheet">
  </head>

  <body class="text-center">
      <form class="form-signin" action = 'login' method="post">
        <s:if test="#attr.error == true">
            <label><s:property value="#attr.errorMessage"/></label>
        </s:if>
      <!--<img class="mb-4" src="C:\Users\Kishi\Desktop\SEN project\LOGO\logo png.png" alt="" width="300" height="180">-->
      <h1 class="h3 mb-3 font-weight-normal">Sign in</h1>
      <label for="inputEmail" class="sr-only">Email address</label>
      <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus name = 'email'>
      <label for="inputPassword" class="sr-only" >Password</label>
      <input type="password" id="inputPassword" class="form-control" placeholder="Password" required name = 'pass'>
      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
        </label>
      </div>

            <p> If you do not have an account, register <a href="./register.html"> here</a></p>

      <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>

      <p class="mt-5 mb-3 text-muted">&copy; BookEasy 2017-2018</p>
    </form>
  </body>
</html>