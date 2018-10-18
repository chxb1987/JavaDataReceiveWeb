package domain;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import dao.GeneralDao;
import model.CommonData;
import service.DataPushService;

public class ProjectData {
	//���������Ϊ���ݻ���
	public static LinkedBlockingQueue<CommonData> queue =  new LinkedBlockingQueue<CommonData>();
	
	private static ArrayList<String> idList = new ArrayList<String>();
	
	/**
	 * ���ע����ˣ��򷵻��棻���δע���򷵻ؼ�
	 * @param id
	 * @return
	 */
	public static boolean isregistered(String id)
	{
		boolean isregistered = false;
		if(idList.contains(id))
			isregistered =true;
		else 
		{
			if(GeneralDao.getIDFromDB(id))
			{
				isregistered =true;
				idList.add(id);
			}
		}
		return isregistered;
	}
		
	/**
	 * ���浽���ݿ�
	 */
	synchronized public static void saveCommondataToDB(){
		if(!queue.isEmpty())
		{
			System.out.println("�ӻ��������ȡ������");
			CommonData cd = queue.poll();			
			boolean esr=DataPushService.pushToSql(cd);
			if(esr){
				System.out.println("---------------------------------�����Ѵ������ݿ�--------------------------------"); 
			}				
		}	
	}
	
	
	synchronized public static boolean offerCommonDataToQueue(CommonData cd){
		return queue.offer(cd);	
	}
	
	synchronized public static int getQueueSize(){
		return  queue.size();
	}
	
	
}






