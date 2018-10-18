package dao;

import org.junit.Test;

import model.AlarmData;

public class Tester {
	
	public static void main(String args[]) {
        AlarmData alarmData = new AlarmData();
        alarmData.setId("1111111111");
        alarmData.setCtt("急停");
        alarmData.setNo("2222222222");
        alarmData.setTime("20170317");
        alarmData.setF((byte)0);
        boolean SQLstring = GeneralDao.saveObject(alarmData);
        System.out.println("获取到的信息：" + SQLstring);

        boolean esr=GeneralDao.saveObject(alarmData); //保存报警信息到阿里云数据库
    	if(esr){
			System.out.println("---------------------------------数据已存入数据库--------------------------------"); 
		}
    }
	
	@Test
	public void test(){
		
		  AlarmData alarmData = new AlarmData();
	        alarmData.setId("1111111111");
	        alarmData.setCtt("急停");
	        alarmData.setNo("2222222222");
	        alarmData.setTime("20170317");
	        alarmData.setF((byte)0);
	        boolean SQLstring = GeneralDao.saveObject(alarmData);
	        System.out.println("获取到的信息：" + SQLstring);
	        
	        boolean esr=GeneralDao.saveObject(alarmData); //保存报警信息到阿里云数据库
	    	if(esr){
				System.out.println("---------------------------------数据已存入数据库--------------------------------"); 
			}
	 
	}
}
