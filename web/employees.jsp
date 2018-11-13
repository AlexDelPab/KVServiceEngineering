<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="main.java.Database.Entities.Employer" %>
<%@ page import="java.util.List"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="employer" class="main.java.Controller.EmployerController"/>

<c:set var="content">
    <div class="card mb-3">
        <div class="card-header">
            <i class="fas fa-table"></i>
            Employees
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Responsibility</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${employer.allEmployees}" var="emp">
                    <tr>
                        <td>${emp.id}</td>
                        <td>${emp.name}</td>
                        <td>${emp.address}</td>
                        <td>TODO</td>
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