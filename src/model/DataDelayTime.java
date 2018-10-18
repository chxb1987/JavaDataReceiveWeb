package model;


/**
 * ��ʱʱ�����ݶ���
 * @author wei
 *	
 */
public class DataDelayTime {
	
	private String 	cncid;	//����ID
	private String 	terminalid; //�ɼ��ն�ID
	private int     datatype ;  //��������
	private long    numofmsg ;  // �˴η�����Ϣ������
	private long    numofmsgunsent ;  //δ���͵ı�����������
	private long    delaytime;  // ��ʱʱ�䣬��λ ms
	private long    packagesize; //���ݰ���С���������ݣ�����λ KB
	private double  speed ;     //����
	private String  ts; //ʱ���
	
	
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
