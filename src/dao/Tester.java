package dao;

import org.junit.Test;

import model.AlarmData;

public class Tester {
	
	public static void main(String args[]) {
        AlarmData alarmData = new AlarmData();
        alarmData.setId("1111111111");
        alarmData.setCtt("��ͣ");
        alarmData.setNo("2222222222");
        alarmData.setTime("20170317");
        alarmData.setF((byte)0);
        boolean SQLstring = GeneralDao.saveObject(alarmData);
        System.out.println("��ȡ������Ϣ��" + SQLstring);

        boolean esr=GeneralDao.saveObject(alarmData); //���汨����Ϣ�����������ݿ�
    	if(esr){
			System.out.println("---------------------------------�����Ѵ������ݿ�--------------------------------"); 
		}
    }
	
	@Test
	public void test(){
		
		  AlarmData alarmData = new AlarmData();
	        alarmData.setId("1111111111");
	        alarmData.setCtt("��ͣ");
	        alarmData.setNo("2222222222");
	        alarmData.setTime("20170317");
	        alarmData.setF((byte)0);
	        boolean SQLstring = GeneralDao.saveObject(alarmData);
	        System.out.println("��ȡ������Ϣ��" + SQLstring);
	        
	        boolean esr=GeneralDao.saveObject(alarmData); //���汨����Ϣ�����������ݿ�
	    	if(esr){
				System.out.println("---------------------------------�����Ѵ������ݿ�--------------------------------"); 
			}
	 
	}
}
