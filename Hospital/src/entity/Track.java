package entity;

public class Track {
	private String track_id;
	private String con_id;
	private String doctor_id;
	private String patient_id;
	private String case_description;
	
	public Track() {
		
	}

	public Track(String track_id, String con_id, String doctor_id, String patient_id, String case_description) {
		super();
		this.track_id = track_id;
		this.con_id = con_id;
		this.doctor_id = doctor_id;
		this.patient_id = patient_id;
		this.case_description = case_description;
	}

	public String getTrack_id() {
		return track_id;
	}

	public void setTrack_id(String track_id) {
		this.track_id = track_id;
	}

	public String getCon_id() {
		return con_id;
	}

	public void setCon_id(String con_id) {
		this.con_id = con_id;
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

	public String getCase_description() {
		return case_description;
	}

	public void setCase_description(String case_description) {
		this.case_description = case_description;
	}
	
	
}
