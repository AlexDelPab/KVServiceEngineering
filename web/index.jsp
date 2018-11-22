<%@ page import="java.sql.SQLException" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="rooms" class="main.java.controller.RoomController"/>
<jsp:useBean id="reservations" class="main.java.controller.ReservationController"/>
<jsp:useBean id="guests" class="main.java.controller.GuestController"/>

<c:set var="content">
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="#">Dashboard</a>
        </li>
        <li class="breadcrumb-item active">Overview</li>
    </ol>
    <div class="card mb-3">
        <div class="card-header">
            <div class="row">
                <div class="col-md-12 -pull-right">
                    Rooms
                </div>
            </div>
        </div>
        <div class="card-body">
            <form action="index.jsp" method="post">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Type</th>
                            <th>Occupied</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${rooms.allRooms}" var="room">
                            <tr>
                                <td>${room.id}
                                    <input type="hidden" id="roomId" name="roomId" value="${room.id}">
                                    <label for="roomId"></label>
                                </td>
                                <td>${room.type}</td>
                                <td>
                                    <c:if test="${!room.occupiedBool}">
                                        <strong style="color: #32d306">FREE</strong>
                                    </c:if>
                                    <c:if test="${room.occupiedBool}">
                                        ${rooms.getGuestById(room.occupiedBy).name} (${rooms.getReservationByGuestId(room.occupiedBy).occupiedTo})
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${room.occupiedBy == -1}">
                                        <select name="guestId">
                                            <c:forEach items="${guests.allGuests}" var="guest">
                                                <option value="${guest.id}">${guest.name} ${guest.id}</option>
                                            </c:forEach>
                                        </select>
                                        <button class="btn btn-sm btn-primary" type="submit" name="button"
                                                value="checkIn">Check-In
                                        </button>
                                    </c:if>
                                    <c:if test="${room.occupiedBy != -1}">
                                        <button class="btn btn-sm btn-primary" type="submit" name="button"
                                                value="checkOut">Check-Out
                                        </button>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </form>
        </div>
        <div class="card-footer small text-muted"></div>
    </div>
    <div class="card mb-3">
    <div class="card-header">
        <div class="row">
            <div class="col-md-12 -pull-right">
                Reservations
            </div>
        </div>
    </div>
    <div class="card-body">
        <form action="index.jsp" method="post">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable1" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Guest</th>
                        <th>Room</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${reservations.allReservations}" var="reservation">
                        <tr>
                            <td>${reservation.id}</td>
                            <td>${reservation.occupiedFrom}</td>
                            <td>${reservation.occupiedTo}</td>
                            <td>${reservation.guest}</td>
                            <td>${reservation.room}</td>
                            <input type="hidden" name="roomId" value="${reservation.room}">
                            <label for="roomId"></label>
                            <td>
                                <button class="btn btn-sm btn-primary" type="submit" name="button"
                                        value="checkIn">Check-In
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
    </div>

    <%--<%= reservations.checkIn(request.getParameter("room")) %>--%>
    <%--<% reservations.checkIn(request.getParameter("room")); %>--%>

    <%=  rooms.doPost(request.getParameter("button"), request.getParameter("roomId"), request.getParameter("guestId")) %>
    <%
        rooms.doPost(request.getParameter("button"), request.getParameter("roomId"), request.getParameter("guestId"));
    %>
</c:set>

<t:base>
    <jsp:body>
        ${content}
    </jsp:body>
</t:base>