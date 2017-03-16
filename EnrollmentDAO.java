import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.util.Assert;

public class EnrollmentDAO {
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	
	public List<Student> getStudentsEnrolledOnCourse(Course course) 
	{
		
		Assert.notNull(course);
		
		String query = "SELECT Student.id_student,age,first_name,last_name,email,birth_date,address FROM Student,Enrollment"
				+ " WHERE Student.id_student=Enrollment.id_student and Enrollment.id_course = ?" ;
		ResultSet rs = null;
		Student student = null;
		List<Student> students = new ArrayList(); 
		
			try {
				
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, course.getIdCourse());
				
				rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					 int idStudent = rs.getInt("id_student");
			         int age = rs.getInt("age");
			         String firstName = rs.getString("first_name");
			         String lastName = rs.getString("last_name");
			         String email = rs.getString("email");
			         java.util.Date birthDate = rs.getDate("birth_date");
			         String address = rs.getString("address");
			         
			         student = new Student(idStudent, age, firstName, lastName, email,birthDate,address);
			         students.add(student);
				}	
				
				rs.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return students;
	}
	
	public void enroll (Student student,Course course,int grade)
	{
		Assert.notNull(course);
		Assert.notNull(student);
		
		PreparedStatement preparedStatement = null;
		String query = "insert into Enrollment values(?, ?,?)" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,student.getIdStudent());
				preparedStatement.setInt(2, course.getIdCourse());
				preparedStatement.setInt(3, grade);
			
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
		EnrollmentDAO enrollementDAO = new EnrollmentDAO();
		StudentDAO studentDAO = new StudentDAO();
		CourseDAO courseDAO = new CourseDAO();
		
		
		Student student = studentDAO.getStudent(2);
		System.out.println(student);
			
		Course course = courseDAO.getCourse(2);
		System.out.println(course);
			
		List<Student> students = enrollementDAO.getStudentsEnrolledOnCourse(course);
		for (Student student2 : students) {
			System.out.println(student2);
		}
			
		enrollementDAO.enroll(student, course, 8);
			
		System.out.println("Student enrolled on course");
			
		students = enrollementDAO.getStudentsEnrolledOnCourse(course);
		for (Student student2 : students) {
			System.out.println(student2);
		}
	}
}
