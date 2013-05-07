package org.elsys.salvation.server;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.SocketPermission;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import org.elsys.salvation.client.Defence;

public class ObjectSerializationToMysql
{
	static final String TABLE_NAME = "Salvation_Table";

	static final String CREATE_DATABASE = "CREATE DATABASE SalvationDB";

	static final String SQL_TO_WRITE_DEFENCE = "INSERT INTO " + TABLE_NAME
			+ " (DEFENCE_NAME, DEFENCE_VALUE) VALUES (?, ?)";

	static final String SQL_TO_READ_DEFENCE = "SELECT DEFENCE_VALUE FROM " + TABLE_NAME + "WHERE OBJECTID = ?";

	static String driver = "com.mysql.jdbc.Driver";

//	static String url = "jdbc:mysql://localhost/SalvationDB";
	static String url = "jdbc:mysql://127.0.0.1:3306/myfirstdb";

	static String username = "root";

	static String password = "root";

	/**
	 * This method will create and return a database connection.
	 */
	public static Connection getConnection() throws Exception
	{
//		Connection conn = DriverManager.getConnection(url, username, password);
		System.out.println("Connecting to database");
		Properties properties = new Properties();
		properties.put("user", "root");
		properties.put("password", "root");
		Connection conn = null;
		conn = DriverManager.getConnection(url, properties);
		if(conn == null)
		{
			System.out.println("Connection failed");
		}
		else
		{
			System.out.println("Connected");
		}
		return conn;
	}

	/**
	 * This method will write a java object to the database Parameters: connection object and object to be serialized
	 */
	public static long writeJavaObject(Connection conn, ArrayList<Defence> defencesList) throws Exception
	{
		String className = null;
		Defence defence = null;
		for (Defence currentDefence : defencesList)
		{
			className = currentDefence.getClass().getName();
			defence = currentDefence;
		}

		PreparedStatement preparedStatement = conn.prepareStatement(SQL_TO_WRITE_DEFENCE);
		preparedStatement.setString(1, className);
		preparedStatement.setObject(2, defence);
		preparedStatement.executeUpdate();
		ResultSet rs = preparedStatement.getGeneratedKeys();
		int id = -1;
		if (rs.next())
		{
			id = rs.getInt(1);
		}
		rs.close();
		preparedStatement.close();
		System.out.println("Serialization Successful." + "Serialized Class: " + className);
		return id;
	}

	/**
	 * This class will de-serialize a java object from the database
	 */
	public static Object readJavaObject(Connection conn, long id) throws Exception
	{
		PreparedStatement preparedStatement = conn.prepareStatement(SQL_TO_READ_DEFENCE);
		preparedStatement.setLong(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		byte[] buf = resultSet.getBytes("object_value");
		ObjectInputStream objectIn = null;
		if (buf != null)
		{
			objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
		}
		Object object = objectIn.readObject();
		String className = object.getClass().getName();
		resultSet.close();
		preparedStatement.close();
		System.out.println("Deserialization Successful." + "\n Deserialized Class: " + className);
		return object;
	}
}
