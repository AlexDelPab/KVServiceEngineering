<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="main.java.database.entities.Employer" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="employees" class="main.java.controller.EmployerController"/>

<c:set var="content">
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="#">Employees</a>
        </li>
        <li class="breadcrumb-item active">Overview</li>
    </ol>
    <div class="card mb-3">
        <div class="card-header">
            <div class="row">
                <div class="col-md-12 -pull-right">
                    <a class="btn btn-primary -pull-right" href="createPerson.jsp?person=employee">Create Employee</a>
                </div>
            </div>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Address</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${employees.allEmployees}" var="emp">
                        <tr>
                            <td>${emp.id}</td>
                            <td>${emp.name}</td>
                            <td>${emp.address}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="card-footer small text-muted"></div>
    </div>
</c:set>

<t:base activeItem="employees">
    <jsp:body>
        ${content}
    </jsp:body>
</t:base>