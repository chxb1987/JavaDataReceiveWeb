package service;

import java.util.List;

import dao.GeneralDao;
import domain.ProjectData;
import model.CommonData;
import model.LogData;
import model.RegisterData;
import model.RunData;
import tools.ToolJson;

public class DataPushService {
	
	public static boolean pushToSql(CommonData cd) {
		boolean res = false;		
		if (cd != null) {
			// ��һ��ͨ�õ����ݿⴢ��ӿھͿ��Դ������ݿ�
			// if(cd.getDid() < 4)//����ֻ�ܴ���ע����Ϣ��������Ϣ��������Ϣ
			// return
			// GeneralDao.saveObject(ToolJson.parseRunDataFromJson(cd.getDt()));

			switch (cd.getDid()) {
			case 1:// ע����Ϣ
			{
				RegisterData registerData = ToolJson.parseRegisterDataFromJson(cd.getDt());
				if(ProjectData.isregistered(registerData.getId()))//�ü���Ƿ�ע��
				{
//					res = GeneralDao.saveObject(registerDataToLogData(registerData));//����Ѿ�ע����ˣ���ô��һ���ǵ�¼��Ϣ
				}
				else
				{
					res = GeneralDao.saveObject(registerData);//���û��ע�������ô��һ����ע����Ϣ				
				}
			}
				break;
			case 2:// ������Ϣ
				res = GeneralDao.saveObject(ToolJson.parseAlarmDataFromJson(cd.getDt()));
				break;
			case 3:// ������Ϣ
				try {
					List<RunData> list = ToolJson.parseRunDataFromJson(cd.getDt());
					for (RunData runData : list) {
						GeneralDao.saveObject(runData);
					}
					res = true;
				} catch (Exception e) {
					e.printStackTrace();
					res = false;					
				}
				break;			
		
			case 4:	//��¼��Ϣ return
				  res =GeneralDao.saveObject(ToolJson.parseLogDataFromJson(cd.getDt()));
				  break;
				  
			case 6: //��ʱʱ��
				 res =GeneralDao.saveObject(ToolJson.parseDataDelayTimeFromJson(cd.getDt()));
				
				break;
			default:break;
			}
		}
		return res;
	}

	private static LogData registerDataToLogData(RegisterData registerData) {		
		LogData logData = new LogData();
		logData.setId(registerData.getId());
		logData.setTime(registerData.getTime());
		logData.setOntime(0);
		logData.setRuntime(0);
		return logData;
	}

}

