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
        System.out.println("���ݿ�д���߳̽���");
        
        stopThreadRunning();
    }
 
    @Override
    public void contextInitialized(ServletContextEvent event) {
    	System.out.println("�������ݿ�д���߳�");
//      new Thread(new DataPushThread()).start();//�������ݿ�д���߳�
    	super.start();// ����һ���߳�  
    }
    
    @Override
	public void run() {
		while (threadRunningFlag) {
			//�����������������ݣ���ô�Ͱ���Щ���ݴ������ݿ�
			//ע�⣺��һ�����еĵ����ݶ��ܹ��洢�ɹ��������ظ�ע������ݣ�Ӧ�ð���Щ�ظ�ע��������ڴ洢��ʱ����Ϊ��¼������
//			System.out.println("��⻺������Ƿ�Ϊ��");
	/*		if(!ProjectData.queue.isEmpty())
			{
				System.out.println("�ӻ��������ȡ������");
				CommonData cd = ProjectData.queue.poll();			
				boolean esr=DataPushService.pushToSql(cd);
				if(esr){
					System.out.println("---------------------------------�����Ѵ������ݿ�--------------------------------"); 
				}				
			}*/
			
			ProjectData.saveCommondataToDB();
			
			//����˯�ߣ���ֹ�߳�ռ�ù���CPU��Դ
			//����������ʱ���ʵ�����˯��ʱ��
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