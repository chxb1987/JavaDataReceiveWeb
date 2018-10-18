package model;

public class RegisterData {
	private String id; //数控系统id
	private String tp; //数控系统型号
	private String ver;//版本信息
	private String time;//时间戳
	
	public RegisterData(){}
	public RegisterData(String id,String tp,String ver, String time){
		this.id = id;
		this.tp = tp;
		this.ver = ver;
		this.time = time;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTp() {
		return tp;
	}
	public void setTp(String tp) {
		this.tp = tp;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
