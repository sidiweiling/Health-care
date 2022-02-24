package net.chat;

import java.text.SimpleDateFormat;
import java.sql.*;

public class Message {
	private String msgFrom; // 消息发送者
	private String msgTo; // 消息接收者
	private String msgContent;// 消信内容

	/**
	 * 消息发送者setMsgFrom属性的set方法
	 */
	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}

	/**
	 * 消息接收者setMsgTo属性的set方法
	 */
	public void setMsgTo(String msgTo) {
		this.msgTo = msgTo;
	}

	/**
	 * 消息内容msgContent属性的set方法
	 */
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	/**
	 * 将message对象保存到数据库msgInfo表中的方法
	 */
	public boolean saveToDataBase() throws SQLException, ClassNotFoundException {
		BaseConn conn = null;
		try {
			conn = new BaseConn();
			SimpleDateFormat cal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = cal.format(new java.util.Date());
			String sql = "insert into message(msgFrom,msgTo,msgTime,msgContent) values(?,?,?,?)";
			PreparedStatement ps = conn.preparedStatement(sql);
			ps.setString(1, msgFrom);
			ps.setString(2, msgTo);
			ps.setString(3, time);
			ps.setString(4, msgContent);
			conn.executeUpdate();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw ex;
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
}
