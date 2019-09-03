package com.app.bzpower.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


public class LockTest {

	public void loginTest() throws Exception {


	}
	
	public static String[] dateFormat(String date) {
        String [] strArray = date.split(" ");
        return strArray;
	}
	
	public static void main(String[] args) {
		Connection conn = JdbcUtils.getConn();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = df.format(new Date());
        
		String sql = "select * from log";
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()) {
				String requestTime = rs.getString(6);
				String[] requestDateArray = dateFormat( requestTime);
				String[] currentDateArray = dateFormat( currentDate);
				if(requestDateArray.length > 0) {
						boolean flag = requestDateArray[0].equals(currentDateArray[0]);
						if(flag) {
							System.out.println(requestTime);
						}
					
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
}
