package entity;

public class Prescription {
	private String pres_id;
	private String medical_advice;
	
	public Prescription() {
		
	}

	public Prescription(String pres_id, String medical_advice) {
		super();
		this.pres_id = pres_id;
		this.medical_advice = medical_advice;
	}

	public String getPres_id() {
		return pres_id;
	}

	public void setPres_id(String pres_id) {
		this.pres_id = pres_id;
	}

	public String getMedical_advice() {
		return medical_advice;
	}

	public void setMedical_advice(String medical_advice) {
		this.medical_advice = medical_advice;
	}
	
	
}
