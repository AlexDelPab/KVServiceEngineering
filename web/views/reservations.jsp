<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="main.java.database.entities.Employer" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="index" class="main.java.controller.IndexController"/>

<c:set var="content">
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="#">Employees</a>
        </li>
        </li>
        <li class="breadcrumb-item active">Reservations</li>
    </ol>
    <div class="card mb-3">
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
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${index.allReservations}" var="reservation">
                            <c:set var="id" value="${reservation.id}"/>
                            <tr>
                                <td>${reservation.id}</td>
                                <td>${reservation.occupiedFrom}</td>
                                <td>${reservation.occupiedTo}</td>
                                <td>${reservation.guest}</td>
                                <td>${reservation.room}</td>
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
                                <input type="number" min="0" max="8" name="room" id="room" class="form-control" value="0" placeholder="Check In Room Number">
                                <label for="room">Check In Room Number</label>
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

    <% index.checkIn(request.getParameter("room")); %>
</c:set>

<t:base>
    <jsp:body>
        ${content}
    </jsp:body>
</t:base>