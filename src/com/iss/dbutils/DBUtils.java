package com.iss.dbutils;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DBUtils {
	static private Connection conn;
	static private PreparedStatement pstmt;
	static private ResultSet rs;
	
	
	static public Connection getConnection() {
		try {
			if(conn==null) {
			Properties properties=new Properties();
			properties.load(DBUtils.class.getResourceAsStream("../../../jdbcConfig.properties"));
			String driver=properties.getProperty("driverClassName");
			String url=properties.getProperty("url");
			String username=properties.getProperty("username");
			String password=properties.getProperty("password");
			Class.forName(driver);
			conn=DriverManager.getConnection(url,user,password);
			}
			return conn;
		} catch (Exception e) {
			System.out.println("连接数据库失败"+e.getMessage());
		}
		return null;
	}
	
	public int update(String sql,Object...obj) {
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			for(int i=0;i<obj.length;i++) {
				pstmt.setObject(1, obj[i]);
			}
			int i=pstmt.executeUpdate();
			return i;
			
		} catch (Exception e) {
			System.out.println("更新数据库失败");
		}
		return 0;
		}
	
	public List<Map<String, Object>> query(String sql,Object...obj) {
		try {
			conn=getConnection();
			pstmt =conn.prepareStatement(sql);
			for(int i=0;i<obj.length;i++) {
				pstmt.setObject(1, obj[i]);
			}
			rs=pstmt.executeQuery();
			
			
			ArrayList<Map<String, Object>> arrayList =new 
			
		} catch ( e) {
			// TODO: handle exception
		}
		
	}
	
	
}
