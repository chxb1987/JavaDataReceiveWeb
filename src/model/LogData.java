package model;

public class LogData {
	private String id;//����ϵͳid
	private long ontime;//�ۼ�����ʱ��
	private long runtime;//�ۼƼӹ�ʱ��
	private String time;//ʱ���
	
	public LogData(){}
	public LogData(String id,long ontime,long runtime,String time){
		this.id = id;
		this.ontime = ontime;
		this.runtime = runtime;
		this.time = time;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getOntime() {
		return ontime;
	}
	public void setOntime(long ontime) {
		this.ontime = ontime;
	}
	public long getRuntime() {
		return runtime;
	}
	public void setRuntime(long runtime) {
		this.runtime = runtime;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
