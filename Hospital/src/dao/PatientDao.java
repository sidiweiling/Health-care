package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Patient;
import util.JDBCtools;

public class PatientDao {
	Connection conn=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	
	public List<Patient> findall() {
		List<Patient> list=new ArrayList<Patient>();
		String sql="select * from patient";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Patient patient=new Patient();
				patient.setPatient_id(resultSet.getString("patient_id"));
				patient.setName(resultSet.getString("name"));
				patient.setSex(resultSet.getInt("sex"));
				patient.setAddress(resultSet.getString("address"));
				patient.setTelephone(resultSet.getString("telephone"));
				list.add(patient);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	public Patient findbypatient_id(String patient_id) {
		Patient patient=new Patient();
		String sql="select * from patient where patient_id='"+ patient_id+ "'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			patient.setPatient_id(resultSet.getString("patient_id"));
			patient.setName(resultSet.getString("name"));
			patient.setSex(resultSet.getInt("sex"));
			patient.setAddress(resultSet.getString("address"));
			patient.setTelephone(resultSet.getString("telephone"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return patient;
	}
	
	public Patient findlastpatient_id() {
		Patient patient=null;
		String sql="with patient_idint(id_int) as(select cast(patient_id as int) from patient )" + 
				"select patient_id from patient,patient_idint group by patient_id " + 
				"having patient_id>=max(id_int)";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			patient=findbypatient_id(resultSet.getString("patient_id"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return patient;
	}
	
	public Patient findbyname(String name) {
		Patient patient=new Patient();
		String sql="select * from patient where name='"+ name+ "'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			patient.setPatient_id(resultSet.getString("patient_id"));
			patient.setName(resultSet.getString("name"));
			patient.setSex(resultSet.getInt("sex"));
			patient.setAddress(resultSet.getString("address"));
			patient.setTelephone(resultSet.getString("telephone"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return patient;
	}
	
	public List<Patient> findbykeyword(String keyword){
		List<Patient> list=new ArrayList<Patient>();
		String sql="select * from patient where name like '%"+ keyword+ "%'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Patient patient=new Patient();
				patient.setPatient_id(resultSet.getString("patient_id"));
				patient.setName(resultSet.getString("name"));
				patient.setSex(resultSet.getInt("sex"));
				patient.setAddress(resultSet.getString("address"));
				patient.setTelephone(resultSet.getString("telephone"));
				list.add(patient);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	public List<Patient> findbysex(int sex){
		List<Patient> list=new ArrayList<Patient>();
		String sql="select * from patient where sex='"+ sex+ "'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Patient patient=new Patient();
				patient.setPatient_id(resultSet.getString("patient_id"));
				patient.setName(resultSet.getString("name"));
				patient.setSex(resultSet.getInt("sex"));
				patient.setAddress(resultSet.getString("address"));
				patient.setTelephone(resultSet.getString("telephone"));
				list.add(patient);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	public boolean add(String name, int sex, String address, String telephone) {
		String sql="select count(patient_id) from patient";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			resultSet.next();
			int testnum=resultSet.getInt(1);
			if(testnum<1) 
			{
				sql="insert into patient values('00000001',?,?,?,?)";
				preparedStatement=conn.prepareStatement(sql);
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, sex);
				preparedStatement.setString(3, address);
				preparedStatement.setString(4, telephone);
				int num2=preparedStatement.executeUpdate();
				if(num2>0) return true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement);
		}
		sql="insert into patient values(?,?,?,?,?)";
		Patient lastpatient=findlastpatient_id();
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql);
			int temple1=Integer.parseInt(lastpatient.getPatient_id())+1;
			int num=temple1;
			int digit=0;
			while(num>0) {
				digit++;
				num/=10;
			}
			String sample="00000000";
			String temple2=sample.substring(0,8-digit)+String.valueOf(temple1);
			preparedStatement.setString(1, temple2);
			preparedStatement.setString(2, name);
			preparedStatement.setInt(3, sex);
			preparedStatement.setString(4, address);
			preparedStatement.setString(5, telephone);
			int num1=preparedStatement.executeUpdate();
			if(num1>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement);
		}
		return false;
	}
	
	public boolean delete(String patient_id) {
		String sql="delete from patient where patient_id='"+patient_id+"'";				
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

	public boolean update(Patient patient) {
		String sql="update patient set name='"
				+patient.getName()+ "', sex='"
				+patient.getSex()+ "',address='"
				+patient.getAddress()+"', telephone='"
				+patient.getTelephone()+"' where patient_id='"
				+patient.getPatient_id()+"'";
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
