package br.senac.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionFactory {
	private static final String DRIVER = "com.mysql.jdbc.driver";
	private static final String URL = "jdbc:mysql://localhost:3306/bank";
	private static final String	USER = "usuario_bank";
	private static final String	PASS = "";
	
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASS);
	} catch (ClassNotFoundException | SQLException ex) {		
		
		throw new RuntimeException(ex);
		
		}
	}
	
	public static void closeConnection(Connection connection) {
		try {
			if(connection != null) {
				connection.close();
			}
			
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
}