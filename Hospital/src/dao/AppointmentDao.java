package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Appointment;
import util.JDBCtools;

public class AppointmentDao {
	Connection conn=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	
	/**
	 * 查询所有预约信息
	 * @return 预约信息集合list
	 */
	public List<Appointment> findall(){
		List<Appointment> list=new ArrayList<Appointment>();
		String sql="select * from appointment";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Appointment appointment=new Appointment();
				appointment.setApp_id(resultSet.getString("app_id"));
				appointment.setApp_department(resultSet.getString("app_department"));
				appointment.setApp_time(resultSet.getDate("app_time"));
				appointment.setDoctor_id(resultSet.getString("doctor_id"));
				appointment.setPatient_id(resultSet.getString("patient_id"));
				appointment.setLive(resultSet.getInt("live"));
				list.add(appointment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	/**
	 * 根据预约id查询预约信息
	 * @param app_id 预约id
	 * @return 一组预约信息
	 */
	public Appointment findbyapp_id(String app_id) {
		Appointment appointment=new Appointment();
		String sql="select * from appointment where app_id='"+app_id+"'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			appointment.setApp_id(resultSet.getString("app_id"));
			appointment.setApp_department(resultSet.getString("app_department"));
			appointment.setApp_time(resultSet.getDate("app_time"));
			appointment.setDoctor_id(resultSet.getString("doctor_id"));
			appointment.setPatient_id(resultSet.getString("patient_id"));
			appointment.setLive(resultSet.getInt("live"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return appointment;
	}
	
	/**
	 * 查询最近预约的对象
	 * @return 最近预约的一组预约信息
	 */
	public Appointment findlastapp_id() {
		Appointment appointment=null;
		String sql="with app_idint(id_int) as(select cast(app_id as int) from appointment )" + 
				"select app_id from appointment,app_idint group by app_id " + 
				"having app_id>=max(id_int)";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			resultSet.next();
			appointment=findbyapp_id(resultSet.getString("app_id"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return appointment;
	}
	
	/**
	 * 根据科室查询预约信息
	 * @param app_department 预约科室
	 * @return 多组预约信息集合
	 */
	public List<Appointment> findbyapp_department(String app_department) {
		List<Appointment> list=new ArrayList<Appointment>();
		String sql="select * from appointment where app_department='"+app_department+"'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Appointment appointment=new Appointment();
				appointment.setApp_id(resultSet.getString("app_id"));
				appointment.setApp_department(resultSet.getString("app_department"));
				appointment.setApp_time(resultSet.getDate("app_time"));
				appointment.setDoctor_id(resultSet.getString("doctor_id"));
				appointment.setPatient_id(resultSet.getString("patient_id"));
				appointment.setLive(resultSet.getInt("live"));
				list.add(appointment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	/**
	 * 根据医生id查询预约信息
	 * @param doctor_id
	 * @return
	 */
	public List<Appointment> findbydoctor_id(String doctor_id) {
		List<Appointment> list=new ArrayList<Appointment>();
		String sql="select * from appointment where doctor_id='"+doctor_id+"'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Appointment appointment=new Appointment();
				appointment.setApp_id(resultSet.getString("app_id"));
				appointment.setApp_department(resultSet.getString("app_department"));
				appointment.setApp_time(resultSet.getDate("app_time"));
				appointment.setDoctor_id(resultSet.getString("doctor_id"));
				appointment.setPatient_id(resultSet.getString("patient_id"));
				appointment.setLive(resultSet.getInt("live"));
				list.add(appointment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}

	/**
	 * 根据病人id查询预约信息
	 * @param patient_id
	 * @return
	 */
	public List<Appointment> findbypatient_id(String patient_id) {
		List<Appointment> list=new ArrayList<Appointment>();
		String sql="select * from appointment where patient_id='"+patient_id+"'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Appointment appointment=new Appointment();
				appointment.setApp_id(resultSet.getString("app_id"));
				appointment.setApp_department(resultSet.getString("app_department"));
				appointment.setApp_time(resultSet.getDate("app_time"));
				appointment.setDoctor_id(resultSet.getString("doctor_id"));
				appointment.setPatient_id(resultSet.getString("patient_id"));
				appointment.setLive(resultSet.getInt("live"));
				list.add(appointment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	public List<Appointment> findbylive_doctor(String doctor_id){
		List<Appointment> list=new ArrayList<Appointment>();
		String sql="select * from appointment where patient_id='"+doctor_id+"' and live=1";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Appointment appointment=new Appointment();
				appointment.setApp_id(resultSet.getString("app_id"));
				appointment.setApp_department(resultSet.getString("app_department"));
				appointment.setApp_time(resultSet.getDate("app_time"));
				appointment.setDoctor_id(resultSet.getString("doctor_id"));
				appointment.setPatient_id(resultSet.getString("patient_id"));
				appointment.setLive(resultSet.getInt("live"));
				list.add(appointment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	public List<Appointment> findbylive_patient(String patient_id){
		List<Appointment> list=new ArrayList<Appointment>();
		String sql="select * from appointment where patient_id='"+patient_id+"' and live=1";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Appointment appointment=new Appointment();
				appointment.setApp_id(resultSet.getString("app_id"));
				appointment.setApp_department(resultSet.getString("app_department"));
				appointment.setApp_time(resultSet.getDate("app_time"));
				appointment.setDoctor_id(resultSet.getString("doctor_id"));
				appointment.setPatient_id(resultSet.getString("patient_id"));
				appointment.setLive(resultSet.getInt("live"));
				list.add(appointment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	/**
	 * 新建预约信息（预约id按照顺序生成）
	 * @param app_department
	 * @param doctor_id
	 * @param patient_id
	 * @return
	 */
	public boolean add(String app_department, String doctor_id, String patient_id) {
		String sql="select count(app_id) from appointment";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			resultSet.next();
			int testnum=resultSet.getInt(1);
			if(testnum<1) 
			{
				sql="insert into appointment values('00000001',getdate(),?,?,?,?)";
				preparedStatement=conn.prepareStatement(sql);
				preparedStatement.setString(1, app_department);
				preparedStatement.setString(2, doctor_id);
				preparedStatement.setString(3, patient_id);
				preparedStatement.setInt(4, 1);
				int num1=preparedStatement.executeUpdate();
				if(num1>0) return true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement);
		}
		sql="insert into appointment values(?,getdate(),?,?,?,?)";
		Appointment lastapp=findlastapp_id();
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql);
			int temple1=Integer.parseInt(lastapp.getApp_id())+1;
			int num=temple1;
			int digit=0;
			while(num>0) {
				digit++;
				num/=10;
			}
			String sample="00000000";
			String temple2=sample.substring(0,8-digit)+String.valueOf(temple1);
			preparedStatement.setString(1, temple2);
			preparedStatement.setString(2, app_department);
			preparedStatement.setString(3, doctor_id);
			preparedStatement.setString(4, patient_id);
			preparedStatement.setInt(5, 1);
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
	 * 根据预约id删除（撤销）预约信息
	 * @param app_id
	 * @return
	 */
	public boolean delete(String app_id) {
		String sql="delete from appointment where app_id='"+app_id+"'";				
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
	 * 更新预约信息（仅包括科室）
	 * @param appointment
	 * @return
	 */
	public boolean update_department(Appointment appointment) {
		String sql="update appointment set app_department='"
					+appointment.getApp_department()+"' where app_id='"
					+appointment.getApp_id()+"'";
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
	
	public boolean update_live(Appointment appointment) {
		String sql="update appointment set live='"
					+appointment.getLive()+"' where app_id='"
					+appointment.getApp_id()+"'";
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