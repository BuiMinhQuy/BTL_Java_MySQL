package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Room {
	private String rid;
	private String name;
	private String area;
	public Room() {
		
	}
	public Room(Room x) {
		this.rid = x.rid;
		this.name = x.name;
		this.area = x.area;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public static ArrayList<Room> load(Connection connection) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		ArrayList<Room> listRooms = new ArrayList<Room>();
		Room r = new Room();
		try {
			String query = "select * from Room"; 
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				r.setRid(resultSet.getString(1));
				r.setName(resultSet.getString(2));
				r.setArea(resultSet.getString(3));

				listRooms.add(new Room(r));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listRooms;
	}
}
