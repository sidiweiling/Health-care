package entity;

import java.util.Date;

public class Message {
	private Date msgTime;
	private String msgFrom;
	private String msgTo;
	private String msgContent;
	
	public Message() {
		
	}

	public Message(Date msgTime, String msgFrom, String msgTo, String msgContent) {
		super();
		this.msgTime = msgTime;
		this.msgFrom = msgFrom;
		this.msgTo = msgTo;
		this.msgContent = msgContent;
	}

	public Date getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(Date msgTime) {
		this.msgTime = msgTime;
	}

	public String getMsgFrom() {
		return msgFrom;
	}

	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}

	public String getMsgTo() {
		return msgTo;
	}

	public void setMsgTo(String msgTo) {
		this.msgTo = msgTo;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	
	
}
