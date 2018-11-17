<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="createEmployer" class="main.java.controller.EmployerController"/>

<c:set var="content">
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="#">Employer</a>
        </li>
        <li class="breadcrumb-item active">Create</li>
    </ol>
    <div class="container">
        <div class="card card-register mx-auto mt-5">
            <div class="card-header">Register an Employee</div>
            <div class="card-body">
                <form action="createEmployer.jsp" method="post">
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <div class="form-label-group">
                                    <input type="text" name="firstName" id="firstName" class="form-control" placeholder="First name"
                                           required="required" autofocus="autofocus">
                                    <label for="firstName">First name</label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-label-group">
                                    <input type="text" name="lastName" id="lastName" class="form-control" placeholder="Last name"
                                           required="required">
                                    <label for="lastName">Last name</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-label-group">
                            <input type="email" name="email" id="email"  class="form-control" placeholder="Email address"
                                   required="required">
                            <label for="email">Email address</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-label-group">
                            <input type="text" name="street" id="street" class="form-control" placeholder="Street"
                                   required="required">
                            <label for="street">Street</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-4">
                                <div class="form-label-group">
                                    <input type="text" name="zip" id="zip" class="form-control" placeholder="Zip"
                                           required="required" autofocus="autofocus">
                                    <label for="zip">Zip</label>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <div class="form-label-group">
                                    <input type="text" name="city" id="city" class="form-control" placeholder="City"
                                           required="required">
                                    <label for="city">City</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-label-group">
                            <input type="text" name="country" id="country" class="form-control" placeholder="Country"
                                   required="required">
                            <label for="country">Country</label>
                        </div>
                    </div>
                    <button class="btn btn-primary btn-block" type="submit">Register</button>
                </form>
            </div>
        </div>
    </div>


    <% createEmployer.createEmployer(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("street"), request.getParameter("zip"), request.getParameter("city"), request.getParameter("country")); %>
</c:set>

<t:base>
    <jsp:body>
        ${content}
    </jsp:body>
</t:base>