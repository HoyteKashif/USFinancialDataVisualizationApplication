package com.usdataproject.util.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FdicDatabaseHelper {
	private final static String URL = "jdbc:mysql://localhost:3306";
	private final static String FDIC_USER = "fdic_user";
	private final static String FDIC_USER_PASSWORD = "fdic_user_password";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public final static Connection getConnectionAsFdicUser() {
		try {
			return DriverManager.getConnection(URL, FDIC_USER, FDIC_USER_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void printMetaData() {
		try {
			Connection connection = getConnectionAsFdicUser();
			DatabaseMetaData metaData = connection.getMetaData();
			ResultSet resultSet = metaData.getTables(null, null, "", new String[] { "TABLE" });

			while (resultSet.next()) {
				System.out.println(resultSet.getString("TABLE_NAME"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
