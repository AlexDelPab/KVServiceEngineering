<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="main.java.database.entities.Guest" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="guests" class="main.java.controller.GuestController"/>

<c:set var="content">
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="#">Guests</a>
        </li>
        <li class="breadcrumb-item active">Overview</li>
    </ol>
    <div class="card mb-3">
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Room</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${guests.allGuests}" var="guest">
                            <tr>
                            <td>${guest.id}</td>
                            <td>${guest.name}</td>
                            <td>${guest.address}</td>
                                <td>
                                    <c:if test="${guest.occupiesRoom != -1}">
                                        ${guests.getRoomById(guest.occupiesRoom).id}
                                        ${guests.getRoomById(guest.occupiesRoom).type}
                                    </c:if>
                                    <c:if test="${guest.occupiesRoom == -1}">
                                        <a class="btn btn-sm btn-primary" href="#">Add Room</a>
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
</c:set>

<t:base>
    <jsp:body>
        ${content}
    </jsp:body>
</t:base>