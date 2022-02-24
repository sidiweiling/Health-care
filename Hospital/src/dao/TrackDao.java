package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Track;
import util.JDBCtools;

public class TrackDao {
	Connection conn=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	
	public List<Track> findall() {
		List<Track> list=new ArrayList<Track>();
		String sql="select * from track";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Track track=new Track();
				track.setTrack_id(resultSet.getString("track_id"));
				track.setCon_id(resultSet.getString("con_id"));
				track.setDoctor_id(resultSet.getString("doctor_id"));
				track.setPatient_id(resultSet.getString("patient_id"));
				track.setCase_description(resultSet.getString("case_description"));
				list.add(track);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	public Track findbytrack_id(String track_id) {
		Track track=new Track();
		String sql="select * from track where track_id='"+ track_id+ "'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			track.setTrack_id(resultSet.getString("track_id"));
			track.setCon_id(resultSet.getString("con_id"));
			track.setDoctor_id(resultSet.getString("doctor_id"));
			track.setPatient_id(resultSet.getString("patient_id"));
			track.setCase_description(resultSet.getString("case_description"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return track;
	}
	
	public Track findlasttrack() {
		Track track=null;
		String sql="with track_idint(id_int) as(select cast(track_id as int) from track )" + 
				"select track_id from track,track_idint group by track_id " + 
				"having track_id>=max(id_int)";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			track=findbytrack_id(resultSet.getString("track_id"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return track;
	}
	
	public Track findbycon_id(String con_id) {
		Track track=new Track();
		String sql="select * from track where con_id='"+ con_id+ "'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			track.setTrack_id(resultSet.getString("track_id"));
			track.setCon_id(resultSet.getString("con_id"));
			track.setDoctor_id(resultSet.getString("doctor_id"));
			track.setPatient_id(resultSet.getString("patient_id"));
			track.setCase_description(resultSet.getString("case_description"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return track;
	}
	
	public Track findbydoctor_id(String doctor_id) {
		Track track=new Track();
		String sql="select * from track where doctor_id='"+ doctor_id+ "'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			track.setTrack_id(resultSet.getString("track_id"));
			track.setCon_id(resultSet.getString("con_id"));
			track.setDoctor_id(resultSet.getString("doctor_id"));
			track.setPatient_id(resultSet.getString("patient_id"));
			track.setCase_description(resultSet.getString("case_description"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return track;
	}
	
	public Track findbypatient_id(String patient_id) {
		Track track=new Track();
		String sql="select * from track where patient_id='"+ patient_id+ "'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			track.setTrack_id(resultSet.getString("track_id"));
			track.setCon_id(resultSet.getString("con_id"));
			track.setDoctor_id(resultSet.getString("doctor_id"));
			track.setPatient_id(resultSet.getString("patient_id"));
			track.setCase_description(resultSet.getString("case_description"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return track;
	}
	
	public boolean add(Track track) {
		String sql="select count(track_id) from track";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			resultSet.next();
			int testnum=resultSet.getInt(1);
			if(testnum<1) 
			{
				sql="insert into track values('00000001',?,?,?,?)";
				preparedStatement=conn.prepareStatement(sql);
				preparedStatement.setString(1, track.getCon_id());
				preparedStatement.setString(2, track.getDoctor_id());
				preparedStatement.setString(3, track.getPatient_id());
				preparedStatement.setString(4, track.getCase_description());
				int num1=preparedStatement.executeUpdate();
				if(num1>0) return true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement);
		}
		sql="insert into track values(?,?,?,?,?)";
		Track track2=findlasttrack();
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql);
			int temple1=Integer.parseInt(track2.getTrack_id())+1;
			int num=temple1;
			int digit=0;
			while(num>0) {
				digit++;
				num/=10;
			}
			String sample="00000000";
			String temple2=sample.substring(0,8-digit)+String.valueOf(temple1);
			preparedStatement.setString(1, temple2);
			preparedStatement.setString(2, track.getCon_id());
			preparedStatement.setString(3, track.getDoctor_id());
			preparedStatement.setString(4, track.getPatient_id());
			preparedStatement.setString(5, track.getCase_description());
			int num2=preparedStatement.executeUpdate();
			if(num2>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement);
		}
		return false;
	}
	
	public boolean delete(String track_id) {
		String sql="delete from track where track_id='"+track_id+"'";				
		try {
			conn=JDBCtools.getconn();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			int num=preparedStatement.executeUpdate();
			if(num>0) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement);
		}
		return false;
	}
	
	public boolean update(Track track) {
		String sql="update track set case_description='"
				+track.getCase_description()+"' where track_id='"
				+track.getTrack_id()+"'";
		try {
			conn=JDBCtools.getconn();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			int num=preparedStatement.executeUpdate();
			if(num>0) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement);
		}
		return false;
	}
}
