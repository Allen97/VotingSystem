package com.iss.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

public class DBUtils {
	static private Connection conn;
	static private PreparedStatement pstmt;
	static private ResultSet rs;

	@Test
	public void test() {
		DBUtils dbUtils = new DBUtils();
		String sql = "select * from tb_ticker where id=?";
		List<Map<String, Object>> query = dbUtils.query(sql,2);
		System.out.println(query);
	}

	static public Connection getConnection() {
		try {
			if (conn == null) {
				Properties properties = new Properties();
				properties.load(DBUtils.class.getResourceAsStream("../../../jdbcConfig.properties"));
				String driver = properties.getProperty("driverClassName");
				String url = properties.getProperty("url");
				String username = properties.getProperty("username");
				String password = properties.getProperty("password");

				Class.forName(driver);
				conn = DriverManager.getConnection(url, username, password);
			}
			return conn;
		} catch (Exception e) {
			System.out.println("数据库连接失败" + e.getMessage());
		}
		return null;
	}

	public int update(String sql, Object... obj) {
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(1, obj[i]);
			}
			int i = pstmt.executeUpdate();
			return i;
		} catch (Exception e) {
			System.out.println("更新失败");
		}
		return 0;
	}

	public List<Map<String, Object>> query(String sql, Object... obj) {
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(1, obj[i]);
			}
			rs = pstmt.executeQuery();
			////////////////////////
			ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					map.put(metaData.getColumnLabel(i + 1), rs.getObject(i + 1));
				}

				arrayList.add(map);
			}
			return arrayList;
			///////////////////////
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			System.out.println("??????");
		}
	}
}
