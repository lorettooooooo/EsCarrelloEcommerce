package it.objectmethod.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private static final String URL = "jdbc:mysql://localhost:33060/ecommerce";
	private static final String USER = "omdev";
	private static final String PASS = "omdev";
	public static Connection getConnection() {
		Connection conn = null;
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
}
