package model;


/**
 * 延时时间数据对象
 * @author wei
 *	
 */
public class DataDelayTime {
	
	private String 	cncid;	//机床ID
	private String 	terminalid; //采集终端ID
	private int     datatype ;  //数据类型
	private long    numofmsg ;  // 此次发送信息的条数
	private long    numofmsgunsent ;  //未发送的本地数据条数
	private long    delaytime;  // 延时时间，单位 ms
	private long    packagesize; //数据包大小（有用数据），单位 KB
	private double  speed ;     //速率
	private String  ts; //时间戳
	
	
	public DataDelayTime() {	}

	

	public DataDelayTime(String cncid, String terminalid, int datatype, long numofmsg, long numofmsgunsent,
			long delaytime, long packagesize, double speed, String ts) {
		
		this.cncid = cncid;
		this.terminalid = terminalid;
		this.datatype = datatype;
		this.numofmsg = numofmsg;
		this.numofmsgunsent = numofmsgunsent;
		this.delaytime = delaytime;
		this.packagesize = packagesize;
		this.speed = speed;
		this.ts = ts;
	}

	@Override
	public String toString() {
		return "DataDelayTime [cncid=" + cncid + ", terminalid=" + terminalid + ", datatype=" + datatype + ", numofmsg="
				+ numofmsg + ", numofmsgunsent=" + numofmsgunsent + ", delaytime=" + delaytime + ", packagesize="
				+ packagesize + ", speed=" + speed + ", ts=" + ts + "]";
	}



	public String getCncId() {
		return cncid;
	}


	public void setCncId(String cncid) {
		this.cncid = cncid;
	}


	public String getTerminalid() {
		return terminalid;
	}


	public void setTerminalid(String terminalid) {
		this.terminalid = terminalid;
	}


	public int getDatatype() {
		return datatype;
	}


	public void setDatatype(int datatype) {
		this.datatype = datatype;
	}


	public long getNumofmsg() {
		return numofmsg;
	}


	public void setNumofmsg(long numofmsg) {
		this.numofmsg = numofmsg;
	}


	public long getNumofmsgunsent() {
		return numofmsgunsent;
	}


	public void setNumofmsgunsent(long numofmsgunsent) {
		this.numofmsgunsent = numofmsgunsent;
	}


	public long getDelaytime() {
		return delaytime;
	}


	public void setDelaytime(long delaytime) {
		this.delaytime = delaytime;
	}


	public long getPackagesize() {
		return packagesize;
	}


	public void setPackagesize(long packagesize) {
		this.packagesize = packagesize;
	}


	public double getSpeed() {
		return speed;
	}


	public void setSpeed(double speed) {
		this.speed = speed;
	}


	public String getTs() {
		return ts;
	}


	public void setTs(String ts) {
		this.ts = ts;
	}

	
		



	


}
