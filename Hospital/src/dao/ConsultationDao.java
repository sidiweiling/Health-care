package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Consultation;
import util.JDBCtools;

public class ConsultationDao {
	Connection conn=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	
	/**
	 * 查询所有会诊信息
	 * @return
	 */
	public List<Consultation> findall(){
		List<Consultation> list=new ArrayList<Consultation>();
		String sql="select * from consultation";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Consultation consultation=new Consultation();
				consultation.setCon_id(resultSet.getString("con_id"));
				consultation.setApp_id(resultSet.getString("app_id"));
				consultation.setCon_time(resultSet.getDate("con_time"));
				consultation.setText_rec(resultSet.getString("text_rec"));
				consultation.setImage_rec(resultSet.getString("image_rec"));
				list.add(consultation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	/**
	 * 根据会诊id查询会诊信息
	 * @param con_id
	 * @return
	 */
	public Consultation findbycon_id(String con_id) {
		Consultation consultation=new Consultation();
		String sql="select * from consultation where con_id='"+con_id+"'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			consultation.setCon_id(resultSet.getString("con_id"));
			consultation.setApp_id(resultSet.getString("app_id"));
			consultation.setCon_time(resultSet.getDate("con_time"));
			consultation.setText_rec(resultSet.getString("text_rec"));
			consultation.setImage_rec(resultSet.getString("image_rec"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return consultation;
	}

	/**
	 * 查找最近一次会诊信息
	 * @return
	 */
	public Consultation findlastcon_id() {
		Consultation consultation=null;
		String sql="with con_idint(id_int) as(select cast(con_id as int) from consultation )" + 
				"select con_id from consultation,con_idint group by con_id " + 
				"having con_id>=max(id_int)";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			consultation=findbycon_id(resultSet.getString("con_id"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return consultation;
	}
	
	/**
	 * 根据预约id查询会诊信息
	 * @param app_id
	 * @return
	 */
	public Consultation findbyapp_id(String app_id) {
		Consultation consultation=new Consultation();
		String sql="select * from consultation where app_id='"+app_id+"'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			consultation.setCon_id(resultSet.getString("con_id"));
			consultation.setApp_id(resultSet.getString("app_id"));
			consultation.setCon_time(resultSet.getDate("con_time"));
			consultation.setText_rec(resultSet.getString("text_rec"));
			consultation.setImage_rec(resultSet.getString("image_rec"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return consultation;
	}
	
	/**
	 * 增加一条会诊信息（会诊id按顺序自动生成）
	 * @param app_id 预约id
	 * @param text_rec 文本记录
	 * @param image_rec 图片记录（路径）
	 * @return
	 */
	public boolean app(String app_id, String text_rec, String image_rec) {
		String sql="select count(con_id) from consultation";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			resultSet.next();
			int testnum=resultSet.getInt(1);
			if(testnum<1) 
			{
				sql="insert into consultation values('00000001',?,getdate(),?,?)";
				preparedStatement=conn.prepareStatement(sql);
				preparedStatement.setString(1, app_id);
				preparedStatement.setString(2, text_rec);
				preparedStatement.setString(3, image_rec);
				int num1=preparedStatement.executeUpdate();
				if(num1>0) return true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement);
		}
		sql="insert into consultation values(?,?,getdate(),?,?)";
		Consultation lastcon=findlastcon_id();
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql);
			int temple1=Integer.parseInt(lastcon.getCon_id())+1;
			int num=temple1;
			int digit=0;
			while(num>0) {
				digit++;
				num/=10;
			}
			String sample="00000000";
			String temple2=sample.substring(0,8-digit)+String.valueOf(temple1);
			preparedStatement.setString(1, temple2);
			preparedStatement.setString(2, app_id);
			preparedStatement.setString(3, text_rec);
			preparedStatement.setString(4, image_rec);
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
	 * 根据会诊id删除会诊信息
	 * @param con_id
	 * @return
	 */
	public boolean delete(String con_id) {
		String sql="delete from consultation where con_id='"+con_id+"'";				
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
	 * 更新会诊信息
	 * @param consultation
	 * @return
	 */
	public boolean update(Consultation consultation) {
		String sql="update consultation set text_rec='"
				+consultation.getText_rec()+"' , image_rec='"
				+ "' where con_id='" +consultation.getCon_id()+"'";
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
