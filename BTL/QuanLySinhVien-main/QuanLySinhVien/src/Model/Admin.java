package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Admin extends Person{
	public Admin() {
		super();
	}
	public Admin(Admin x) {
		super.setId(x.getId());
		super.setName(x.getName());
		super.setEmail(x.getEmail());
		super.setPhone(x.getPhone());
		super.setAddress(x.getAddress());
		super.setAid(x.getAid());
	}
	public static ArrayList<Admin> load(Connection connection) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<Admin> listAdmins = new ArrayList<Admin>();
		Admin ad =new Admin();
		try {
			String query = "select * from Admin"; 
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ad.setId(resultSet.getString(1));
				ad.setName(resultSet.getString(2));
				ad.setEmail(resultSet.getString(3));
				ad.setPhone(resultSet.getString(4));
				ad.setAddress(resultSet.getString(5));
				ad.setAid(resultSet.getString(6));
				
				listAdmins.add(new Admin(ad));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listAdmins;
	}
	// thêm 
	public static int Insert(Admin ad, Connection conn) throws ClassNotFoundException, SQLException {
		
		int rs = 0;
		try  
		{
			String query = "Insert into Admin values ( ? , ? , ? , ? , ? , ?);";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, ad.getId());
			ps.setString(2, ad.getName());
			ps.setString(3, ad.getEmail());
			ps.setString(4, ad.getPhone());
			ps.setString(5, ad.getAddress());
			ps.setString(6, ad.getAid());
			
			rs = ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	// sửa
	public static int Edit(Admin ad,Connection conn) throws ClassNotFoundException, SQLException {
		
		int rs = 0;
		try  
		{
			
			String query = "Update Admin set NAME = ?, EMAIL = ?, PHONE =?, ADDRESS =?, AID =? where ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, ad.getName());
			ps.setString(2, ad.getEmail());
			ps.setString(3, ad.getPhone());
			ps.setString(4, ad.getAddress());
			ps.setString(5, ad.getAid());
			ps.setString(6, ad.getId());
			
			rs = ps.executeUpdate();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	// xóa
	public static int Del(String id,Connection conn) throws ClassNotFoundException, SQLException {
		
		int rs = 0;
		try  
		{	// ngôn ngữ truy vấn đến các tham số trong mysql
			PreparedStatement ps = null;
			String query = "delete from Admin where ID = ?";			
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
	// tìm kiếm admin theo mã số
	public static Admin findAdmin(String id,Connection conn) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Admin ad = new Admin();
		try {
			String query = "select * from Admin where ID = ?"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				ad.setId(resultSet.getString(1));
				ad.setName(resultSet.getString(2));
				ad.setEmail(resultSet.getString(3));
				ad.setPhone(resultSet.getString(4));
				ad.setAddress(resultSet.getString(5));
				ad.setAid(resultSet.getString(6));
				return ad;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	// tìm kiếm admin theo thứ tự 
	public static Admin findAdminofAID(String aid, Connection conn) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		Admin ad = new Admin();
		try {
			String query = "select * from Admin where AID = ?"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, aid);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				ad.setId(resultSet.getString(1));
				ad.setName(resultSet.getString(2));
				ad.setEmail(resultSet.getString(3));
				ad.setPhone(resultSet.getString(4));
				ad.setAddress(resultSet.getString(5));
				ad.setAid(resultSet.getString(6));
				return ad;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
