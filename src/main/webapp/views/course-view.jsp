<%-- 
    Document   : course-view
    Created on : 30/05/2019, 06:32:04 PM
    Author     : fernando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean 
    id="single_course" 
    scope="request" 
    class="com.academik.mvc.model.Course"/>
<jsp:include page="../templates/header.jsp">
    <jsp:param name="custom-title" value="Academik | ${single_course.name}"/>
</jsp:include>

<h1>Información del curso</h1>

    <div class="row">
        <div class="col">Nombre</div>
        <div class="col">${single_course.name}</div>
    </div>
    <div class="row">
        <div class="col">Descripción</div>
        <div class="col">${single_course.description}</div>
    </div>
    <div class="row">
        <div class="col">Créditos</div>
        <div class="col">${single_course.credits}</div>
    </div>
    
    <a class="btn btn-primary" href="edit?id=${single_course.code}">Editar</a>
    <input class="btn btn-danger" type="submit" form="form-to-delete" value="Eliminar">
    <form method="POST" name="form-to-delete" id="form-to-delete">
        <input type="hidden" name="_method" value="DELETE"/>
        <input type="hidden" name="code" value="${single_course.code}"/>
    </form>

<%@include file="../templates/footer.jsp" %>