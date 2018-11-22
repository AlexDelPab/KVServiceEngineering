<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
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


    <div class="row">
        <div class="col-xl-6 col-sm-6 mb-3">
            <div class="card text-white bg-primary o-hidden h-100">
                <div class="card-body">
                    <div class="card-body-icon">
                        <i class="fas fa-fw fa-comments"></i>
                    </div>
                    <div class="mr-5"><h3>Check-In</h3></div>
                    <form action="index.jsp" method="post">
                        <div class="row">
                            <div class="col-sm-4 col-xl-6">
                                <div class="form-label-group">
                                    <strong>Room: </strong>
                                    <select name="roomId" id="roomId1">
                                        <c:forEach items="${reservations.allFreeRooms}" var="room">
                                            <option value="${room.id}">${room.id} ${room.type}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xl-6">
                                <div class="form-label-group">
                                    <strong>Guest: </strong>
                                    <select name="guestId" id="guestId">
                                        <c:forEach items="${guests.allGuests}" var="guest">
                                            <option value="${guest.id}">${guest.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xl-6  mt-3">
                                <div class="form-label-group">
                                    <button class="btn btn-secondary" name="button" value="checkIn">OK</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-xl-6 col-sm-6 mb-3">
            <div class="card text-white bg-danger o-hidden h-100">
                <div class="card-body">
                    <div class="card-body-icon">
                        <i class="fas fa-fw fa-life-ring"></i>
                    </div>
                    <div class="mr-5"><h3>Check-Out</h3></div>
                    <form action="index.jsp" method="post">
                        <div class="row">
                            <div class="col-sm-4 col-xl-6">
                                <div class="form-label-group">
                                    <strong>Room: </strong>
                                    <select name="roomId">
                                        <c:forEach items="${rooms.allRooms}" var="room">
                                            <option value="${room.id}">${room.id} ${room.type}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4 col-xl-6">
                            </div>
                            <div class="col-sm-4 col-xl-6 mt-3">
                                <div class="form-label-group">
                                    <button class="btn btn-secondary" name="button" value="checkOut">OK</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </form>

    <% rooms.doPost(request.getParameter("button"), request.getParameter("roomId"), request.getParameter("guestId")); %>
    <%=  rooms.doPost(request.getParameter("button"), request.getParameter("roomId"), request.getParameter("guestId")) %>
    <div class="card mb-3">
        <div class="card-header">
            <div class="row">
                <div class="col-md-12 -pull-right">
                    Rooms
                </div>
            </div>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Type</th>
                        <th>Occupied</th>
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
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
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
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable1" width="100%" cellspacing="0">
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

</c:set>

<t:base activeItem="index">
    <jsp:body>
        ${content}
    </jsp:body>
</t:base>