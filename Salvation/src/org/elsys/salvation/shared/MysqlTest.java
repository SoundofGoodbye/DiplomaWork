package org.elsys.salvation.shared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MysqlTest
{
	public static void main(String[] args)
	{
		MysqlTest test = new MysqlTest();

		try
		{
			Connection connection = test.getConnection();
			test.viewTable(connection);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	private Connection getConnection() throws SQLException
	{
		System.out.println("Connecting to database.");

		Properties properties = new Properties();
		properties.put("user", "root");
		properties.put("password", "root");
		Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myfirstdb", properties);

		System.out.println("Connected to database");

		return connection;
	}

	private void viewTable(Connection con) throws SQLException
	{
		Statement stmt = null;

		String query = "SELECT SuplierName,Phone,Email FROM table_name";
		try
		{
			stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery(query);

			while (resultSet.next())
			{
				String suplierName = resultSet.getString("SuplierName");
				int phone = resultSet.getInt("Phone");
				String email = resultSet.getString("Email");
				System.out.println("SuplierName: " + suplierName + " Phone: " + phone + " Email: " + email);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (stmt != null)
			{
				stmt.close();
			}
		}
	}
}
