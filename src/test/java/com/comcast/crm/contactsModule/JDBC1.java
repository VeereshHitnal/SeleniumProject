package com.comcast.crm.contactsModule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class JDBC1 {

	public static void main(String[] args) throws SQLException {
		

		Driver driver = new Driver();

		DriverManager.registerDriver(driver);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");

		Statement statement = connection.createStatement();

		ResultSet result = statement.executeQuery("select * from student_info");
	
		
			System.out.println(result.getString(1));
		

		connection.close();

	
	}

}
