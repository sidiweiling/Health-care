package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCtools {
	static public Connection getconn() {
		 String driverPath="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		 String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=health";
		 String user="sa";
		 String password="a19751022";
		 Connection conn=null;
		 try {
			 Class.forName(driverPath);
			 conn=DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
			return null;
		}
		 return conn;
	}
	
	static public void release(Connection conn) {
		try {
			if(conn!=null) 
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static public void release(Connection conn,PreparedStatement preparedStatement) {
		try {
			if(preparedStatement!=null)
				preparedStatement.close();
			if(conn!=null) 
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static public void release(Connection conn,PreparedStatement preparedStatement,ResultSet resultSet) {
		try {
			if(resultSet!=null)
				resultSet.close();
			if(preparedStatement!=null)
				preparedStatement.close();
			if(conn!=null) 
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
