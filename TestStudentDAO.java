import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.junit.Before;

import org.junit.Test;


public class TestStudentDAO {

    private Student student;

    @Before

    public void setUp() throws Exception {

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = " 1996-02-11";
        Date dateObject = sdf.parse(dateString);
        
        student = new Student(4,24,"Ioana","Marinescu","imarinescu@yahoo.com",dateObject,"CLuj-N");

    }

    @Test(expected=IllegalArgumentException.class)

    public void nullCreateThrowsException() {

        new StudentDAO().create(null);

    }
    
    @Test(expected=IllegalArgumentException.class)

    public void nullDeleteThrowsException() {

        new StudentDAO().delete(null);

    }


    @Test

    public void createAndRetrieveStudent() throws Exception {

        StudentDAO dao = new StudentDAO();

        dao.create(student);

        Student r = dao.getStudent(student.getIdStudent());

        assertEquals(r, student);
       
    }
    
    @Test

    public void updateAndRetrieveStudent() throws Exception {

        StudentDAO dao = new StudentDAO();

        dao.update(student);

        Student r = dao.getStudent(student.getIdStudent());

        assertEquals(r, student);
    }
    
    @Test

    public void deleteAndRetrieveStudent() throws Exception {

        StudentDAO dao = new StudentDAO();

        dao.delete(student);

        Student r = dao.getStudent(student.getIdStudent());

        assertEquals(r, null);
    }
}