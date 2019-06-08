<%-- 
    Document   : course-edit
    Created on : 30/05/2019, 07:02:37 PM
    Author     : fernando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean 
    id="single_course" 
    scope="request" 
    class="com.academik.mvc.model.Course"/>
<jsp:include page="../templates/header.jsp">
    <jsp:param name="custom-title" value="Academik | Editar estudiante"/>
</jsp:include>

<h1>Editar curso</h1>
<form method="POST">
    <input type="hidden" name="_method" value="PUT"/>
    <input type="hidden" name="code" value="${single_course.code}"/>
    <div class="form-group">
        <label for="s_name">Nombre</label>
        <input class="form-control" type="text" name="s_name" value="${single_course.name}"/>
    </div>
    <div class="form-group">
        <label for="s_description">Descripción</label>
        <input class="form-control" type="text" name="s_description" value="${single_course.description}"/>
    </div>
    <div class="form-group">
        <label for="s_credits">Créditos</label>
        <input class="form-control" type="number" name="s_credits" min="1" step="1" value="${single_course.credits}"/>
    </div>
    <input class="btn btn-primary" type="submit" value="Guardar"/>
</form>

<%@include file="../templates/footer.jsp" %>