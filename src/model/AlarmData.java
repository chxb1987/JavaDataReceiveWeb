package model;

public class AlarmData {
	private String id; //���ػ�����id
	private byte f;//����ʱ���־λ
	private String no;//��������
	private String time;//��������������ʱ��
	private String ctt;//��������
	
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
