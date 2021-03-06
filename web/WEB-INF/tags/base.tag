<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@ attribute name="activeItem" required="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Hotel Frontdesk</title>

    <link href="../../resources/template/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../resources/template/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="../../resources/template/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <link href="../../resources/template/css/sb-admin.css" rel="stylesheet">

    <link href="../../resources/base.css" rel="stylesheet">
</head>

<body id="page-top">

<nav class="navbar navbar-expand navbar-dark bg-dark static-top">

    <a class="navbar-brand mr-1" href="../../index.jsp">Hotel Frontdesk</a>

</nav>

<div id="wrapper">
    <ul class="sidebar navbar-nav">
        <li class="nav-item  <c:if test="${activeItem == 'index'}">active</c:if>">
            <a class="nav-link" href="../../index.jsp">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Dashboard</span>
            </a>
        </li>
        <li class="nav-item <c:if test="${activeItem == 'guests'}">active</c:if>">
            <a class="nav-link" href="../../views/guests.jsp">
                <i class="fas fa-bed"></i>
                <span>Guests</span>
            </a>
        </li>
        <li class="nav-item <c:if test="${activeItem == 'employees'}">active</c:if>">
            <a class="nav-link" href="../../views/employees.jsp">
                <i class="fas fa-address-book"></i>
                <span>Employees</span></a>
        </li>
        <li class="nav-item <c:if test="${activeItem == 'rooms'}">active</c:if>">
            <a class="nav-link" href="../../views/rooms.jsp">
                <i class="fas fa-bed"></i>
                <span>Rooms</span></a>
        </li>
        <li class="nav-item <c:if test="${activeItem == 'reservations'}">active</c:if>">
            <a class="nav-link" href="../../views/reservations.jsp">
                <i class="fas fa-clipboard-list"></i>
                <span>Reservations</span></a>
        </li>
    </ul>

    <div id="content-wrapper">

        <div class="container-fluid">

            <jsp:doBody/>

        </div>
    </div>

    <footer class="sticky-footer">
        <div class="container my-auto">
            <div class="copyright text-center my-auto">
                <span>Copyright © Gruppe 3</span>
            </div>
        </div>
    </footer>

</div>
</body>
</html>

