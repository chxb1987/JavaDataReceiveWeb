package tools;

import java.util.ArrayList;
import java.util.List;

import model.AlarmData;
import model.CommonData;
import model.DataDelayTime;
import model.LogData;
import model.RegisterData;
import model.RunData;
import model.Test;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ToolJson {
	/**
	 * @param jstr
	 * @return
	 * 将Json字符串转化为CommonData对象
	 */
/*	public static CommonData parseCommonDataFromJson(String jstr){
		CommonData cd = null;
		try{
			JSONObject jobject = JSONObject.fromObject(jstr);
			cd = (CommonData)JSONObject.toBean(jobject, CommonData.class);
		}catch(Exception e){
			cd = null;
			e.printStackTrace();
		}
		return cd;
	}*/
	
	/**
	 * @param jstr
	 * @return
	 * 将Json字符串转化为List<RunData>对象
	 */
	@SuppressWarnings("unchecked")
	public static List<RunData> parseRunDataFromJson(String jstr){
		ArrayList<RunData> lrd = null;
		try{
			JSONArray ja = JSONArray.fromObject(jstr);
			lrd = (ArrayList<RunData>)JSONArray.toCollection(ja, RunData.class);
		}catch(Exception e){
//			e.printStackTrace();
		}
		return lrd;
	}
	
	/**
	 * @param jstr
	 * @return
	 * 将Json字符串转化为RegisterData对象
	 */
	public static RegisterData parseRegisterDataFromJson(String jstr){
		RegisterData rd = null;
		try{
			JSONObject jo = JSONObject.fromObject(jstr);
			rd = (RegisterData)JSONObject.toBean(jo, RegisterData.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rd;
	}
	
	/**
	 * @param jstr
	 * @return
	 * 将Json字符串转化为LogData对象
	 */
	public static LogData parseLogDataFromJson(String jstr){
		LogData ld = null;
		try{
			JSONObject jo = JSONObject.fromObject(jstr);
			ld = (LogData)JSONObject.toBean(jo, LogData.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ld;
	}
	
	/**
	 * @param jstr
	 * @return
	 * 将Json字符串转化为AlarmData对象
	 */
	public static AlarmData parseAlarmDataFromJson(String jstr){
		AlarmData ad = null;
		try{
			JSONObject jo = JSONObject.fromObject(jstr);
			ad = (AlarmData)JSONObject.toBean(jo, AlarmData.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ad;
	}
	
	/**
	 * @param jstr
	 * @return
	 * 将Json字符串转化为DataDelayTime对象
	 */
	public static DataDelayTime parseDataDelayTimeFromJson(String jstr){
		DataDelayTime dt = null;
		try{
			JSONObject jo = JSONObject.fromObject(jstr);
			dt = (DataDelayTime)JSONObject.toBean(jo, DataDelayTime.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		return dt;
	}
	
	/*@SuppressWarnings("unchecked")
	public static void main(String args[]){
		RunData ld = new RunData();
		JSONObject jo = JSONObject.fromObject(ld);
		System.out.println(jo.toString());
		
		String s = "{\"did\":2,\"dt\":\"11\"}";
		
		CommonData cd = null;
		cd=(CommonData)JSONObject.toBean(JSONObject.fromObject(s), CommonData.class);
		
		if(cd.getDid() == 1){
			System.out.println("tr");
		}else if(cd.getDid() ==2){
			System.out.println("tr2");
		}

		ArrayList<CommonData> list = new ArrayList<CommonData>();
		list.add(cd);
//		list.add(ld2);
//		list.add(ld3);
		JSONArray jo1 = JSONArray.fromObject(list);
		System.out.println(jo1.toString());
		
		String ss = "[{\"did\":2,\"dt\":\"11\"}]";
		JSONArray jo2 = JSONArray.fromObject(ss);
		ArrayList<CommonData> list1=null;
		list1=(ArrayList<CommonData>)JSONArray.toCollection(jo2, CommonData.class);
		
		System.out.println(list1.size());
		CommonData c = list1.get(0);
		System.out.println(c.getDt());
		System.out.println(c.getDid());
		
		ArrayList<CommonData> list2 = new ArrayList<CommonData>();
		list2.add(new CommonData());
		list2.add(new CommonData());
		list2.add(new CommonData());
		Test t =new Test();
		t.setTid("111");
		t.setList(list2);
		
		JSONObject j = JSONObject.fromObject(t);
		System.out.println(j.toString());
		JSONObject jj = JSONObject.fromObject(j.toString());
		t=null;
		 Test tt = (Test)JSONObject.toBean(jj, Test.class);
		 System.out.println(tt.getTid());
		 System.out.println(tt.getList().size());	 
		 
	}*/
}
