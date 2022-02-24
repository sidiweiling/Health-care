package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Doctor;
import util.JDBCtools;

public class DoctorDao {
	Connection conn=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	
	/**
	 * 查找所有医生信息
	 * @return
	 */
	public List<Doctor> findall(){
		List<Doctor> list=new ArrayList<Doctor>();
		String sql="select * from doctor";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Doctor doctor=new Doctor();
				doctor.setDoctor_id(resultSet.getString("doctor_id"));
				doctor.setName(resultSet.getString("name"));
				doctor.setDepartment(resultSet.getString("department"));
				doctor.setTelephone(resultSet.getString("telephone"));
				list.add(doctor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	/**
	 * 根据医生id查找医生信息
	 * @param doctor_id
	 * @return
	 */
	public Doctor findbydoctor_id(String doctor_id) {
		Doctor doctor=new Doctor();
		String sql="select * from doctor where doctor_id='"+ doctor_id+ "'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			doctor.setDoctor_id(resultSet.getString("doctor_id"));
			doctor.setName(resultSet.getString("name"));
			doctor.setDepartment(resultSet.getString("department"));
			doctor.setTelephone(resultSet.getString("telephone"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return doctor;
	}
	
	/**
	 * 查找最新注册的医生信息
	 * @return
	 */
	public Doctor findlastdoctor_id() {
		Doctor doctor=null;
		String sql="with doctor_idint(id_int) as(select cast(doctor_id as int) from doctor )" + 
				"select doctor_id from doctor,doctor_idint group by doctor_id " + 
				"having doctor_id>=max(id_int)";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			doctor=findbydoctor_id(resultSet.getString("doctor_id"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return doctor;
	}
	
	/**
	 * 根据姓名查找医生信息
	 * @param name
	 * @return
	 */
	public Doctor findbyname(String name) {
		Doctor doctor=new Doctor();
		String sql="select * from doctor where name='"+ name+ "'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			doctor.setDoctor_id(resultSet.getString("doctor_id"));
			doctor.setName(resultSet.getString("name"));
			doctor.setDepartment(resultSet.getString("department"));
			doctor.setTelephone(resultSet.getString("telephone"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return doctor;
	}
	
	public List<Doctor> findbykeyword(String keyword){
		List<Doctor> list=new ArrayList<Doctor>();
		String sql="select * from doctor where name like '%"+ keyword+ "%'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Doctor doctor=new Doctor();
				doctor.setDoctor_id(resultSet.getString("doctor_id"));
				doctor.setName(resultSet.getString("name"));
				doctor.setDepartment(resultSet.getString("department"));
				doctor.setTelephone(resultSet.getString("telephone"));
				list.add(doctor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	/**
	 * 根据科室查找医生信息
	 * @param department
	 * @return 多组医生信息的集合
	 */
	public List<Doctor> findbydepartment(String department) {
		List<Doctor> list=new ArrayList<Doctor>();
		String sql="select * from doctor where department='"+ department+ "'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Doctor doctor=new Doctor();
				doctor.setDoctor_id(resultSet.getString("doctor_id"));
				doctor.setName(resultSet.getString("name"));
				doctor.setDepartment(resultSet.getString("department"));
				doctor.setTelephone(resultSet.getString("telephone"));
				list.add(doctor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	/**
	 * 新注册一组医生信息（医生id按顺序自动生成）
	 * @param name
	 * @param department
	 * @param telephone
	 * @return
	 */
	public boolean add(String name, String department, String telephone) {
		String sql="select count(doctor_id) from doctor";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			resultSet.next();
			int testnum=resultSet.getInt(1);
			if(testnum<1) 
			{
				sql="insert into doctor values('00000001',?,?,?)";
				preparedStatement=conn.prepareStatement(sql);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, department);
				preparedStatement.setString(3, telephone);
				int num1=preparedStatement.executeUpdate();
				if(num1>0) return true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement);
		}
		sql="insert into doctor values(?,?,?,?)";
		Doctor lastdoctor=findlastdoctor_id();
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql);
			int temple1=Integer.parseInt(lastdoctor.getDoctor_id())+1;
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
			preparedStatement.setString(3, department);
			preparedStatement.setString(4, telephone);
			int num2=preparedStatement.executeUpdate();
			if(num2>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement);
		}
		return false;
	}
	
	/**
	 * 注销一名医生的账户
	 * @param doctor_id
	 * @return
	 */
	public boolean delete(String doctor_id) {
		String sql="delete from doctor where doctor_id='"+doctor_id+"'";				
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
	
	
	/**
	 * 更改医生信息
	 * @param doctor
	 * @return
	 */
	public boolean update(Doctor doctor) {
		String sql="update doctor set name='"
				+doctor.getName()+ "', department='"
				+doctor.getDepartment()+"', telephone='"
				+doctor.getTelephone()+"' where doctor_id='"
				+doctor.getDoctor_id()+"'";
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
