package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class GetDataFromDataBaseTest {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub

		Driver driver = new Driver();

		DriverManager.registerDriver(driver);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");

		Statement statement = connection.createStatement();

		ResultSet result = statement.executeQuery("select * from student_info where firstname='veer'");
	
		while (result.next()) {
			System.out.println(result.getString(1)+"\t"+result.getString(2)+"\t"+result.getString(3));
		}

		connection.close();

	}

}
