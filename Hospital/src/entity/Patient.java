package entity;

public class Patient {
	/**
	 * sex的0表示男，1表示女
	 */
	private String patient_id;
	private String name;
	private int sex;
	private String address;
	private String telephone;
	
	public Patient() {
		
	}

	public Patient(String patient_id, String name, int sex, String address, String telephone) {
		super();
		this.patient_id = patient_id;
		this.name = name;
		this.sex = sex;
		this.address = address;
		this.telephone = telephone;
	}

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "Patient [patient_id=" + patient_id + ", name=" + name + ", sex=" + sex + ", address=" + address
				+ ", telephone=" + telephone + "]";
	}

	
	
}
