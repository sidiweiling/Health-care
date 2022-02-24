package entity;

import java.util.Date;

public class Appointment {
	/**
	 * app_id 预约号
	 * app_time	预约时间
	 * app_department 预约科室
	 * live 预约有效标志,0表示失效（即已处理或者过时失效）,1表示有效（即仍未处理）
	 */
	private String app_id;
	private Date app_time;
	private String app_department;
	private String doctor_id;
	private String patient_id;
	private int live;
	
	public Appointment() {
		
	}

	public Appointment(String app_id, Date app_time, String app_department, String doctor_id, String patient_id,
			int live) {
		super();
		this.app_id = app_id;
		this.app_time = app_time;
		this.app_department = app_department;
		this.doctor_id = doctor_id;
		this.patient_id = patient_id;
		this.live = live;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public Date getApp_time() {
		return app_time;
	}

	public void setApp_time(Date app_time) {
		this.app_time = app_time;
	}

	public String getApp_department() {
		return app_department;
	}

	public void setApp_department(String app_department) {
		this.app_department = app_department;
	}

	public String getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public int getLive() {
		return live;
	}

	public void setLive(int live) {
		this.live = live;
	}
	
	
	
	
}
