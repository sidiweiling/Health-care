package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Medicine;
import util.JDBCtools;

public class MedicineDao {
	Connection conn=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	
	public List<Medicine> findall(){
		List<Medicine> list=new ArrayList<Medicine>();
		String sql="select * from medicine";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.last()==false)
				return null;
			resultSet.beforeFirst();
			while(resultSet.next())
			{
				Medicine medicine=new Medicine();
				medicine.setPres_id(resultSet.getString("pres_id"));
				medicine.setMedicine(resultSet.getString("medicine"));
				list.add(medicine);
			}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCtools.release(conn, preparedStatement, resultSet);
			}
		return list;
	}
	
//	public Medicine findlastMedicine() {
//		Medicine medicine=new Medicine();
//		String sql="with pres_idint(id_int) as(select cast(pres_id as int) from medicine )" + 
//				"select pres_id from medicine,pres_idint group by pres_id " + 
//				"having pres_id>=max(id_int)";
//		try {
//			conn=JDBCtools.getconn();
//			preparedStatement=conn.prepareStatement(sql);
//			resultSet=preparedStatement.executeQuery();
//			medicine.setPres_id(resultSet.getString("pres_id"));
//			medicine.setMedicine(resultSet.getString("medicine"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			JDBCtools.release(conn, preparedStatement, resultSet);
//		}
//		return medicine;
//	}
	
	public boolean add(Medicine medicine) {
		String sql="insert into medicine values(?,?)";
		try {
			conn=JDBCtools.getconn();
			preparedStatement=conn.prepareStatement(sql);
			preparedStatement.setString(1, medicine.getPres_id());
			preparedStatement.setString(2, medicine.getMedicine());
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
		String sql="delete from medicine where pres_id='"+pres_id+"'";				
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
	
	public boolean update(Medicine medicine) {
		String sql="update medicine set medicine='"
				+medicine.getMedicine()+"' where pres_id='" +medicine.getPres_id()+"'";
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
