package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Student extends Person{
	private boolean gender;
	private Date dateOfBirth;
	private String fid; // Khoa của sinh viên
	public Student() {
		super();
	}
	public Student(Student x) {
		super.setId(x.getId());
		super.setName(x.getName());
		super.setEmail(x.getEmail());
		super.setPhone(x.getPhone());
		super.setAddress(x.getAddress());
		super.setAid(x.getAid());
		this.gender = x.isGender();
		this.dateOfBirth = x.dateOfBirth;
		this.fid = x.fid;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() { 
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	// Tải lại 
	public static ArrayList<Student> load(Connection connection) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		ArrayList<Student> listStudents = new ArrayList<Student>();
		Student sd =new Student();
		try {
			String query = "select * from Student"; 
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				sd.setId(resultSet.getString(1));
				sd.setName(resultSet.getString(2));
				sd.setGender(resultSet.getBoolean(3));
				sd.setDateOfBirth(resultSet.getDate(4));
				sd.setEmail(resultSet.getString(5));
				sd.setPhone(resultSet.getString(6));
				sd.setAddress(resultSet.getString(7));
				sd.setAid(resultSet.getString(8));
				sd.setFid(resultSet.getString(9));

				listStudents.add(new Student(sd));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listStudents;
	}
	// Thêm
	public static int Insert(Student sd, Connection conn) throws ClassNotFoundException, SQLException {

		int rs = 0;
		try  
		{
			String query = "Insert into Student values ( ? , ? , ? , ? , ? , ?, ?, ?, ?);";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, sd.getId());
			ps.setString(2, sd.getName());
			ps.setBoolean(3, sd.isGender());
			ps.setDate(4, new java.sql.Date(sd.getDateOfBirth().getTime()));
			ps.setString(5, sd.getEmail());
			ps.setString(6, sd.getPhone());
			ps.setString(7, sd.getAddress());
			ps.setString(8, sd.getAid());
			ps.setString(9, sd.getFid());

			rs = ps.executeUpdate();

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	// Sửa 
	public static int Edit(Student sd,Connection conn) throws ClassNotFoundException, SQLException {
		int rs = 0;
		try  
		{
			String query = "Update Student set NAME = ?, GENDER = ?, DATEOFBIRTH = ?, EMAIL = ?, PHONE =?, ADDRESS =?, AID = ? , FID = ? where SID = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, sd.getName());
			ps.setBoolean(2, sd.isGender());
			ps.setDate(3, new java.sql.Date(sd.getDateOfBirth().getTime()));
			ps.setString(4, sd.getEmail());
			ps.setString(5, sd.getPhone());
			ps.setString(6, sd.getAddress());
			ps.setString(7, sd.getAid());
			ps.setString(8, sd.getFid());
			ps.setString(9, sd.getId());

			rs = ps.executeUpdate();

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	// Xóa
	public static int Del(String id,Connection conn) throws ClassNotFoundException, SQLException {

		int rs = 0;
		try  
		{
			PreparedStatement ps = null;
			String query = "delete from Student where SID = ?";			
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
	// tìm sinh viên theo ID
	public static Student findStudent(String id,Connection conn) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Student sd =new Student();
		try {
			String query = "call test.find_student(?);"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				sd.setId(resultSet.getString(1));
				sd.setName(resultSet.getString(2));
				sd.setGender(resultSet.getBoolean(3));
				sd.setDateOfBirth(resultSet.getDate(4));
				sd.setEmail(resultSet.getString(5));
				sd.setPhone(resultSet.getString(6));
				sd.setAddress(resultSet.getString(7));
				sd.setAid(resultSet.getString(8));
				sd.setFid(resultSet.getString(9));
				return sd;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	// Tìm sinh viên theo thứ tự
	public static Student findStudentofAID(String aid, Connection conn) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		Student sd = new Student();
		try {
			String query = "select * from Student where AID = ?"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, aid);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				sd.setId(resultSet.getString(1));
				sd.setName(resultSet.getString(2));
				sd.setGender(resultSet.getBoolean(3));
				sd.setDateOfBirth(resultSet.getDate(4));
				sd.setEmail(resultSet.getString(5));
				sd.setPhone(resultSet.getString(6));
				sd.setAddress(resultSet.getString(7));
				sd.setAid(resultSet.getString(8));
				sd.setFid(resultSet.getString(9));
				return sd;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//
	public static String getSIDofUserName(String userName, Connection conn) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		String SID = "";
		try {
			String query = "select s.SID from Student s inner join Account a ON s.AID = a.AID where a.USERNAME = ?"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, userName);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				SID = resultSet.getString(1);
				return SID;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SID;
	}
}
