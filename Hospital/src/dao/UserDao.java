package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import util.JDBCtools;

public class UserDao {
	Connection conn=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	
	public User findbyusername(String username) {
		User user=new User();
		String sql="select * from \"user\" where username='"+ username+ "'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			user.setUsername(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setDoctor_id(resultSet.getString("doctor_id"));
			user.setPatient_id(resultSet.getString("patient_id"));
			user.setPlayer(resultSet.getInt("player"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return user;
	}
	
	public List<User> findall(){
		List<User> list=new ArrayList<User>();
		String sql="select * from \"user\"";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				User user=null;
				user=findbyusername(resultSet.getString("username"));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	public User findbydoctor_id(String doctor_id) {
		User user=null;
		String sql="select * from \"user\" where doctor_id='"+ doctor_id+ "'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			user=findbyusername(resultSet.getString("username"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return user;
	}
	
	public User findbypatient_id(String patient_id) {
		User user=null;
		String sql="select * from \"user\" where patient_id='"+ patient_id+ "'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			user=findbyusername(resultSet.getString("username"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return user;
	}
	
	public boolean add(User user) {
		String sql="insert into \"user\" values(?,?,?,?,?)";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getDoctor_id());
			preparedStatement.setString(4, user.getPatient_id());
			preparedStatement.setInt(5, user.getPlayer());
			int num=preparedStatement.executeUpdate();
			if(num>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement);
		}
		return false;
	}
	
	public boolean delete(String username) {
		String sql="delete from \"user\" where username='"+username+"'";				
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
	
	public boolean updatepassword(User user, String password) {
		String sql="update \"user\" set password='"
				+password+"' where username='" +user.getUsername()+"'";
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
