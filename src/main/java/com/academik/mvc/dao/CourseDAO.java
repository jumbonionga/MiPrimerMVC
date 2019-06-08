/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.academik.mvc.dao;

import static com.academik.mvc.dao.GeneralDAO.CONN_WRAPPER;
import com.academik.mvc.model.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando
 */
public class CourseDAO implements GeneralDAO<Course>{

    @Override
    public List<Course> queryAll() {
        List<Course> temp = new ArrayList<>();
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            Statement stmnt = conn.createStatement();
            ResultSet result = stmnt.executeQuery("SELECT code, name, description, credits FROM course");
            while(result.next()) {
                Course c = new Course();
                c.setCode(result.getLong("code"));
                c.setName(result.getString("name"));
                c.setDescription(result.getString("description"));
                c.setCredits(result.getInt("credits"));
                temp.add(c);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return temp;
        
    }

    @Override
    public Course findById(long id) {
        Course c = null;
        
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            
            PreparedStatement stmnt = conn.prepareStatement("SELECT code, name, description, credits FROM course WHERE code = ?");
            stmnt.setLong(1, id);
            
            ResultSet result = stmnt.executeQuery();
            
            if(result.next()) {
                c = new Course();
                c.setCode(result.getLong("code"));
                c.setName(result.getString("name"));
                c.setDescription(result.getString("description"));
                c.setCredits(result.getInt("credits"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        return c;
    }

    @Override
    public void create(Course noob) {
       try {
           Connection conn = CONN_WRAPPER.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(
                "INSERT INTO course"
              + " (name, description, credits)"
              + " VALUES (?, ?, ?)"
            );
            stmnt.setString(1, noob.getName());
            stmnt.setString(2, noob.getDescription());
            stmnt.setInt(3, noob.getCredits());
            stmnt.execute();
           
       } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void edit(long id, Course edited) {
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(
                "UPDATE course SET "
              + " name = ?,"
              + " description = ?,"
              + " credits = ?"
              + " WHERE code = ?"
            );
            stmnt.setString(1, edited.getName());
            stmnt.setString(2, edited.getDescription());
            stmnt.setInt(3, edited.getCredits());
            int nid = (int)id;
            stmnt.setInt(4, nid);
            stmnt.execute();
        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            
            PreparedStatement stmnt = conn.prepareStatement(
                    "DELETE FROM course WHERE code = ?"
            );
            stmnt.setLong(1, id);
            stmnt.executeUpdate();
            
        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
}
