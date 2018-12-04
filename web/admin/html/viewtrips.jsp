<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <!--<link rel="icon" type="" href="">-->
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

  <title>Admin Dashboard | Young Shall Grow Motors</title>

  <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Animation library for notifications   -->
    <link href="../css/animate.min.css" rel="stylesheet"/>

    <!--  Light Bootstrap Table core CSS    -->
    <link href="../css/dashboard.css?v=1.4.0" rel="stylesheet"/>

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

    <link href="../css/pe-icon-7-stroke.css" rel="stylesheet" />
</head>
<body>

    <div class="wrapper">
        <div class="sidebar" data-color="blue">

          <div class="sidebar-wrapper">
                <div class="logo">
                   <a href="#" class="simple-text">
                        Young Shall Grow Motors
                    </a>
                </div>

                <ul class="nav">
                    <li>
                        <a href="profile.jsp">
                            <i class="pe-7s-user"></i>
                            <p>Profile</p>
                        </a>
                    </li>
                    <li>
                        <a href="createtrip.jsp">
                            <i class="pe-7s-note2"></i>
                            <p>Create Trip</p>
                        </a>
                    </li>
                    <li>
                        <a href="addbus.jsp">
                            <i class="pe-7s-plus"></i>
                            <p>Add Bus</p>
                        </a>
                    </li>
                    <li>
                        <a href="viewbuses.jsp">
                            <i class="pe-7s-car"></i>
                            <p>View Buses</p>
                        </a>
                    </li>
                    <li>
                        <a href="addroute.jsp">
                            <i class="pe-7s-plus"></i>
                            <p>Add Route</p>
                        </a>
                    </li>
                    <li  class="active">
                        <a href="viewtrips.jsp">
                            <i class="pe-7s-car"></i>
                            <p>View Trips</p>
                        </a>
                    </li>
                </ul>
          </div>
        </div>

        <div class="main-panel">
        <nav class="navbar navbar-default navbar-fixed">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-example-2">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">Admin Dashboard</a>
                    </div>

                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <a href="logout">
                                    <p>Log out</p>
                                </a>
                            </li>
                <li class="separator hidden-lg hidden-md"></li>
                        </ul>
                    </div>
                </div>
            </nav>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="header">
                                    <h4 class="title">Trips</h4>
                                </div>
                                <div class="content table-responsive table-full-width">
                                    <table class="table table-hover table-striped">
                                        <thead>
                                            <th>ID</th>
                                            <th>Bus Number</th>
                                            <th>Bus Type</th>
                                            <th>Route</th>
                                            <th>Departure Date</th>
                                            <th>Departure Time</th>
                                            <th>Price</th>
                                        </thead>
                                        <tbody>
                                            <s:iterator value ="#attr.trips">
                                                <tr>
                                                    <td><s:property value="getId()"/></td>
                                                    <td><s:property value="getBus().getID()"/></td>
                                                    <td><s:property value="getBus().getType()"/></td>
                                                    <td><s:property value="getRoute()"/></td>
                                                    <td><s:property value="getDepartureDateString()"/></td>
                                                    <td><s:property value="getDepartureTime()"/></td>
                                                    <td><s:property value="getPrice()"/></td>
                                                </tr>
                                            </s:iterator>
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                        </div>

           <!--   Core JS Files   -->
        <script src="../js/jquery.3.2.1.min.js" type="text/javascript"></script>
      <script src="../js/bootstrap.min.js" type="text/javascript"></script>

      <!--  Charts Plugin -->
      <script src="../js/chartist.min.js"></script>

        <!--  Notifications Plugin    -->
        <script src="../js/bootstrap-notify.js"></script>

      <script src="../js/light-bootstrap-dashboard.js?v=1.4.0"></script>


</body>

    

</html>
