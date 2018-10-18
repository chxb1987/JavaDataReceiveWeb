package model;

public class CommonData {
	private byte did; //数据类型
	private String dt;//数据内容
	
	public CommonData(){}
	
	public CommonData(byte did, String dt){
		this.did = did;
		this.dt = dt;
	}
	
	public byte getDid() {
		return did;
	}
	public void setDid(byte did) {
		this.did = did;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	
}
