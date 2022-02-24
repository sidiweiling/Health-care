package entity;

import java.util.Date;

public class Pre_prescription {
	private String con_id;
	private String pres_id;
	private Date date;
	
	public Pre_prescription() {
		
	}
	
	public Pre_prescription(String con_id, String pres_id, Date date) {
		super();
		this.con_id = con_id;
		this.pres_id = pres_id;
		this.date = date;
	}

	public String getCon_id() {
		return con_id;
	}

	public void setCon_id(String con_id) {
		this.con_id = con_id;
	}

	public String getPres_id() {
		return pres_id;
	}

	public void setPres_id(String pres_id) {
		this.pres_id = pres_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
}
