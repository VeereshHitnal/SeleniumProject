package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DeleteDataFromTableTest {

	public static void main(String[] args) throws SQLException {
		
		//Create the Driver Object
		Driver driver = new Driver();
		
		//Register the Driver
		DriverManager.registerDriver(driver);
		
		//Get the connection by providing the url, username and password
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");
		
		//Create the Statement
		Statement statement = connection.createStatement();
		
		//Provide the query to perform action
		int result = statement.executeUpdate("delete from student_info where regno=6");
		
		if(result==1) {
			System.out.println("1 row deleted");
		}else {
			System.out.println("Row not deleted");
		}
		connection.close();
	}

}
