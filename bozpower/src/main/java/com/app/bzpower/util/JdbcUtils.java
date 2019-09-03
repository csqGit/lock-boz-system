package com.app.bzpower.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtils {
	
	private static String driver = "com.mysql.jdbc.Driver";
	
	private static String url = "jdbc:mysql://localhost:3306/dblock?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT";
	
	private static String user = "root";
	
	private static String password = "Bozpower123#";
	
	public static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(driver);
			 conn = DriverManager.getConnection(url, user, password);
			 System.out.println(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		JdbcUtils.getConn();
	}

}
