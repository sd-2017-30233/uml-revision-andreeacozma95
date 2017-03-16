import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDB {
	
	private static ConnectionDB instance;
	Connection connection;
	
    private String url="jdbc:mysql://localhost/university";
    private String username="root";
    private String password="";

    private ConnectionDB(){
    	
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ConnectionDB getInstance()
    {
    	if (instance == null)
    		instance = new ConnectionDB();
    	return instance;
    }
    
    public  Connection getConnection()
    {
	    if(connection==null)
	    {
	        try {
	            connection = DriverManager.getConnection( url, username, password );
	        } catch (SQLException ex) {
	            System.out.println("Database connection failed!");
	        }
	    }

	    return connection;
    }
    
    public void closeConnection()
    {
    	if(connection!=null)
	    {
    		try {
				connection.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
	    }	
    }
}