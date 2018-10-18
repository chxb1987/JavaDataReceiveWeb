package pushdata;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import domain.ProjectData;
import model.CommonData;
import service.DataPushService;
 
public class MyServletContextListener extends Thread implements ServletContextListener{
 
	private volatile boolean threadRunningFlag=true;
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("数据库写入线程结束");
        
        stopThreadRunning();
    }
 
    @Override
    public void contextInitialized(ServletContextEvent event) {
    	System.out.println("开启数据库写入线程");
//      new Thread(new DataPushThread()).start();//开启数据库写入线程
    	super.start();// 启动一个线程  
    }
    
    @Override
	public void run() {
		while (threadRunningFlag) {
			//如果缓存队列中有数据，那么就把这些数据存入数据库
			//注意：不一定所有的的数据都能够存储成功，比如重复注册的数据，应该把这些重复注册的数据在存储的时候视为登录的数据
//			System.out.println("检测缓存队列是否为空");
	/*		if(!ProjectData.queue.isEmpty())
			{
				System.out.println("从缓存队列中取出数据");
				CommonData cd = ProjectData.queue.poll();			
				boolean esr=DataPushService.pushToSql(cd);
				if(esr){
					System.out.println("---------------------------------数据已存入数据库--------------------------------"); 
				}				
			}*/
			
			ProjectData.saveCommondataToDB();
			
			//加入睡眠，防止线程占用过多CPU资源
			//数据量较少时，适当增加睡眠时间
			if(ProjectData.getQueueSize()<10){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {				
					e.printStackTrace();
				}				
			}else{
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {				
					e.printStackTrace();
				}
			}
		}				
	}
    
    //stop thread running
    private void stopThreadRunning(){
    	this.threadRunningFlag=false;
    }
 
}