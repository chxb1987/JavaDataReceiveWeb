package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author fan
 *
 */
public class DBFactory {
	
	private DBFactory()
	{}
	
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	private static Connection con = null;
	
	//static代码块
	static{
		try{  
			
			InputStream ips = DBFactory.class.getResourceAsStream("./../resources/database.properties");
			Properties props = new Properties();			
			props.load(ips);
						
			driver = props.getProperty("driver");
			url = props.getProperty("url");
			username = props.getProperty("user");
			password = props.getProperty("password");	
			
//			driver="com.mysql.jdbc.Driver";
//			url="jdbc:mysql://127.0.0.1:3306/db_one";
//			url="jdbc:mysql://rm-uf615642lf08v9b01o.mysql.rds.aliyuncs.com:3306/db_one";
//			username="root";  
//			password="dongwei@150";
						
		}catch(Exception e)
		{
			System.out.println("database properties error");
			e.printStackTrace();
		}
		
		try {//加载数据库驱动
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("加载数据库驱动出错");
		}
		
		try {   //和数据库建立连接
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("和数据库建立连接出错");
		}
	}
	
	//get  database connection 
	public static Connection getDBConnection() {
		if(!getConnectionState()){
			reConnecting();
		}
		
		return con;
	}
	
	//rebuild connection with database
	public static Connection reConnecting(){
		if(con!=null){
			closeConnection();
		}
		try {   //和数据库建立连接
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("和数据库建立连接出错");
		}
		return con;		
	}
	
	//checking the connection if is valid
	public static boolean getConnectionState(){
		boolean ConnecionState=false;
		if(con!=null){
			try {
				ConnecionState=con.isValid(1000*10);
			} catch (SQLException e) {
				ConnecionState=false;
				e.printStackTrace();
			}
		}
		
		return ConnecionState;
	}
	
	//close database connection
	public static void closeConnection(){
		if(con!=null){
			try {
				con.close();
				con=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
