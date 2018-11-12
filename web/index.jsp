<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="test" class="main.java.Test"/>

<t:template>
    <jsp:body>
        <h1>Hello <% test.getTestString(); %></h1>
        <p>Hi I'm the heart of the message</p>
    </jsp:body>
</t:template>