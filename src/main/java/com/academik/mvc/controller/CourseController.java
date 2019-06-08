/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.academik.mvc.controller;

import com.academik.mvc.dao.CourseDAO;
import com.academik.mvc.model.Course;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fernando
 */
@WebServlet("/courses/*")
public class CourseController extends HttpServlet {

    CourseDAO dao = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String complement = req.getPathInfo();
        if (complement == null) {
            complement = "";
        }
        System.err.println(complement);
        String redirectPage;
        switch (complement) {
            case "/create":
                redirectPage = "course-create.jsp";
                break;
            case "/view":
                long idToView = Long.parseLong(req.getParameter("id"));
                Course cToView = dao.findById(idToView);
                req.setAttribute("single_course", cToView);
                redirectPage = "course-view.jsp";
                break;
            case "/edit":
                long idToEdit = Long.parseLong(req.getParameter("id"));
                Course cToEdit = dao.findById(idToEdit);
                req.setAttribute("single_course", cToEdit);
                redirectPage = "course-edit.jsp";
                break;
            case "/list":
            case "/":
            case "":
                List<Course> allCourses = dao.queryAll();
                req.setAttribute("list_of_courses", allCourses);
                redirectPage = "course-list.jsp";
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/courses");
                return;
        }
        //Renderice este JSP
        RequestDispatcher rd = req.getRequestDispatcher(
                "/views/" + redirectPage
        );
        //Adelante con la renderización
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if ("DELETE".equals(req.getParameter("_method"))) {
            doDelete(req, resp);
            return;
        }
        if ("PUT".equals(req.getParameter("_method"))) {
            doPut(req, resp);
            return;
        }

        Course noob = new Course();

        noob.setName(req.getParameter("s_name"));
        noob.setDescription(req.getParameter("s_description"));
        noob.setCredits(Integer.parseInt(req.getParameter("s_credits")));

        dao.create(noob);

        resp.sendRedirect(req.getContextPath() + "/courses");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("code"));
        dao.delete(id);
        resp.sendRedirect(req.getContextPath() + "/courses");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Course edited = new Course();
        
        //Valores NUEVOS para las propiedades que vienen desde el formulario HTML
        edited.setName(req.getParameter("s_name"));
        edited.setDescription(req.getParameter("s_description"));
        edited.setCredits(Integer.parseInt(req.getParameter("s_credits")));
        
        //Utilizar el DAO para guardar la informacion en la base de datos
        dao.edit(Integer.parseInt(req.getParameter("code")), edited);
        resp.sendRedirect(req.getContextPath() + "/courses");
    }
    
    

}
