package com.test;

import org.junit.Test;

import dao.GeneralDao;
import model.CommonData;
import model.DataDelayTime;
import service.DataPushService;
import tools.JsonUtil;
import tools.ToolJson;

public class TestUnit {
	
	@Test
	public void testDelay(){
		
		DataDelayTime  delaytime=new DataDelayTime("NO0001" ,"ANDROID002",
				3,12,200,300,1024,23,"20171129 17:20:38");
		
		CommonData cd=new CommonData((byte)6,JsonUtil.object2Json(delaytime));
		
		DataPushService.pushToSql(cd);
				
	}

}

