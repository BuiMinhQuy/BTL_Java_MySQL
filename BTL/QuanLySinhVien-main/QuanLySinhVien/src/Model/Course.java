package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Course {
	private String cid;
	private String name;
	private String description;
	private int numOfCredits;
	public Course() {
		
	}
	public Course(Course x) {
		this.cid = x.cid;
		this.name = x.name;
		this.description = x.description;
		this.numOfCredits = x.numOfCredits;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNumOfCredits() {
		return numOfCredits;
	}
	public void setNumOfCredits(int numOfCredits) {
		this.numOfCredits = numOfCredits;
	}
	public static ArrayList<Course> load(Connection connection) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		ArrayList<Course> listCourses = new ArrayList<Course>();
		Course cs =new Course();
		try {
			String query = "select * from Course"; 
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				cs.setCid(resultSet.getString(1));
				cs.setName(resultSet.getString(2));
				cs.setDescription(resultSet.getString(3));
				cs.setNumOfCredits(resultSet.getInt(4));
				
				listCourses.add(new Course(cs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCourses;
	}
	public static int Insert(Course cs, Connection conn) throws ClassNotFoundException, SQLException {

		int rs = 0;
		try  
		{
			String query = "Insert into Course values ( ? , ? , ? , ?);";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, cs.getCid());
			ps.setString(2, cs.getName());
			ps.setString(3, cs.getDescription());
			ps.setInt(4, cs.getNumOfCredits());

			rs = ps.executeUpdate();

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	public static int Edit(Course cs,Connection conn) throws ClassNotFoundException, SQLException {
		int rs = 0;
		try  
		{
			String query = "Update Course set NAME = ?, DESCRIPTION = ?, NUMBEROFCREDITS = ? where CID = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, cs.getName());
			ps.setString(2, cs.getDescription());
			ps.setInt(3, cs.getNumOfCredits());
			ps.setString(4, cs.getCid());

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
			String query = "delete from Course where CID = ?";			
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

	public static Course findCourse(String id,Connection conn) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Course cs =new Course();
		try {
			String query = "call test.find_course_cid(?);"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				cs.setCid(resultSet.getString(1));
				cs.setName(resultSet.getString(2));
				cs.setDescription(resultSet.getString(3));
				cs.setNumOfCredits(resultSet.getInt(4));
				
				return cs;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
