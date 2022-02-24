package entity;

public class User {
	/**
	 * username 用户名
	 * password 密码
	 * doctor_id 用于关联医生信息（非医生身份时为空）
	 * patient_id 用于关联病人信息(非病人身份时为空)
	 * player用于区分注册类型,0表示病人，1表示医生
	 */
	private String username;
	private String password;
	private String doctor_id;
	private String patient_id;
	private int player;
	
	public User() {
		
	}

	public User(String username, String password, String doctor_id, String patient_id, int player) {
		super();
		this.username = username;
		this.password = password;
		this.doctor_id = doctor_id;
		this.patient_id = patient_id;
		this.player = player;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	

	
}
