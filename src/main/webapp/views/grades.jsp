<%-- 
    Document   : grades
    Created on : 6/06/2019, 06:42:43 PM
    Author     : fernando
--%>


<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.academik.mvc.dao.GradeDAO"%>
<%@page import="com.academik.mvc.model.Grade"%>
<%@page import="com.academik.mvc.dao.CourseDAO"%>
<%@page import="com.academik.mvc.dao.StudentDAO"%>
<%@page import="com.academik.mvc.model.Course"%>
<%@page import="com.academik.mvc.model.Student"%>
<%@page import="java.util.InputMismatchException"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../templates/header.jsp">
    <jsp:param name="custom-title" value="Academik | Agregar nota"/>
</jsp:include>

<%
    StudentDAO sdao = new StudentDAO();
    List<Student> list_of_students = sdao.queryAll();
    CourseDAO cdao = new CourseDAO();
    List<Course> list_of_courses = cdao.queryAll();
%>

<h1>Agregar notas</h1>
<div class="container">
    <form method="POST">
        <div class="form-group">
            <label for="student">Estudiante</label>
            <select name="student">
                <% for (Student s : list_of_students) {%>
                <option value="<%= s.getCode()%>"><%= s.getFullName()%></option>
                <%}%>
            </select>
        </div>
        <div class="form-group">
            <label for="course">Curso</label>
            <select name="course">
                <% for (Course c : list_of_courses) {%>
                <option value="<%= c.getCode()%>"><%= c.getName()%></option>
                <%}%>
            </select>
        </div>
        <div class="form-group">
            <label for="grade">Nota</label>
            <input type="number" min="0" max="100" step="0.01" name="grade">
        </div>
        <input class="btn btn-primary" type="submit" value="Guardar"/>
    </form>
</div>

<%
    long scode;
    long ccode;
    double sgrade;

    final String DRIVER = "org.postgresql.Driver";
    final String HOST = "localhost";
    final String DATA_BASE = "Education";
    final int PORT = 5432;
    final String URL = "jdbc:postgresql://" + HOST + ':' + PORT + '/' + DATA_BASE;
    final String USER = "postgres";
    final String PASS = "admin";
    
    Class.forName(DRIVER);
    Connection conn = DriverManager.getConnection(URL, USER, PASS);
    
    if (request.getParameter("student") != null
            && request.getParameter("course") != null
            && request.getParameter("grade") != null) {
        try {
            Grade grade = new Grade();
            
            scode = Long.parseLong(request.getParameter("student"));
            grade.setStudentCode(scode);
            
            ccode = Long.parseLong(request.getParameter("course"));
            grade.setCourseCode(ccode);
            
            sgrade = Double.parseDouble(request.getParameter("grade"));
            grade.setGrade(sgrade);
            
            PreparedStatement stmnt = conn.prepareStatement(""
                    + "INSERT INTO grade"
                    + "(student_code,course_code,grade)"
                    + "VALUES(?,?,?)");
            stmnt.setLong(1, grade.getStudentCode());
            stmnt.setLong(2,grade.getCourseCode());
            stmnt.setDouble(3, grade.getGrade());
            stmnt.execute();

        } catch (InputMismatchException ex) {
            ex.printStackTrace();
        }
    }


%>
<%@include file="../templates/footer.jsp" %>