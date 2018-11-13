<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="index" class="main.java.Index"/>

<c:set var="content">
    <h1>Hello <%= index.getTestString() %></h1>
</c:set>

<t:base>
    <jsp:body>
        ${content}
    </jsp:body>
</t:base>