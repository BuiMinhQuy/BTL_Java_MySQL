package DAO;
// duy oc chó 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection initializeDatabase() throws SQLException{
		// Initialize all the information regarding 
		// Database Connection
		
		String dbDriver = "com.mysql.cj.jdbc.Driver";
		String dbURL = "jdbc:mysql://localhost:3306"; 
		// Database name to access 
		String dbName = "QuanLySinhVien"; 
//		String dbName = "QL"; 
		//String dbUsername = "root";
		//String dbPassword = "12345"; 
		String dbUsername = "root";
		String dbPassword = "root"; 
		String connectionURL = dbURL + "/" + dbName;
		Connection conn = null;
		try { 
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(connectionURL, dbUsername, dbPassword);
			System.out.println("connect successfully!");
		}catch (Exception ex) {
			System.out.println("connect failure!" ) ; 
			ex.printStackTrace();
		}
		
		return conn; 
	}
}