package org.iiitb.bmtc.database.sql;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.iiitb.bmtc.exception.ConnectionException;
//Data access object layer Singleton class
public class DataAccessObject {

	private static Connection conn;

	private DataAccessObject() {
		Properties prop = new Properties();
		try {
			String propFileName = "config.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			prop.load(inputStream);
			String userName = prop.getProperty("UserName");
			String password = prop.getProperty("Password");
			String url = prop.getProperty("URL");
			System.out.println("userName = " + userName + " password = " + password + " url = " + url);
			Class.forName(prop.getProperty("DriverName")).newInstance();
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println(prop.getProperty("SuccessMessage"));
		} catch (Exception e) {
			System.out.println("Exception found" + e);
			closeConnection();
			throw new ConnectionException(e.getMessage());
		}
	}

	private static class DAOHelper {
		private static final DataAccessObject dataobject_INSTANCE = new DataAccessObject();
	}

	public static DataAccessObject getInstance() {
		return DAOHelper.dataobject_INSTANCE;
	}

	public Connection Connect() {
			
		return DataAccessObject.conn;
	}

	public void closeConnection() {
		try {
			conn.close();
		} catch (Exception e) {
			System.out.println("Connection close error");
		}
	}
	
	//Testing Code
	/*public static void main(String arg[])
	{
		DataAccessObject dao = new DataAccessObject();
	}*/

}
