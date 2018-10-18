package domain;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import dao.GeneralDao;
import model.CommonData;
import service.DataPushService;

public class ProjectData {
	//这个队列作为数据缓存
	public static LinkedBlockingQueue<CommonData> queue =  new LinkedBlockingQueue<CommonData>();
	
	private static ArrayList<String> idList = new ArrayList<String>();
	
	/**
	 * 如果注册过了，则返回真；如果未注册则返回假
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
	 * 保存到数据库
	 */
	synchronized public static void saveCommondataToDB(){
		if(!queue.isEmpty())
		{
			System.out.println("从缓存队列中取出数据");
			CommonData cd = queue.poll();			
			boolean esr=DataPushService.pushToSql(cd);
			if(esr){
				System.out.println("---------------------------------数据已存入数据库--------------------------------"); 
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






