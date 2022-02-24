package net.chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ResourceBundle;


public class BaseConn {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	/**
	 * BaseConn的构造函数，在这里我们完成数据库的初始华操作，即连接数据库操作
	 */

	public BaseConn() throws SQLException, ClassNotFoundException {
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://127.0.0.1:1433; DatabaseName=health";
		String username = "sa";
		String password = "a19751022";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			System.out.println("Error occured when Connect DataBase:" + e);
			throw e;
		} catch (ClassNotFoundException e) {
			System.out.println("Error occured when Connect DataBase:" + e);
			throw e;
		}
	}

	/**
	 * 初始化预编译的 SQL 语句的对象
	 */
	public PreparedStatement preparedStatement(String sql) throws SQLException {
		try {
			ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			return ps;
		} catch (SQLException e) {
			System.out.println("Error occured when create preparedStatement:"
					+ e);
			throw e;
		}
	}

	/**
	 * 执行静态 SQL 查询语句并返回它所生成结果的对象
	 */
	public ResultSet executeQuery(String sql) throws SQLException {
		rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.out.println("Error occured when query database：" + ex);
			throw ex;
		}
		return rs;
	}

	/**
	 * 执行静态SQL更新语句并返回影响数据条数
	 */
	public int executeUpdate(String sql) throws SQLException {
		try {
			conn.setAutoCommit(false);
			int re = stmt.executeUpdate(sql);
			conn.commit();
			return re;
		} catch (SQLException e) {
			conn.rollback();
			System.out.println("Error occured when update database：" + e);
			throw e;
		}
	}

	/**
	 * 执行预编译的SQL查询语句
	 * 
	 */
	public ResultSet executeQuery() throws SQLException {
		try {
			return ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("Error occured when query database：" + e);
			throw e;
		}
	}

	/**
	 * 执行预编译的SQL更新语句
	 * 
	 */
	public int executeUpdate() throws SQLException {
		try {
			conn.setAutoCommit(false);
			int r = ps.executeUpdate();
			conn.commit();
			return r;
		} catch (SQLException e) {
			conn.rollback();
			System.out.println("Error occured when update database：" + e);
			throw e;
		}
	}

	/**
	 * 数据库关闭操作
	 */
	public boolean closeDB() throws SQLException {
		try {
			if (this.rs != null)
				rs.close();
			if (this.stmt != null)
				this.stmt.close();
			if (this.ps != null)
				this.ps.close();
			if (this.conn != null)
				conn.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Error occured when close database：" + e);
			throw e;
		}
	}
}