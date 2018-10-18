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
			// 用一个通用的数据库储存接口就可以存入数据库
			// if(cd.getDid() < 4)//现在只能存入注册信息，报警信息和运行信息
			// return
			// GeneralDao.saveObject(ToolJson.parseRunDataFromJson(cd.getDt()));

			switch (cd.getDid()) {
			case 1:// 注册信息
			{
				RegisterData registerData = ToolJson.parseRegisterDataFromJson(cd.getDt());
				if(ProjectData.isregistered(registerData.getId()))//得检测是否注册
				{
//					res = GeneralDao.saveObject(registerDataToLogData(registerData));//如果已经注册过了，那么这一条是登录信息
				}
				else
				{
					res = GeneralDao.saveObject(registerData);//如果没有注册过，那么这一条是注册信息				
				}
			}
				break;
			case 2:// 报警信息
				res = GeneralDao.saveObject(ToolJson.parseAlarmDataFromJson(cd.getDt()));
				break;
			case 3:// 运行信息
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
		
			case 4:	//登录信息 return
				  res =GeneralDao.saveObject(ToolJson.parseLogDataFromJson(cd.getDt()));
				  break;
				  
			case 6: //延时时间
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

