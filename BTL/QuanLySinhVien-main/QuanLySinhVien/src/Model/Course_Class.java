package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Course_Class {
	private String cid;
	private String ccid;
	private String rid;
	private String tid;
	private boolean status;
	private int semester;
	private String description;
	public Course_Class() {
		
	}
	public Course_Class(Course_Class x) {
		this.cid = x.cid;
		this.ccid = x.ccid;
		this.rid = x.rid;
		this.tid = x.tid;
		this.status = x.status;
		this.semester = x.semester;
		this.description = x.description;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCcid() {
		return ccid;
	}
	public void setCcid(String ccid) {
		this.ccid = ccid;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static ArrayList<Course_Class> load(Connection connection) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<Course_Class> listCourse_Classs = new ArrayList<Course_Class>();
		Course_Class css =new Course_Class();
		try {
			String query = "select * from Course_Class"; 
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				css.setCid(resultSet.getString(1));
				css.setCcid(resultSet.getString(2));
				css.setRid(resultSet.getString(3));
				css.setTid(resultSet.getString(4));
				css.setStatus(resultSet.getBoolean(5));
				css.setSemester(resultSet.getInt(6));
				css.setDescription(resultSet.getString(7));
				
				listCourse_Classs.add(new Course_Class(css));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCourse_Classs;
	}
	public static int Insert(Course_Class css, Connection conn) throws ClassNotFoundException, SQLException {
		
		int rs = 0;
		try  
		{
			String query = "Insert into Course_Class values ( ? , ? , ? , ? , ? , ?, ?);";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, css.getCid());
			ps.setString(2, css.getCcid());
			ps.setString(3, css.getRid());
			ps.setString(4, css.getTid());
			ps.setBoolean(5, css.isStatus());
			ps.setInt(6, css.getSemester());
			ps.setString(7, css.getDescription());
			
			rs = ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	public static int Edit(Course_Class css,Connection conn) throws ClassNotFoundException, SQLException {
		
		int rs = 0;
		try  
		{
			
			String query = "Update Course_Class set CID = ?, RID = ?, TID = ?, STATUS =?, SEMESTER =?, DESCRIPTION =? where CCID = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, css.getCid());
			ps.setString(2, css.getRid());
			ps.setString(3, css.getTid());
			ps.setBoolean(4, css.isStatus());
			ps.setInt(5, css.getSemester());
			ps.setString(6, css.getDescription());
			ps.setString(7, css.getCcid());
			
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
			String query = "delete from Course_Class where CCID = ?";			
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
	
	public static Course_Class findCourse_Class(String id,Connection conn) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Course_Class css =new Course_Class();
		try {
			String query = "select * from Course_Class where CCID = ?"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				css.setCid(resultSet.getString(1));
				css.setCcid(resultSet.getString(2));
				css.setRid(resultSet.getString(3));
				css.setTid(resultSet.getString(4));
				css.setStatus(resultSet.getBoolean(5));
				css.setSemester(resultSet.getInt(6));
				css.setDescription(resultSet.getString(7));
				return css;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String getCCIDCourse_Class(String cid, Connection conn) throws ClassNotFoundException, SQLException {
		String ccid = "";
		try {
			ArrayList<Course_Class> listCourse_Classs = new ArrayList<Course_Class>();
			String query = "select * from Course_Class where CID = ?"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, cid);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Course_Class css = new Course_Class();
				css.setCid(resultSet.getString(1));
				css.setCcid(resultSet.getString(2));
				css.setRid(resultSet.getString(3));
				css.setTid(resultSet.getString(4));
				css.setStatus(resultSet.getBoolean(5));
				css.setSemester(resultSet.getInt(6));
				css.setDescription(resultSet.getString(7));
				
				listCourse_Classs.add(css);
			}
			int id = 0;
			for(int i=0;i<listCourse_Classs.size();i++) {
				int index = listCourse_Classs.get(i).getCcid().lastIndexOf("_");
				if(index != -1) {
					int value = Integer.valueOf(listCourse_Classs.get(i).getCcid().substring(index+1));
					if(value > id) {
						id = value;
					}
				}
			}
			id++;
			if(id < 10) {
				return cid + "_0" + String.valueOf(id);
			}
			else {
				return cid + "_" + String.valueOf(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ccid;
	}
	public static String getNameCourseClass(Course_Class css, Connection conn) throws ClassNotFoundException, SQLException {
		String name = "";
		try {
			String query = "select NAME from Course where CID = ?"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, css.getCid());
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				name = resultSet.getString(1);
				int index = css.getCcid().lastIndexOf("_");
				if(index != -1) {
					name = name + " Lớp " + css.getCcid().substring(index+1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	public static boolean existsCourseTeacher(String ccid, Connection conn) throws ClassNotFoundException, SQLException {
		try {
			String query = "select TID from Course_Class where CCID = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,ccid);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				String tid = resultSet.getString(1);
				if(tid == null || tid.equals("") == true) {
					return false;
				}
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
