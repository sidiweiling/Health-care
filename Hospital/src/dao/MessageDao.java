package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Doctor;
import entity.Message;
import entity.Patient;
import util.JDBCtools;

public class MessageDao {
	Connection conn=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	
	/**
	 * 对于单个用户来说十分无用的功能，但以防万一先写出来
	 * @return
	 */
	public List<Message> findall(){
		List<Message> list=new ArrayList<Message>();
		String sql="select * from message";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Message message=new Message();
				message.setMsgTime(resultSet.getDate("msgTime"));
				message.setMsgFrom(resultSet.getString("msgFrom"));
				message.setMsgTo(resultSet.getString("msgTo"));
				message.setMsgContent(resultSet.getString("msgContent"));
				list.add(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	public List<Message> findbymsgFrom(String msgFrom){
		List<Message> list=new ArrayList<Message>();
		String sql="select * from message where msgFrom='"+msgFrom+"'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Message message=new Message();
				message.setMsgTime(resultSet.getDate("msgTime"));
				message.setMsgFrom(resultSet.getString("msgFrom"));
				message.setMsgTo(resultSet.getString("msgTo"));
				message.setMsgContent(resultSet.getString("msgContent"));
				list.add(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	public List<Message> findbymsgTo(String msgTo){
		List<Message> list=new ArrayList<Message>();
		String sql="select * from message where msgTo='"+msgTo+"'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Message message=new Message();
				message.setMsgTime(resultSet.getDate("msgTime"));
				message.setMsgFrom(resultSet.getString("msgFrom"));
				message.setMsgTo(resultSet.getString("msgTo"));
				message.setMsgContent(resultSet.getString("msgContent"));
				list.add(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	/**
	 * 用于查询有哪些医生给这个患者发送过消息
	 * @param msgTo 查询的需要方（患者）
	 * @return
	 */
	public List<Doctor> findmsgofdoctor(String msgTo){
		List<Doctor> list=new ArrayList<Doctor>();
		List<String> list2=new ArrayList<String>();
		List<String> list3=new ArrayList<String>();
		String sql1="select distinct msgFrom from message where msgTo='"+msgTo+"'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql1,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
				list2.add(resultSet.getString("msgFrom"));
			int len1=list2.size();
			for(int i=0;i<len1;i++) {
				String sql2="select doctor_id from \"user\" where username='"+list2.get(i)+"'";
				preparedStatement=conn.prepareStatement(sql2,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				resultSet=preparedStatement.executeQuery();
				if(resultSet.last()) {
					resultSet.beforeFirst();
					resultSet.next();
					list3.add(resultSet.getString("doctor_id"));	
				}
			}
			int len2=list3.size();
			DoctorDao doctorDao=new DoctorDao();
			for(int i=0;i<len2;i++) {
				String sql3="select * from doctor where doctor_id='"+list3.get(i)+"'";
				preparedStatement=conn.prepareStatement(sql3,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				resultSet=preparedStatement.executeQuery();
				if(resultSet.last()) {
					resultSet.beforeFirst();
					resultSet.next();
					list.add(doctorDao.findbydoctor_id(resultSet.getString("doctor_id")));	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	/**
	 *  用于查询有哪些患者给这个医生发送过消息
	 * @param msgTo 查询的需要方（医生）
	 * @return
	 */
	public List<Patient> findmsgofpatient(String msgTo){
		List<Patient> list=new ArrayList<Patient>();
		List<String> list2=new ArrayList<String>();
		List<String> list3=new ArrayList<String>();
		String sql1="select distinct msgFrom from message where msgTo='"+msgTo+"'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql1,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
				list2.add(resultSet.getString("msgFrom"));
			int len1=list2.size();
			for(int i=0;i<len1;i++) {
				String sql2="select patient_id from \"user\" where username='"+list2.get(i)+"'";
				preparedStatement=conn.prepareStatement(sql2,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				resultSet=preparedStatement.executeQuery();
				if(resultSet.last()) {
					resultSet.beforeFirst();
					resultSet.next();
					list3.add(resultSet.getString("patient_id"));	
				}
			}
			int len2=list3.size();
			PatientDao patientDao=new PatientDao();
			for(int i=0;i<len2;i++) {
				String sql3="select * from patient where patient_id='"+list3.get(i)+"'";
				preparedStatement=conn.prepareStatement(sql3,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				resultSet=preparedStatement.executeQuery();
				if(resultSet.last()) {
					resultSet.beforeFirst();
					resultSet.next();
					list.add(patientDao.findbypatient_id(resultSet.getString("patient_id")));	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	/**
	 * 使用时新建一个message对象，把它作为参数传入该方法使用
	 * @param message
	 * @return
	 */
	public boolean add(Message message) {
		String sql="insert into message values(getdate(),?,?,?)";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, message.getMsgFrom());
			preparedStatement.setString(2, message.getMsgTo());
			preparedStatement.setString(3, message.getMsgContent());
			int num=preparedStatement.executeUpdate();
			if(num>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement);
		}
		return false;
	}
	
	/**
	 * 提供发送方和接收方的id即可删除具体一组信息
	 * @param msgFrom 信息发送方
	 * @param msgTo 信息接收方
	 * @return
	 */
	public boolean delete(String msgFrom, String msgTo) {
		String sql="delete from message where msgFrom='"+msgFrom+"' and msgTo='"+msgTo+"'";				
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
	 * 使用时新建一个message对象，把它作为参数传入该方法使用
	 * @param message
	 * @return
	 */
	public boolean update_msgContent(Message message) {
		String sql="update message set msgContent='"
				+message.getMsgContent()+"' where msgFrom='" +message.getMsgFrom()+"'";
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
	 * 使用时新建一个message对象，把它作为参数传入该方法使用
	 * @param message
	 * @return
	 */
	public boolean update_msgTo(Message message) {
		String sql="update message set msgTo='"
				+message.getMsgTo()+"' where msgFrom='" +message.getMsgFrom()+"'";
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
