package model;

public class AlarmData {
	private String id; //数控机床的id
	private byte f;//报警时间标志位
	private String no;//报警代码
	private String time;//报警发生或消除时间
	private String ctt;//报警内容
	
	public AlarmData(){}
	public AlarmData(String id, byte f, String no, String time, String ctt){
		this.id = id;
		this.f = f;
		this.no = no;
		this.time = time;
		this.ctt = ctt;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public byte getF() {
		return f;
	}
	public void setF(byte f) {
		this.f = f;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCtt() {
		return ctt;
	}
	public void setCtt(String ctt) {
		this.ctt = ctt;
	}
}
