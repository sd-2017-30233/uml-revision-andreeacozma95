import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.util.Assert;

public class CourseDAO {

	private Connection connection;
	private PreparedStatement preparedStatement;
	
	private DataSource ds;

	public CourseDAO(DataSource ds) {
		this.ds = ds;
	}
	
	public CourseDAO(){
		
	}
	
	public Course getCourse(int idCourse) 
	{
		String query = "SELECT * FROM Course WHERE id_course=?" ;
		ResultSet rs = null;
		Course Course = null;
		
	
			try {
				
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, idCourse);
				
				rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					 String courseName = rs.getString("course_name");
			         String professorName = rs.getString("professor_name");
			         int studyYear = rs.getInt("study_year");
			         int numberOfStudents = rs.getInt("number_of_students");
			         
			         Course = new Course(idCourse,courseName,professorName,studyYear,numberOfStudents);
				}	
				
				rs.close();
			
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return Course;
	}
	
	public void create (Course course)
	{
		Assert.notNull(course);
		
		PreparedStatement preparedStatement = null;
		String query = "insert into Course(id_course,course_name,professor_name,study_year,number_of_students)"
				+ " values(?, ?,?,?,?) ON DUPLICATE KEY UPDATE course_name=?,professor_name=?,study_year=?,number_of_students=?" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,course.getIdCourse());
				preparedStatement.setString(2, course.getCourseName());
				preparedStatement.setString(3, course.getProfessorName());
				preparedStatement.setInt(4, course.getStudy_year());
				preparedStatement.setInt(5, course.getNumberOfStudents());
				preparedStatement.setString(6, course.getCourseName());
				preparedStatement.setString(7, course.getProfessorName());
				preparedStatement.setInt(8, course.getStudy_year());
				preparedStatement.setInt(9, course.getNumberOfStudents());
				
				preparedStatement.executeUpdate();
				
		    }catch (SQLException e) {
		       System.out.println(e);
		    } finally {
		        if (preparedStatement != null)
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} 
		    }
	}
	
	public void update (Course course)
	{
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM Course WHERE id_course=?" ;
		ResultSet rs = null;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query,ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE);
				preparedStatement.setInt(1,course.getIdCourse());
				
				rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					rs.updateString(2, course.getCourseName());
					rs.updateString(3,course.getProfessorName());
					rs.updateInt(4, course.getStudy_year());
					rs.updateInt(5, course.getNumberOfStudents());
					rs.updateRow();
				}
				
				rs.close();
		    }catch (SQLException e) {
		       System.out.println(e);
		    } finally {
		        if (preparedStatement != null)
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} 
		    }
	}
	
	public void delete (Course course)
	{
		Assert.notNull(course);
		
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM Course WHERE id_course=?" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,course.getIdCourse());
				
				preparedStatement.executeUpdate();
				
			
		    }catch (SQLException e) {
		       System.out.println(e);
		    } finally {
		        if (preparedStatement != null)
					try {
						preparedStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} 
		    }
	}
	
	public static void main(String args[])
	{
		CourseDAO courseDAO = new CourseDAO();
		
		Course course = courseDAO.getCourse(3);
		System.out.println(course);
			
		//courseDAO.create(new Course(3,"LFT","Negrescu",3,20));
		//courseDAO.update(new Course(3,"LimbajeFT","Liviu",4,22));
		
		//courseDAO.delete(new Course(3,"LimbajeFT","Liviu",4,22));
		
	}
}
