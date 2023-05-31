package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Faculty{
	private String fid;
	private String name;

	public Faculty() {

	}
	public Faculty(Faculty x) {
		this.fid = x.fid;
		this.name = x.name;
	}

	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static ArrayList<Faculty> load(Connection connection) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		ArrayList<Faculty> listFacultys = new ArrayList<Faculty>();
		try {
			String query = "select * from Faculty"; 
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Faculty sd =new Faculty();
				sd.setFid(resultSet.getString(1));
				sd.setName(resultSet.getString(2));

				listFacultys.add(new Faculty(sd));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listFacultys;
	}

	public static Faculty findFaculty(String id,Connection conn) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		Faculty sd =new Faculty();
		try {
			String query = "select * from Faculty where FID = ?"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				sd.setFid(resultSet.getString(1));
				sd.setName(resultSet.getString(2));
				return sd;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
