package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Prescription;
import util.JDBCtools;

public class PrescriptionDao {
	Connection conn=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	
	public List<Prescription> findall(){
		List<Prescription> list=new ArrayList<Prescription>();
		String sql="select * from prescription";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Prescription prescription=new Prescription();
				prescription.setPres_id(resultSet.getString("pres_id"));
				prescription.setMedical_advice(resultSet.getString("medical_advice"));
				list.add(prescription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	public Prescription findbypres_id(String pres_id) {
		Prescription prescription=new Prescription();
		String sql="select * from prescription where pres_id='"+ pres_id+ "'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			prescription.setPres_id(resultSet.getString("pres_id"));
			prescription.setMedical_advice(resultSet.getString("medical_advice"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return prescription;
	}
	
	public Prescription findlastPrescription() {
		Prescription prescription=null;
		String sql="with pres_idint(id_int) as(select cast(pres_id as int) from prescription )" + 
				"select pres_id from prescription,pres_idint group by pres_id " + 
				"having pres_id>=max(id_int)";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			prescription=findbypres_id(resultSet.getString("pres_id"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return prescription;
	}
	
	public boolean add(String medical_advice) {
		String sql="select count(pres_id) from prescription";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			resultSet.next();
			int testnum=resultSet.getInt(1);
			if(testnum<1)  
			{
				sql="insert into prescription values('00000001',?)";
				preparedStatement=conn.prepareStatement(sql);
				preparedStatement.setString(1, medical_advice);
				int num1=preparedStatement.executeUpdate();
				if(num1>0) return true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement);
		}
		sql="insert into prescription values(?,?)";
		Prescription prescription=findlastPrescription();
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql);
			int temple1=Integer.parseInt(prescription.getPres_id())+1;
			int num=temple1;
			int digit=0;
			while(num>0) {
				digit++;
				num/=10;
			}
			String sample="00000000";
			String temple2=sample.substring(0,8-digit)+String.valueOf(temple1);
			preparedStatement.setString(1, temple2);
			preparedStatement.setString(2, medical_advice);
			int num2=preparedStatement.executeUpdate();
			if(num2>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement);
		}
		return false;
	}
	
	public boolean delete(String pres_id) {
		String sql="delete from prescription where pres_id='"+pres_id+"'";				
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
	
	public boolean update(Prescription prescription) {
		String sql="update prescription set medical_advice='"
				+prescription.getMedical_advice()+"' where pres_id='"
				+prescription.getPres_id()+"'";
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
