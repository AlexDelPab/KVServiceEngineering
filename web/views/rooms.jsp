<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="main.java.database.entities.Room" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="rooms" class="main.java.controller.RoomController"/>

<c:set var="content">
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="#">Rooms</a>
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
</c:set>

<t:base activeItem="rooms">
    <jsp:body>
        ${content}
    </jsp:body>
</t:base>