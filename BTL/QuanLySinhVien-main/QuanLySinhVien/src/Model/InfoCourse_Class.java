package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InfoCourse_Class extends Course_Class {
	private String name;
	private String descriptionCourse;
	private int numOfCredits;
	private float score;
	public InfoCourse_Class() {
		super();
	}
	public InfoCourse_Class(InfoCourse_Class x) {
		super.setCid(x.getCid());
		super.setCcid(x.getCcid());
		super.setRid(x.getRid());
		super.setTid(x.getTid());
		super.setStatus(x.isStatus());
		super.setSemester(x.getSemester());
		super.setDescription(x.getDescription());
		this.name = x.getName();
		this.descriptionCourse = x.getDescriptionCourse();
		this.numOfCredits = x.getNumOfCredits();
		this.score = x.getScore();

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescriptionCourse() {
		return descriptionCourse;
	}
	public void setDescriptionCourse(String descriptionCourse) {
		this.descriptionCourse = descriptionCourse;
	}
	public int getNumOfCredits() {
		return numOfCredits;
	}
	public void setNumOfCredits(int numOfCredits) {
		this.numOfCredits = numOfCredits;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}

	public static ArrayList<InfoCourse_Class> loadInfo(String search, boolean searchCID, Connection connection) throws ClassNotFoundException, SQLException {
	    ArrayList<InfoCourse_Class> listInfoCourse_Class = new ArrayList<InfoCourse_Class>();
	    InfoCourse_Class ics = new InfoCourse_Class();
	    try {
	        String query = "SELECT c.CID, cc.CCID, c.NAME, cc.RID, cc.TID, cc.STATUS, cc.SEMESTER, cc.DESCRIPTION, c.NUMBEROFCREDITS, c.DESCRIPTION " 
	                     + "FROM Course c INNER JOIN Course_Class cc ON c.CID = cc.CID ";
	        if (!search.isEmpty()) {
	            if (searchCID) {
	                query += "WHERE c.CID LIKE ?";
	            } else {
	                query += "WHERE c.NAME LIKE ?";
	            }
	        }
	        PreparedStatement ps = connection.prepareStatement(query);
	        if (!search.isEmpty()) {
	            ps.setString(1, "%" + search + "%");
	        }
	        ResultSet resultSet = ps.executeQuery();
	        while (resultSet.next()) {
	            ics.setCid(resultSet.getString(1));
	            ics.setCcid(resultSet.getString(2));
	            ics.setName(resultSet.getString(3));
	            ics.setRid(resultSet.getString(4));
	            ics.setTid(resultSet.getString(5));
	            ics.setStatus(resultSet.getBoolean(6));
	            ics.setSemester(resultSet.getInt(7));
	            ics.setDescription(resultSet.getString(8));
	            ics.setNumOfCredits(resultSet.getInt(9));
	            ics.setDescriptionCourse(resultSet.getString(10));
	            listInfoCourse_Class.add(new InfoCourse_Class(ics));
	        }
	        return listInfoCourse_Class;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public static ArrayList<InfoCourse_Class> loadInfoOpen(String search, boolean searchCID, Connection connection) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		ArrayList<InfoCourse_Class> listInfoCourse_Class = new ArrayList<InfoCourse_Class>();
		InfoCourse_Class ics =new InfoCourse_Class();
		try {
			String query = "select c.CID, cc.CCID, c.NAME, cc.RID, cc.TID, cc.STATUS, cc.SEMESTER, cc.DESCRIPTION, c.NUMBEROFCREDITS, c.DESCRIPTION from Course c INNER JOIN Course_Class cc ON c.CID = cc.CID where cc.STATUS = 1";
			if(!search.equals("")) {
				if(searchCID) {
					query = query + " and c.CID LIKE ?";
				}
				else {
					query = query + " and c.NAME LIKE ?";
				}
			}
			PreparedStatement ps = connection.prepareStatement(query);
			if(!search.equals("")) {
				ps.setString(1,"%"+search+"%");
			}
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				ics.setCid(resultSet.getString(1));
				ics.setCcid(resultSet.getString(2));
				ics.setName(resultSet.getString(3));
				ics.setRid(resultSet.getString(4));
				ics.setTid(resultSet.getString(5));
				ics.setStatus(resultSet.getBoolean(6));
				ics.setSemester(resultSet.getInt(7));
				ics.setDescription(resultSet.getString(8));
				ics.setNumOfCredits(resultSet.getInt(9));
				ics.setDescriptionCourse(resultSet.getString(10));

				listInfoCourse_Class.add(new InfoCourse_Class(ics));
			}
			return listInfoCourse_Class;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ArrayList<InfoCourse_Class> loadInfoOfStudent(String sid, boolean openType, Connection connection) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		ArrayList<InfoCourse_Class> listInfoCourse_Class = new ArrayList<InfoCourse_Class>();
		InfoCourse_Class ics =new InfoCourse_Class();
		try {
			String query = "select c.CID, cc.CCID, c.NAME, cc.RID, cc.TID, cc.STATUS, cc.SEMESTER, cc.DESCRIPTION, c.NUMBEROFCREDITS, c.DESCRIPTION from Course c INNER JOIN Course_Class cc ON c.CID = cc.CID INNER JOIN Transcript ts ON cc.CCID = ts.CCID WHERE ts.SID = ?";
			if(openType) {
				query = query + " and cc.STATUS = 1";
			}
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,sid);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				ics.setCid(resultSet.getString(1));
				ics.setCcid(resultSet.getString(2));
				ics.setName(resultSet.getString(3));
				ics.setRid(resultSet.getString(4));
				ics.setTid(resultSet.getString(5));
				ics.setStatus(resultSet.getBoolean(6));
				ics.setSemester(resultSet.getInt(7));
				ics.setDescription(resultSet.getString(8));
				ics.setNumOfCredits(resultSet.getInt(9));
				ics.setDescriptionCourse(resultSet.getString(10));

				listInfoCourse_Class.add(new InfoCourse_Class(ics));
			}
			return listInfoCourse_Class;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ArrayList<InfoCourse_Class> loadInfoOfTeacher(String tid, boolean openType, Connection connection) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		ArrayList<InfoCourse_Class> listInfoCourse_Class = new ArrayList<InfoCourse_Class>();
		InfoCourse_Class ics =new InfoCourse_Class();
		try {
			String query = "select c.CID, cc.CCID, c.NAME, cc.RID, cc.TID, cc.STATUS, cc.SEMESTER, cc.DESCRIPTION, c.NUMBEROFCREDITS, c.DESCRIPTION from Course c INNER JOIN Course_Class cc ON c.CID = cc.CID WHERE cc.TID = ?";
			if(openType) {
				query = query + " and cc.STATUS = 1";
			}
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,tid);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				ics.setCid(resultSet.getString(1));
				ics.setCcid(resultSet.getString(2));
				ics.setName(resultSet.getString(3));
				ics.setRid(resultSet.getString(4));
				ics.setTid(resultSet.getString(5));
				ics.setStatus(resultSet.getBoolean(6));
				ics.setSemester(resultSet.getInt(7));
				ics.setDescription(resultSet.getString(8));
				ics.setNumOfCredits(resultSet.getInt(9));
				ics.setDescriptionCourse(resultSet.getString(10));

				listInfoCourse_Class.add(new InfoCourse_Class(ics));
			}
			return listInfoCourse_Class;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ArrayList<InfoCourse_Class> loadInfoTranscriptOfStudent(String sid, int semester, Connection connection) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		ArrayList<InfoCourse_Class> listInfoCourse_Class = new ArrayList<InfoCourse_Class>();
		InfoCourse_Class ics =new InfoCourse_Class();
		try {
			String query = "select c.CID, cc.CCID, c.NAME, cc.RID, cc.TID, cc.SEMESTER, cc.DESCRIPTION, c.NUMBEROFCREDITS, c.DESCRIPTION, ts.SCORE from Course c INNER JOIN Course_Class cc ON c.CID = cc.CID INNER JOIN Transcript ts ON cc.CCID = ts.CCID where ts.SID = ?";
			if(semester != -1) {
				query = query + " and cc.SEMESTER = ?";
			}
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,sid);
			if(semester != -1) {
				ps.setInt(2, semester);
			}
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				ics.setCid(resultSet.getString(1));
				ics.setCcid(resultSet.getString(2));
				ics.setName(resultSet.getString(3));
				ics.setRid(resultSet.getString(4));
				ics.setTid(resultSet.getString(5));
				ics.setSemester(resultSet.getInt(6));
				ics.setDescription(resultSet.getString(7));
				ics.setNumOfCredits(resultSet.getInt(8));
				ics.setDescriptionCourse(resultSet.getString(9));
				ics.setScore(resultSet.getFloat(10));
				
				listInfoCourse_Class.add(new InfoCourse_Class(ics));
			}
			return listInfoCourse_Class;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
