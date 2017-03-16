import java.sql.*;

import javax.sql.DataSource;

import org.springframework.util.Assert;

public class StudentDAO {

	private Connection connection;
	private PreparedStatement preparedStatement;
	
    private DataSource ds;

    public StudentDAO(DataSource ds) {

        this.ds = ds;
    }
    
    public StudentDAO()
    {
    	
    }
	public Student getStudent(int idStudent) 
	{
		String query = "SELECT * FROM Student WHERE id_student=?" ;
		ResultSet rs = null;
		Student student = null;
		
			try 
			{			
				connection = ConnectionDB.getInstance().getConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, idStudent);
				
				rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
			         int age = rs.getInt("age");
			         String firstName = rs.getString("first_name");
			         String lastName = rs.getString("last_name");
			         String email = rs.getString("email");
			         java.util.Date birthDate = new Date(rs.getDate("birth_date").getTime());
			         String address = rs.getString("address");
			         
			         student = new Student(idStudent, age, firstName, lastName, email,birthDate,address);
				}	
				
				rs.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return student;
	}
	
	public void create (Student student)
	{
		Assert.notNull(student);
		
		PreparedStatement preparedStatement = null;
		String query = "insert into Student(id_student,age,first_name,last_name,email,birth_date,address) "
				+ "values(?, ?,?,?,?,?,?) ON DUPLICATE KEY UPDATE age=?,first_name=?,last_name=?,email=?,birth_date=?,"
				+ "address=?" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,student.getIdStudent());
				preparedStatement.setInt(2, student.getAge());
				preparedStatement.setString(3, student.getFirstName());
				preparedStatement.setString(4, student.getLastName());
				preparedStatement.setString(5, student.getEmail());
				preparedStatement.setDate(6, new java.sql.Date(student.getBirthDate().getTime()));
				preparedStatement.setString(7, student.getAddress());
				preparedStatement.setInt(8, student.getAge());
				preparedStatement.setString(9, student.getFirstName());
				preparedStatement.setString(10, student.getLastName());
				preparedStatement.setString(11, student.getEmail());
				preparedStatement.setDate(12, new java.sql.Date(student.getBirthDate().getTime()));
				preparedStatement.setString(13, student.getAddress());
				
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
	
	public void update (Student student)
	{
		
		if (student==null)
			return;
		
		PreparedStatement preparedStatement = null;
		String query = "SELECT * FROM Student WHERE id_student=?" ;
		ResultSet rs = null;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query,ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE);
				preparedStatement.setInt(1,student.getIdStudent());
				
				rs = preparedStatement.executeQuery();
				
				while(rs.next())
				{
					rs.updateInt(2, student.getAge());
					rs.updateString(3, student.getFirstName());
					rs.updateString(4,student.getLastName());
					rs.updateString(5, student.getEmail());
					rs.updateDate(6, new java.sql.Date(student.getBirthDate().getTime()));
					rs.updateString(7, student.getAddress());
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
	
	public void delete (Student student)
	{
		
		Assert.notNull(student);
		
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM Student WHERE id_student=?" ;
		
		    try {
		    	connection = ConnectionDB.getInstance().getConnection();
				
		    	preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,student.getIdStudent());
				
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
		StudentDAO studentDAO = new StudentDAO();
		
		Student student = studentDAO.getStudent(3);
		System.out.println(student);
			
		//studentDAO.create(new Student(3,23,"Ion","Pop","ionp@yahoo.com",new Date(100000),"Cj"));
		//studentDAO.update(new Student(3,23,"I","P","ip@yahoo.com",new Date(100000),"Cluj-Napoca"));
			
		//studentDAO.delete(new Student(3,23,"Ion","Pop","ionp@yahoo.com",new Date(100000),"Cluj-Napoca"));
		
	}
}
