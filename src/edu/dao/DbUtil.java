package edu.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DbUtil {
	
	private static String className = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/db_contacts?characterEncoding=UTF-8";
	private static String name = "root";
	private static String pwd = "123456";
	private static Connection conn = null;
	
	public static Connection getConnection() {
		try {
			Class.forName(className);
			conn = (Connection) DriverManager.getConnection(url,name,pwd);
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
