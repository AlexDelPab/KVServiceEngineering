<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<jsp:useBean id="reservations" class="main.java.controller.ReservationController"/>

<c:set var="content">
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="reservations.jsp">Reservations
            </a>
        </li>
        <li class="breadcrumb-item active">Create</li>
    </ol>
    <div class="container">
        <div class="card card-register mx-auto mt-5">
            <div class="card-header">Create an Reservation</div>
            <div class="card-body">
                <form action="createReservation.jsp" method="post">
                    <div class="form-group">
                        <div class="form-row">
                            <%
                                Date today = Calendar.getInstance().getTime();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                String min = sdf.format(today);
                            %>
                            <div class="col-md-6">
                                <div class="form-label-group">
                                    <input type="date" name="from" id="from" min="<%= min %>" value="<%= min %>"
                                           class="form-control" placeholder="Von"
                                           required>
                                    <label for="to">Von </label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-label-group">
                                    <input type="date" name="to" id="to" min="<%= min %>" value="<%= min %>"
                                           class="form-control" placeholder="Bis"
                                           required>
                                    <label for="to">Bis</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <div class="form-label-group">
                                    <select id="room" name="room" class="form-control" required>
                                        <c:forEach items="${reservations.allRooms}" var="room">
                                            <option value="${room.id}">${room.id} ${room.type}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-label-group">
                                    <select id="user" name="user" class="form-control" required>
                                        <c:forEach items="${reservations.allGuests}" var="guest">
                                            <option value="${guest.id}">${guest.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button class="btn btn-primary btn-block" type="submit">Register</button>
                </form>
            </div>
        </div>
    </div>

    <% reservations.create(request.getParameter("from"), request.getParameter("to"), request.getParameter("room"), request.getParameter("user")); %>
</c:set>

<t:base>
    <jsp:body>
        ${content}
    </jsp:body>
</t:base>