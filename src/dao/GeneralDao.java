package dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
//import cn.netjava.factory.Connect2DBFactory;
//import cn.netjava.pojo.UserInfo;
 
public class GeneralDao {
	
	/**
     * �����󱣴浽���ݿ���
     *
     * @param object
     *            ����Ҫ����Ķ���
     * @return������ִ�еĽ��;1:��ʾ�ɹ���0:��ʾʧ��
     */
    public static boolean saveObject(Object object) {
    	boolean res = false;
        Connection con = DBFactory.getDBConnection();
        String sql = getSaveObjectSql(object);
        Statement stmt = null;
        try {
            // Statement statement=(Statement) con.createStatement();
        	stmt =con.createStatement();
        	stmt.executeUpdate(sql);
            res = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
	
    /**
     * ��������������sql���,Ӧ�õķ��䣬����˳����ͨ����
     * ���������ԭ��ͨ���������ﵽĿ��
     *
     * @param object
     *            ����Ҫ����Ķ���
     * @return����������sql���
     */
    @SuppressWarnings("unchecked")
	public static String getSaveObjectSql(Object object) {
        // ����һ��sql�ַ���
        String sql = "insert into ";
        // �õ��������
        @SuppressWarnings("rawtypes")
		Class c = object.getClass();
        // �õ����������еķ���
        Method[] methods = c.getMethods();
        // �õ����������е�����
        @SuppressWarnings("unused")
		Field[] fields = c.getFields();
        // �õ������������
        String cName = c.getName();
        // ����������н���������
        String tableName = cName.substring(cName.lastIndexOf(".") + 1,
                cName.length());
        sql += tableName + "(";
        List<String> mList = new ArrayList<String>();
        @SuppressWarnings("rawtypes")
		List vList = new ArrayList();
        for (Method method : methods) {
            String mName = method.getName();
            if (mName.startsWith("get") && !mName.startsWith("getClass")) //�õ����е�get���Է���
            {
                String fieldName = mName.substring(3, mName.length());//��ȡ�ֶε�����
//                System.out.println("�ֶ�����----->" + fieldName);
                mList.add(fieldName);
                
                try {
                    Object value = method.invoke(object);
//                    System.out.println("ִ�з������ص�ֵ��" + value);
                    if (value instanceof String) 
                    {
//                    	System.out.println("�ֶ�ֵ------>" + value);//�õ����Զ�Ӧ��ֵ
                        vList.add("\'" + value + "\'");//�ַ���ǰ��Ӧ���������,SQLServer����ӵ�����                        
                    } else 
                    {
                        vList.add(value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < mList.size(); i++) //һ��������ֶ�
        {
            if (i < mList.size() - 1) {
                sql += mList.get(i) + ",";
            } else {
                sql += mList.get(i) + ") values(";
            }
        }
        for (int i = 0; i < vList.size(); i++) //һ�����������ֵ
        {
            if (i < vList.size() - 1) {
                sql += vList.get(i) + ",";
            } else {
                sql += vList.get(i) + ")";
            }
        }
 
        return sql;
    }
 
    //�������ݿ��������id��Ӧ�Ļ�����Ϣ�Ƿ����
  	public static boolean getIDFromDB(String SNNum)
  	{
//  		String table = "RegisterData";
  		String table = "registerdata";    //������mysql���ݿ����ֻ֧��Сд
  		ResultSet rs = commonQuery(table, SNNum);
  		boolean res = false;
  		if(rs != null)
  		{
	  		int result = -1;
	  		try {
	  			if(rs.next())
	  			{
//	  				result = rs.getInt("ID");
//	  				if(result >= 0)
	  				res = true;
	  			}
	  		} catch (SQLException e) {
	  			e.printStackTrace();
	  		}
  		}
  		return res;
  	}    
    
    private static ResultSet commonQuery(String table, String SNNum) {
		try {
			String SQL = "SELECT * FROM " + table + " WHERE id = '" + SNNum + "'";
			Statement stmt = DBFactory.getDBConnection().createStatement();
			ResultSet res = stmt.executeQuery(SQL);

			return res;
			// Iterate through the data in the result set and display it.
			/*
			 * if(rs.wasNull()) System.out.println("null"); while (rs.next()) {
			 * System.out.println(rs.getString(2) + " " + rs.getString(3)); }
			 */
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
    
    
   /* public static ResultSet commonQuery(String table, int ID) {
		try {
			String SQL = "SELECT * FROM " + table + " WHERE id='" + ID + "'";
			Connection con = DBFactory.getDBConnection();
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(SQL);
			return res;
			// Iterate through the data in the result set and display it.
			
			 * if(rs.wasNull()) System.out.println("null"); while (rs.next()) {
			 * System.out.println(rs.getString(2) + " " + rs.getString(3)); }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}*/
 
    /**
     * �����ݿ���ȡ�ö���
     *
     * @param arg0
     *            ��������������
     * @param id
     *            �������id
     * @return:��Ҫ���ҵĶ���
     */
    public Object getObject(String className, int Id) {
        // �õ�������
        String tableName = className.substring(className.lastIndexOf(".") + 1,
                className.length());
        // ��������������Class����
        @SuppressWarnings("rawtypes")
		Class c = null;
        try {
            c = Class.forName(className);
 
        } catch (ClassNotFoundException e1) {
 
            e1.printStackTrace();
        }
        // ƴ�ղ�ѯsql���
        String sql = "select * from " + tableName + " where Id=" + Id;
        System.out.println("����sql��䣺" + sql);
        // ������ݿ�����
        Connection con = DBFactory.getDBConnection();
        // �������ʵ��
        Object obj = null;
        try {
 
            Statement stm = con.createStatement();
            // �õ�ִ�в�Ѱ��䷵�صĽ����
            ResultSet set = stm.executeQuery(sql);
            // �õ�����ķ�������
            Method[] methods = c.getMethods();
            // ���������
            while (set.next()) {
                obj = c.newInstance();
                // ��������ķ���
                for (Method method : methods) {
                    String methodName = method.getName();
                    // �������ķ�����set��ͷ
                    if (methodName.startsWith("set")) {
                        // ���ݷ������ֵõ����ݱ�����ֶε�����
                        String columnName = methodName.substring(3,
                                methodName.length());
                        // �õ������Ĳ�������
                        Class[] parmts = method.getParameterTypes();
                        if (parmts[0] == String.class) {
                            // �������ΪString���ͣ���ӽ�����а�������ȡ�ö�Ӧ��ֵ������ִ�и�set����
                            method.invoke(obj, set.getString(columnName));
                        }
                        if (parmts[0] == int.class) {
                            method.invoke(obj, set.getInt(columnName));
                        }
                    }
 
                }
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}



