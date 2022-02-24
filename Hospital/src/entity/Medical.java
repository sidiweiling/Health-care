package entity;

/**
 * 病历
 *
 */
public class Medical {
	
	private String con_id;
	private String sec_id;
	private String patient_id;
	private String diagnosis;
	
	public Medical() {
		
	}

	public Medical(String con_id, String sec_id, String patient_id, String diagnosis) {
		super();
		this.con_id = con_id;
		this.sec_id = sec_id;
		this.patient_id = patient_id;
		this.diagnosis = diagnosis;
	}

	public String getCon_id() {
		return con_id;
	}

	public void setCon_id(String con_id) {
		this.con_id = con_id;
	}

	public String getSec_id() {
		return sec_id;
	}

	public void setSec_id(String sec_id) {
		this.sec_id = sec_id;
	}

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	

}
