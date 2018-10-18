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
     * 将对象保存到数据库中
     *
     * @param object
     *            ：需要保存的对象
     * @return：方法执行的结果;1:表示成功，0:表示失败
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
     * 解析出保存对象的sql语句,应用的反射，提高了程序的通用性
     * 分析反射的原理，通过反射来达到目的
     *
     * @param object
     *            ：需要保存的对象
     * @return：保存对象的sql语句
     */
    @SuppressWarnings("unchecked")
	public static String getSaveObjectSql(Object object) {
        // 定义一个sql字符串
        String sql = "insert into ";
        // 得到对象的类
        @SuppressWarnings("rawtypes")
		Class c = object.getClass();
        // 得到对象中所有的方法
        Method[] methods = c.getMethods();
        // 得到对象中所有的属性
        @SuppressWarnings("unused")
		Field[] fields = c.getFields();
        // 得到对象类的名字
        String cName = c.getName();
        // 从类的名字中解析出表名
        String tableName = cName.substring(cName.lastIndexOf(".") + 1,
                cName.length());
        sql += tableName + "(";
        List<String> mList = new ArrayList<String>();
        @SuppressWarnings("rawtypes")
		List vList = new ArrayList();
        for (Method method : methods) {
            String mName = method.getName();
            if (mName.startsWith("get") && !mName.startsWith("getClass")) //得到所有的get属性方法
            {
                String fieldName = mName.substring(3, mName.length());//获取字段的名字
//                System.out.println("字段名字----->" + fieldName);
                mList.add(fieldName);
                
                try {
                    Object value = method.invoke(object);
//                    System.out.println("执行方法返回的值：" + value);
                    if (value instanceof String) 
                    {
//                    	System.out.println("字段值------>" + value);//得到属性对应的值
                        vList.add("\'" + value + "\'");//字符串前面应该添加引号,SQLServer是添加单引号                        
                    } else 
                    {
                        vList.add(value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < mList.size(); i++) //一个个添加字段
        {
            if (i < mList.size() - 1) {
                sql += mList.get(i) + ",";
            } else {
                sql += mList.get(i) + ") values(";
            }
        }
        for (int i = 0; i < vList.size(); i++) //一个个添加属性值
        {
            if (i < vList.size() - 1) {
                sql += vList.get(i) + ",";
            } else {
                sql += vList.get(i) + ")";
            }
        }
 
        return sql;
    }
 
    //查找数据库里面机床id对应的机床信息是否存在
  	public static boolean getIDFromDB(String SNNum)
  	{
//  		String table = "RegisterData";
  		String table = "registerdata";    //阿里云mysql数据库表名只支持小写
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
     * 从数据库中取得对象
     *
     * @param arg0
     *            ：对象所属的类
     * @param id
     *            ：对象的id
     * @return:需要查找的对象
     */
    public Object getObject(String className, int Id) {
        // 得到表名字
        String tableName = className.substring(className.lastIndexOf(".") + 1,
                className.length());
        // 根据类名来创建Class对象
        @SuppressWarnings("rawtypes")
		Class c = null;
        try {
            c = Class.forName(className);
 
        } catch (ClassNotFoundException e1) {
 
            e1.printStackTrace();
        }
        // 拼凑查询sql语句
        String sql = "select * from " + tableName + " where Id=" + Id;
        System.out.println("查找sql语句：" + sql);
        // 获得数据库链接
        Connection con = DBFactory.getDBConnection();
        // 创建类的实例
        Object obj = null;
        try {
 
            Statement stm = con.createStatement();
            // 得到执行查寻语句返回的结果集
            ResultSet set = stm.executeQuery(sql);
            // 得到对象的方法数组
            Method[] methods = c.getMethods();
            // 遍历结果集
            while (set.next()) {
                obj = c.newInstance();
                // 遍历对象的方法
                for (Method method : methods) {
                    String methodName = method.getName();
                    // 如果对象的方法以set开头
                    if (methodName.startsWith("set")) {
                        // 根据方法名字得到数据表格中字段的名字
                        String columnName = methodName.substring(3,
                                methodName.length());
                        // 得到方法的参数类型
                        Class[] parmts = method.getParameterTypes();
                        if (parmts[0] == String.class) {
                            // 如果参数为String类型，则从结果集中按照列名取得对应的值，并且执行改set方法
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



