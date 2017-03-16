import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;


public class TestCourseDAO {

    private Course course;

    @Before

    public void setUp() throws Exception {   

        course = new Course(3,"LFT","Negrescu",3,20);     
    }

    @Test(expected=IllegalArgumentException.class)

    public void nullCreateThrowsException() {

        new CourseDAO().create(null);

    }
    
    @Test(expected=IllegalArgumentException.class)

    public void nullDeleteThrowsException() {

        new CourseDAO().delete(null);

    }

    @Test

    public void createAndRetrieveCourse() throws Exception {

    	CourseDAO dao = new CourseDAO();

        dao.create(course);

        Course r = dao.getCourse(course.getIdCourse());
        
        assertEquals(r, course);
    }
    
    @Test

    public void updateAndRetrieveCourse() throws Exception {

    	CourseDAO dao = new CourseDAO();

        dao.update(course);

        Course r = dao.getCourse(course.getIdCourse());

        assertEquals(r, course);
    }
    
    @Test

    public void deleteAndRetrieveCourse() throws Exception {

        CourseDAO dao = new CourseDAO();

        dao.delete(course);

        Course r = dao.getCourse(course.getIdCourse());

        assertEquals(r, null);
    }
}