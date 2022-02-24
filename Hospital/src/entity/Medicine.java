package entity;

public class Medicine {
	/**
	 * 用于药品管理
	 * (pres_id,medicine)为主键，通过pres_id查询得到多个药品组成药方
	 */
	private String pres_id;
	private String medicine;
	
	public Medicine() {
		
	}
	
	public Medicine(String pres_id, String medicine) {
		super();
		this.pres_id = pres_id;
		this.medicine = medicine;
	}
	public String getPres_id() {
		return pres_id;
	}
	public void setPres_id(String pres_id) {
		this.pres_id = pres_id;
	}
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	
}
