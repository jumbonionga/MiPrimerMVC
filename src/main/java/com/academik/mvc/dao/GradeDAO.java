/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.academik.mvc.dao;

import static com.academik.mvc.dao.GeneralDAO.CONN_WRAPPER;
import com.academik.mvc.model.Grade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author fernando
 */

public class GradeDAO implements GeneralDAO<Grade> {

    @Override
    public void create(Grade grade) {
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(""
                    + "INSERT INTO grade"
                    + "(student_code,course_code,grade)"
                    + "VALUES(?,?,?)");
            stmnt.setLong(1, grade.getStudentCode());
            stmnt.setLong(2,grade.getCourseCode());
            stmnt.setDouble(3, grade.getGrade());
            stmnt.execute();
            
        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Grade> queryAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Grade findById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(long id, Grade element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}
