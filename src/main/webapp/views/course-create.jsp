<%-- 
    Document   : course-create
    Created on : 30/05/2019, 06:04:37 PM
    Author     : fernando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../templates/header.jsp">
    <jsp:param name="custom-title" value="Academik | Crear curso"/>
</jsp:include>

<h1>Nuevo curso</h1>
    <form method="POST">
        <div class="form-group">
            <label for="s_name">Nombre</label>
            <input class="form-control" type="text" name="s_name"/>
        </div>
        <div class="form-group">
            <label for="s_description">Descripcion</label>
            <input class="form-control" type="text" name="s_description"/>
        </div>
        <div class="form-group">
            <label for="s_credits">Créditos</label>
            <input class="form-control" type="number" min="1" step="1" name="s_credits"/>
        </div>
        <input class="btn btn-primary" type="submit" value="Crear"/>
    </form>

<%@include file="../templates/footer.jsp" %>