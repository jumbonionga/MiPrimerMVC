<%@page import="com.academik.mvc.model.Course"%>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../templates/header.jsp">
    <jsp:param name="custom-title" value="Academik | Cursos"/>
</jsp:include>

<jsp:useBean 
    id="list_of_courses"
    scope="request" 
    class="List<Course>" />

<table class="table">
    <thead>
        <tr>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Créditos</th>
        </tr>
    </thead>
    <tbody>
        <% for(Course c : list_of_courses) { %> 
        <tr>
            <td><%= c.getName() %></td>
            <td><%= c.getDescription() %></td>
            <td><%= c.getCredits() %></td>
            <td>
                <a class="btn btn-primary" href="courses/edit?id=<%= c.getCode() %>">Editar</a>
                <a class="btn btn-primary" href="courses/view?id=<%= c.getCode() %>">Ver</a>
            </td>
        </tr>
        <%}%>
    </tbody>
</table>

<%@include file="../templates/footer.jsp" %>