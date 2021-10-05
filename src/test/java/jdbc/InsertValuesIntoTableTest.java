package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class InsertValuesIntoTableTest {

	public static void main(String[] args) throws SQLException {

		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");
			Statement statement = connection.createStatement();
			int result = statement
					.executeUpdate("insert into student_info values('6', 'Mahendra', 'Baahubali','Maahishmati')");

			if (result == 1) {
				System.out.println("Values are added into the database");
			} else {
				System.out.println("Values are not added into the database");
			}

		} catch (Exception e) {

		} finally {

			connection.close();
		}

	}

}
