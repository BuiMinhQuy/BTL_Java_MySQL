package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class Account {
	private String aid;
	private String userName;
	private String passWord;
	private int permission;
	private Date dateOfCreate;
	public Account() {
		
	}
	public Account(Account ac) {
		this.aid = ac.aid;
		this.userName = ac.userName;
		this.passWord = ac.passWord;
		this.permission = ac.permission;
		this.dateOfCreate = ac.dateOfCreate;
	}
	//số thứ tự tài khoản
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	// Tên người dùng
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	// Mật khẩu
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	//Loại tài khoản
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	// Ngày khởi tạo
	public Date getDateOfCreate() {
		return dateOfCreate;
	}
	public void setDateOfCreate(Date dateOfCreate) {
		this.dateOfCreate = dateOfCreate;
	}
	
	public static boolean checkLogin(String userName, String passWord, int permission, Connection conn) throws ClassNotFoundException, SQLException{
		String sql = "select * from Account where username = ? and password = ? and permission = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, userName);
		pstm.setString(2, passWord);
		pstm.setInt(3, permission);
		ResultSet rs = pstm.executeQuery();
		
		if (rs.next()) {
			return true;
		}
		return false;
	}
	public static ArrayList<Account> load(Connection connection) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		ArrayList<Account> listAccounts = new ArrayList<Account>();
		Account ac = new Account();
		try {
			String query = "select * from Account"; 
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ac.setAid(resultSet.getString(1));
				ac.setUserName(resultSet.getString(2));
				ac.setPassWord(resultSet.getString(3));
				ac.setPermission(resultSet.getInt(4));
				ac.setDateOfCreate(resultSet.getDate(5));

				listAccounts.add(new Account(ac));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listAccounts;
	}
	public static int Insert(Account ac, Connection conn) throws ClassNotFoundException, SQLException {

		int rs = 0;
		try  
		{
			String query = "Insert into Account values (?,?,?,?,?);";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, ac.getAid());
			ps.setString(2, ac.getUserName());
			ps.setString(3, ac.getPassWord());
			ps.setInt(4, ac.getPermission());
			ps.setDate(5,  new java.sql.Date(ac.getDateOfCreate().getTime()));

			rs = ps.executeUpdate();

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	public static int Edit(Account ac,Connection conn) throws ClassNotFoundException, SQLException {
		int rs = 0;
		try  
		{
			String query = "Update Account set USERNAME = ?, PASSWORD = ?, PERMISSION = ?, DATEOFCREATE = ? where AID = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, ac.getUserName());
			ps.setString(2, ac.getPassWord());
			ps.setInt(3, ac.getPermission());
			ps.setDate(4,  new java.sql.Date(ac.getDateOfCreate().getTime()));
			ps.setString(5, ac.getAid());
			rs = ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	public static int UpdatePassWord(String userName, String newPassword ,Connection conn) throws ClassNotFoundException, SQLException {
		int rs = 0;
		try  
		{
			String query = "Update Account set PASSWORD = ? where UserName = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, newPassword);
			ps.setString(2, userName);
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
			String query = "delete from Account where AID = ?";			
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

	public static Account findAccount(String id,Connection conn)  throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		Account ac =new Account();
		try {
			String query = "select * from Account where AID = ?"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				ac.setAid(resultSet.getString(1));
				ac.setUserName(resultSet.getString(2));
				ac.setPassWord(resultSet.getString(3));
				ac.setPermission(resultSet.getInt(4));
				ac.setDateOfCreate(resultSet.getDate(5));
				return ac;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Account findAccountofUserName(String userName,Connection conn)  throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		Account ac = new Account();
		String ID = "";
		try {
			String query = "select * from Account where USERNAME = ?"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, userName);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				ac.setAid(resultSet.getString(1));
				ac.setUserName(resultSet.getString(2));
				ac.setPassWord(resultSet.getString(3));
				ac.setPermission(resultSet.getInt(4));
				ac.setDateOfCreate(resultSet.getDate(5));
				return ac;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
	