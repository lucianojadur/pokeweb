package databaseProcessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection{
	
	private static final String URL = "";
	private static final String USER = "admin";
	private static final String PASSWORD = "password123";
	

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	
	public static void close(Statement st) throws SQLException {
		st.close();
	}
	
	public static void close(ResultSet rs) throws SQLException {
		rs.close();
	}
	
	public static void close(Connection cn) throws SQLException {
		cn.close();
	}
}