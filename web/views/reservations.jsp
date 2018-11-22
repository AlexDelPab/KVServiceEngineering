<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<jsp:useBean id="reservations" class="main.java.controller.ReservationController"/>

<c:set var="content">
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="#">Reservations</a>
        </li>
        <li class="breadcrumb-item active">Overview</li>
    </ol>
    <div class="card mb-3">
        <div class="card-header">
            <div class="row">
                <div class="col-md-12 -pull-right">
                    <a class="btn btn-primary -pull-right" href="createReservation.jsp">Create Reservation</a>
                </div>
            </div>
        </div>
        <form action="reservations.jsp">
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>From</th>
                            <th>To</th>
                            <th>Guest</th>
                            <th>Room</th>
                            <th>Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${reservations.allReservations}" var="reservation">
                            <c:set var="from" value="${reservation.occupiedFrom}"/>
                            <c:set var="to" value="${reservation.occupiedTo}"/>
                            <%
                                Date from = (Date) pageContext.getAttribute("from");
                                Date to = (Date) pageContext.getAttribute("to");
                                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                            %>
                            <tr>
                                <td>${reservation.id}</td>
                                <td><%= sdf.format(from) %>
                                </td>
                                <td><%= sdf.format(from) %>
                                </td>
                                <td>${reservation.guest}</td>
                                <td>${reservation.room}</td>
                                <td>${reservation.status}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card-footer small text-muted">

                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-2">
                            <div class="form-label-group">
                                <select id="room" name="room" class="form-control" required>
                                    <c:forEach items="${reservations.reservatedRooms}" var="room">
                                        <option value="${room.id}">${room.id} ${room.type}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-primary btn-block" type="submit">Check In</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <% reservations.checkIn(request.getParameter("room")); %>
</c:set>

<t:base activeItem="reservations">
    <jsp:body>
        ${content}
    </jsp:body>
</t:base>