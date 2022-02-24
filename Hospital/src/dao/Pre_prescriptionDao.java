package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Pre_prescription;
import util.JDBCtools;

public class Pre_prescriptionDao {
	Connection conn=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	
	public List<Pre_prescription> findall(){
		List<Pre_prescription> list=new ArrayList<Pre_prescription>();
		String sql="select * from pre_prescription";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Pre_prescription pre_prescription=new Pre_prescription();
				pre_prescription.setCon_id(resultSet.getString("con_id"));
				pre_prescription.setPres_id(resultSet.getString("pres_id"));
				pre_prescription.setDate(resultSet.getDate("date"));
				list.add(pre_prescription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	public Pre_prescription findbycon_id(String con_id) {
		Pre_prescription pre_prescription=new Pre_prescription();
		String sql="select * from pre_prescription where con_id='"+ con_id+ "'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			pre_prescription.setCon_id(resultSet.getString("con_id"));
			pre_prescription.setPres_id(resultSet.getString("pres_id"));
			pre_prescription.setDate(resultSet.getDate("date"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return pre_prescription;
	}
	
	public Pre_prescription findbypres_id(String pres_id) {
		Pre_prescription pre_prescription=new Pre_prescription();
		String sql="select * from pre_prescription where pres_id='"+ pres_id+ "'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			pre_prescription.setCon_id(resultSet.getString("con_id"));
			pre_prescription.setPres_id(resultSet.getString("pres_id"));
			pre_prescription.setDate(resultSet.getDate("date"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return pre_prescription;
	}
	
	public boolean add(Pre_prescription pre_prescription) {
		String sql="insert into pre_prescription values(?,?,getdate())";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, pre_prescription.getCon_id());
			preparedStatement.setString(2, pre_prescription.getPres_id());
			int num=preparedStatement.executeUpdate();
			if(num>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement);
		}
		return false;
	}
	
	public boolean delete(String pres_id) {
		String sql="delete from pre_prescription where pres_id='"+pres_id+"'";				
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
	
	public boolean update(Pre_prescription pre_prescription) {
		String sql="update pre_prescription set date='"
				+pre_prescription.getDate()+"' where pres_id='"
				+pre_prescription.getPres_id()+"'";
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
