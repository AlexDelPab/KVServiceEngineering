<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="test" class="main.java.Test"/>

<c:set var="content">
    <h1>Hello <%= test.getTestString() %>
    </h1>
    <form action="samplePost.jsp" method="GET">
        First Name: <input type="text" name="first_name">
        <br/>
        Last Name: <input type="text" name="last_name"/>
        <input type="submit" value="Submit"/>
    </form>

    <b>First Name:</b><%= request.getParameter("first_name")%><br/>
    <b>Last Name:</b><%= request.getParameter("last_name")%>

    <h1><%= test.getPostData(request.getParameter("last_name")) %>
    </h1>
</c:set>

<t:base>
    <jsp:body>
        ${content}
    </jsp:body>
</t:base>