package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.DBConnection;

public class Teacher extends Person{
	public Teacher() {
		super();
	}
	public Teacher(Teacher x) {
		super.setId(x.getId());
		super.setName(x.getName());
		super.setEmail(x.getEmail());
		super.setPhone(x.getPhone());
		super.setAddress(x.getAddress());
		super.setAid(x.getAid());
	}
	public static ArrayList<Teacher> load(Connection connection) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<Teacher> listTeachers = new ArrayList<Teacher>();
		Teacher tc =new Teacher();
		try {
			String query = "select * from teacher"; 
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				tc.setId(resultSet.getString(1));
				tc.setName(resultSet.getString(2));
				tc.setEmail(resultSet.getString(3));
				tc.setPhone(resultSet.getString(4));
				tc.setAddress(resultSet.getString(5));
				tc.setAid(resultSet.getString(6));
				
				listTeachers.add(new Teacher(tc));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listTeachers;
	}
	public static int Insert(Teacher tc, Connection conn) throws ClassNotFoundException, SQLException {
		
		int rs = 0;
		try  
		{
			String query = "Insert into teacher values ( ? , ? , ? , ? , ? , ?);";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, tc.getId());
			ps.setString(2, tc.getName());
			ps.setString(3, tc.getEmail());
			ps.setString(4, tc.getPhone());
			ps.setString(5, tc.getAddress());
			ps.setString(6, tc.getAid());
			
			rs = ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	public static int Edit(Teacher tc,Connection conn) throws ClassNotFoundException, SQLException {
		
		int rs = 0;
		try  
		{
			
			String query = "Update teacher set NAME = ?, EMAIL = ?, PHONE =?, ADDRESS =?, AID =? where TID = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, tc.getName());
			ps.setString(2, tc.getEmail());
			ps.setString(3, tc.getPhone());
			ps.setString(4, tc.getAddress());
			ps.setString(5, tc.getAid());
			ps.setString(6, tc.getId());
			
			rs = ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	public static int Del(String id,Connection conn) throws ClassNotFoundException, SQLException {
		
		int rs = 0;
		try  
		{
			PreparedStatement ps = null;
			String query = "delete from teacher where TID = ?";			
			ps = conn.prepareStatement(query);
			ps.setString(1,id);
			rs = ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	public static Teacher findTeacher(String id,Connection conn) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Teacher tc =new Teacher();
		try {
			String query = "select * from teacher where TID = ?"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				tc.setId(resultSet.getString(1));
				tc.setName(resultSet.getString(2));
				tc.setEmail(resultSet.getString(3));
				tc.setPhone(resultSet.getString(4));
				tc.setAddress(resultSet.getString(5));
				tc.setAid(resultSet.getString(6));
				return tc;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Teacher findTeacherofAID(String aid, Connection conn) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		Teacher tc = new Teacher();
		try {
			String query = "select * from Teacher where AID = ?"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, aid);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				tc.setId(resultSet.getString(1));
				tc.setName(resultSet.getString(2));
				tc.setEmail(resultSet.getString(3));
				tc.setPhone(resultSet.getString(4));
				tc.setAddress(resultSet.getString(5));
				tc.setAid(resultSet.getString(6));
				return tc;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String getTIDofUserName(String userName, Connection conn) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		String TID = "";
		try {
			String query = "select t.TID from Teacher t inner join Account a ON t.AID = a.AID where a.USERNAME = ?"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, userName);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				TID = resultSet.getString(1);
				return TID;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return TID;
	}
}
