package com.academik.mvc.dao;

import com.academik.mvc.model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de todas las operaciones de base de datos referentes a la
 * clase Student.
 *
 * @author esvux
 */
public class StudentDAO implements GeneralDAO<Student> {

    /**
     * Método que se encarga de traer los objetos Student 
     * desde la base de datos.
     *
     * @return
     */
    @Override
    public List<Student> queryAll() {
        List<Student> temp = new ArrayList<>();
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            Statement stmnt = conn.createStatement();
            ResultSet result = stmnt.executeQuery("SELECT code, first_name, last_name, email, contact_phone, guardian FROM student");
            while (result.next()) {
                Student s = new Student();
                s.setCode(result.getLong("code"));
                s.setFirstName(result.getString("first_name"));
                s.setLastName(result.getString("last_name"));
                s.setEmail(result.getString("email"));
                s.setContactPhone(result.getString("contact_phone"));
                s.setGuardian(result.getString("guardian"));
                temp.add(s);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return temp;
    }

    @Override
    public Student findById(long id) {
        Student s = null;
        try {
            Connection conn = CONN_WRAPPER.getConnection();

            //Esto es usando Statement y concatencaciones de texto
            //"SELECT * FROM student WHERE code = " + id
            //"SELECT * FROM student WHERE last_name = '" + lastName + "'"
            //"SELECT * FROM student WHERE last_name = 'O';DROP TABLE student;--connor'"
            //Esto es usando PreparedStatement
            //"SELECT * FROM student WHERE code = ?"            
            PreparedStatement stmnt = conn.prepareStatement("SELECT code, first_name, last_name, gender, email, contact_phone, guardian, birthday FROM student WHERE code = ?");
            stmnt.setLong(1, id);

            ResultSet result = stmnt.executeQuery();
            if (result.next()) {
                s = new Student();
                s.setCode(result.getLong("code"));
                s.setFirstName(result.getString("first_name"));
                s.setLastName(result.getString("last_name"));
                s.setGender(result.getString("gender"));
                s.setEmail(result.getString("email"));
                s.setContactPhone(result.getString("contact_phone"));
                s.setGuardian(result.getString("guardian"));
                s.setBirthday(result.getDate("birthday"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        return s;
    }

    @Override
    public void create(Student noob) {
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(
                "INSERT INTO student"
              + " (first_name, last_name, gender, email, contact_phone, guardian, birthday)"
              + " VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            stmnt.setString(1, noob.getFirstName());
            stmnt.setString(2, noob.getLastName());
            stmnt.setString(3, noob.getGender());
            stmnt.setString(4, noob.getEmail());
            stmnt.setString(5, noob.getContactPhone());
            stmnt.setString(6, noob.getGuardian());
            stmnt.setTimestamp(7, Timestamp.from(noob.getBirthday().toInstant()));
            stmnt.execute();
        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void edit(long id, Student edited) {
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(
                "UPDATE student SET "
              + " first_name = ?,"
              + " last_name = ?,"
              + " gender = ?,"
              + " email = ?,"
              + " contact_phone = ?,"
              + " guardian = ?,"
              + " birthday = ?"
              + " WHERE code = ?"
            );
            stmnt.setString(1, edited.getFirstName());
            stmnt.setString(2, edited.getLastName());
            stmnt.setString(3, edited.getGender());
            stmnt.setString(4, edited.getEmail());
            stmnt.setString(5, edited.getContactPhone());
            stmnt.setString(6, edited.getGuardian());
            stmnt.setTimestamp(7, Timestamp.from(edited.getBirthday().toInstant()));
            stmnt.setLong(8, id);
            stmnt.execute();
        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

}
