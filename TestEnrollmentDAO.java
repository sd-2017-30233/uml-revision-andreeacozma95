import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;

import org.junit.Test;

public class TestEnrollmentDAO {

    private Course course;
    private Student student;

    @Test(expected=IllegalArgumentException.class)

    public void nullCreateThrowsException() {

    	Course course = null;
    	Student student = null;
        new EnrollmentDAO().enroll(student, course, 10);

    }
  
    @Test

    public void enrollStudentOnCourse() {

    	StudentDAO studentDAO = new StudentDAO();
		CourseDAO courseDAO = new CourseDAO();
		
		student = studentDAO.getStudent(1);
		course = courseDAO.getCourse(2);
				
		EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        List<Student> students = enrollmentDAO.getStudentsEnrolledOnCourse(course);
        //assertFalse(students.contains(student)); 
        
        enrollmentDAO.enroll(student, course, 10);
        
        students = enrollmentDAO.getStudentsEnrolledOnCourse(course);
        assertTrue(students.contains(student));     
    }
}