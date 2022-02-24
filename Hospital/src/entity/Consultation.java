package entity;

import java.util.Date;

public class Consultation {
	/**
	 * image_rec为图片路径
	 */
	private String con_id;
	private String app_id;
	private Date con_time;
	private String text_rec;
	private String image_rec;
	
	public Consultation() {
		
	}

	public Consultation(String con_id, String app_id, Date con_time, String text_rec, String image_rec) {
		super();
		this.con_id = con_id;
		this.app_id = app_id;
		this.con_time = con_time;
		this.text_rec = text_rec;
		this.image_rec = image_rec;
	}

	public String getCon_id() {
		return con_id;
	}

	public void setCon_id(String con_id) {
		this.con_id = con_id;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public Date getCon_time() {
		return con_time;
	}

	public void setCon_time(Date con_time) {
		this.con_time = con_time;
	}

	public String getText_rec() {
		return text_rec;
	}

	public void setText_rec(String text_rec) {
		this.text_rec = text_rec;
	}

	public String getImage_rec() {
		return image_rec;
	}

	public void setImage_rec(String image_rec) {
		this.image_rec = image_rec;
	}
	

	
	
}
