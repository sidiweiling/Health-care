package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Medical;
import util.JDBCtools;

public class MedicalDao {
	Connection conn=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	
	/**
	 * 根据病人id查找该病人的所有病历
	 * @param patiend_id
	 * @return
	 */
	public List<Medical> findallbypatient_id(String patiend_id){
		List<Medical> list=new ArrayList<Medical>();
		String sql="select * from medical where patiend_id='"+patiend_id+"'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Medical medical=new Medical();
				medical.setCon_id(resultSet.getString("con_id"));
				medical.setSec_id(resultSet.getString("sec_id"));
				medical.setPatient_id(resultSet.getString("patiend_id"));
				medical.setDiagnosis(resultSet.getString("diagnosis"));
				list.add(medical);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return list;
	}
	
	/**
	 * 根据病人id查找该病人某一页的病历信息
	 * @param sec_id
	 * @return
	 */
	public Medical findbysec_id(String sec_id) {
		Medical medical=new Medical();
		String sql="select * from medical where sec_id='"+ sec_id+ "'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			medical.setCon_id(resultSet.getString("con_id"));
			medical.setSec_id(resultSet.getString("sec_id"));
			medical.setPatient_id(resultSet.getString("patiend_id"));
			medical.setDiagnosis(resultSet.getString("diagnosis"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return medical;
	}
	
	/**
	 * 查找病人最近的病历信息
	 * @return
	 */
	public Medical findlastsec_id(String patient_id) {
		Medical medical=null;
		String sql="with sec_idint(id_int) as(select cast(sec_id as int) from medical where patient_id='"+patient_id+"' )" + 
				"select sec_id from medical,sec_idint group by sec_id " + 
				"having sec_id>=max(id_int)";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			medical=findbycon_id(resultSet.getString("con_id"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return medical;
	}
	
	/**
	 * 根据会诊id查找病历记录
	 * @param con_id
	 * @return
	 */
	public Medical findbycon_id(String con_id) {
		Medical medical=new Medical();
		String sql="select * from medical where con_id='"+ con_id+ "'";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			resultSet.next();
			medical.setCon_id(resultSet.getString("con_id"));
			medical.setSec_id(resultSet.getString("sec_id"));
			medical.setPatient_id(resultSet.getString("patiend_id"));
			medical.setDiagnosis(resultSet.getString("diagnosis"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement, resultSet);
		}
		return medical;
	}
	
	/**
	 * 增加一条病历信息（项id按顺序自动生成）
	 * @param con_id
	 * @param patient_id
	 * @param diagnosis
	 * @return
	 */
	public boolean add(String con_id, String patient_id, String diagnosis) {
		String sql="select count(sec_id) from medical";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			resultSet.next();
			int testnum=resultSet.getInt(1);
			if(testnum<1) 
			{
				sql="insert into doctor values(?,'00000001',?,?)";
				preparedStatement=conn.prepareStatement(sql);
				preparedStatement.setString(1, con_id);
				preparedStatement.setString(2, patient_id);
				preparedStatement.setString(3, diagnosis);
				int num2=preparedStatement.executeUpdate();
				if(num2>0) return true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			JDBCtools.release(conn, preparedStatement);
		}
		sql="insert into medical values(?,?,?,?,?)";
		Medical lastmedical=findlastsec_id(patient_id);
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql);
			int temple1=Integer.parseInt(lastmedical.getSec_id())+1;
			int num=temple1;
			int digit=0;
			while(num>0) {
				digit++;
				num/=10;
			}
			String sample="00000000";
			String temple2=sample.substring(0,8-digit)+String.valueOf(temple1);
			preparedStatement.setString(1, con_id);
			preparedStatement.setString(2, temple2);
			preparedStatement.setString(3, patient_id);
			preparedStatement.setString(4, diagnosis);
			int num1=preparedStatement.executeUpdate();
			if(num1>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCtools.release(conn, preparedStatement);
		}
		return false;
	}
	
	/**
	 * 根据会诊id删除一项病历信息
	 * @param con_id
	 * @return
	 */
	public boolean deletebycon_id(String con_id) {
		String sql="delete from medical where con_id='"+con_id+"'";				
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
	 * 根据项id删除一项病历信息
	 * @param sec_id
	 * @return
	 */
	public boolean deletebysec_id(String sec_id) {
		String sql="delete from medical where sec_id='"+sec_id+"'";				
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
	 * 根据会诊id更新病人的病历信息
	 * @param medical
	 * @return
	 */
	public boolean update(Medical medical) {
		String sql="update medical set sec_id='"
				+medical.getSec_id()+ "', diagnosis='"
				+medical.getDiagnosis()+"' where con_id='"
				+medical.getCon_id()+"'";
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
