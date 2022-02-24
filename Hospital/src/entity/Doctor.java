package entity;

public class Doctor {
	private String doctor_id;
	private String name;
	private String department;
	private String telephone;
	
	
	public Doctor() {
		
	}


	public Doctor(String doctor_id, String name, String department, String telephone) {
		super();
		this.doctor_id = doctor_id;
		this.name = name;
		this.department = department;
		this.telephone = telephone;
	}


	public String getDoctor_id() {
		return doctor_id;
	}


	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	@Override
	public String toString() {
		return "Doctor [doctor_id=" + doctor_id + ", name=" + name + ", department=" + department + ", telephone="
				+ telephone + "]";
	}
	
	
	
}
