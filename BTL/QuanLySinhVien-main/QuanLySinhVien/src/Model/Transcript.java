package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Transcript {
	private String ccid;
	private String sid;
	private float score;
	public Transcript() {
		this.score = -1;
	}
	public Transcript(Transcript x) {
		this.ccid = x.ccid;
		this.sid = x.sid;
		this.score = x.score;
	}
	public String getCcid() {
		return ccid;
	}
	public void setCcid(String ccid) {
		this.ccid = ccid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public static ArrayList<Transcript> load(Connection connection) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		ArrayList<Transcript> listTranscripts = new ArrayList<Transcript>();
		Transcript tr =new Transcript();
		try {
			String query = "select * from Transcript"; 
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				tr.setCcid(resultSet.getString(1));
				tr.setSid(resultSet.getString(2));
				tr.setScore(resultSet.getFloat(3));

				listTranscripts.add(new Transcript(tr));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listTranscripts;
	}
	public static int Insert(Transcript tr, Connection conn) throws ClassNotFoundException, SQLException {

		int rs = 0;
		try  
		{
			String query = "Insert into Transcript values ( ? , ? , ?);";

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, tr.getCcid());
			ps.setString(2, tr.getSid());
			ps.setFloat(3, tr.getScore());

			rs = ps.executeUpdate();

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	public static int Edit(Transcript tr,Connection conn) throws ClassNotFoundException, SQLException {
		int rs = 0;
		try  
		{
			String query = "Update Transcript set SCORE = ? where CCID = ? and SID = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setFloat(1, tr.getScore());
			ps.setString(2, tr.getCcid());
			ps.setString(3, tr.getSid());
			
			rs = ps.executeUpdate();

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	public static int Del(String ccid, String sid,Connection conn) throws ClassNotFoundException, SQLException {

		int rs = 0;
		try  
		{
			PreparedStatement ps = null;
			String query = "delete from Transcript where CCID = ? and SID = ?";			
			ps = conn.prepareStatement(query);

			ps.setString(1,ccid);
			ps.setString(2,sid);

			rs = ps.executeUpdate();

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return rs;
	}

	public static Transcript findTranscript(String ccid, String sid,Connection conn) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Transcript tr =new Transcript();
		try {
			String query = "select * from Transcript where CCID = ? and SID = ?"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,ccid);
			ps.setString(2,sid);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				tr.setCcid(resultSet.getString(1));
				tr.setSid(resultSet.getString(2));
				tr.setScore(resultSet.getFloat(3));
				
				return tr;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ArrayList<Transcript> findTranscriptoOfCCID(String ccid,Connection conn) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<Transcript> listTranscripts = new ArrayList<Transcript>();
		Transcript tr  = new Transcript();
		try {
			String query = "select * from Transcript where CCID = ?"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,ccid);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				tr.setCcid(resultSet.getString(1));
				tr.setSid(resultSet.getString(2));
				tr.setScore(resultSet.getFloat(3));
				
				listTranscripts.add(new Transcript(tr));
			}
			return listTranscripts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ArrayList<Transcript> findTranscriptOfSID(String sid,Connection conn) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<Transcript> listTranscripts = new ArrayList<Transcript>();
		Transcript tr = new Transcript();
		try {
			String query = "select * from Transcript where SID = ?"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,sid);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				tr.setCcid(resultSet.getString(1));
				tr.setSid(resultSet.getString(2));
				tr.setScore(resultSet.getFloat(3));
				
				listTranscripts.add(new Transcript(tr));
			}
			return listTranscripts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ArrayList<Transcript> findTranscriptOfTID(String tid, String ccid, Connection conn) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<Transcript> listTranscripts = new ArrayList<Transcript>();
		Transcript tr = new Transcript();
		try {
			String query = "select t.CCID, t.SID, t.SCORE from Transcript t inner join Course_Class cc on t.CCID = cc.CCID where cc.TID = ?"; 
			if(!ccid.equals("")) {
				query = query + " and cc.CCID = ?";
			}
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,tid);
			if(!ccid.equals("")) {
				ps.setString(2, ccid);
			}
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				tr.setCcid(resultSet.getString(1));
				tr.setSid(resultSet.getString(2));
				tr.setScore(resultSet.getFloat(3));
				
				listTranscripts.add(new Transcript(tr));
			}
			return listTranscripts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static boolean existsCourseStudent(String cid, String sid, Connection conn) throws ClassNotFoundException, SQLException {
		boolean found = false;
		try {
			String query = "select * from Transcript ts inner join Course_Class cc on ts.CCID = cc.CCID where cc.CID = ? and ts.sid = ? and status = 1"; 
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, cid);
			ps.setString(2, sid);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				found = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return found;
	}
}
