package org.iiitb.bmtc.database.csv;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.iiitb.bmtc.exception.ConnectionException;

public class CSVFileConnection {

	private static Connection conn;

	public CSVFileConnection() {
		Properties prop = new Properties();
		try {

			String propFileName = "config.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			prop.load(inputStream);
			String driverName = prop.getProperty("CSVDriverName");
			String csvconn = prop.getProperty("CSVConnection");
			Class.forName(driverName);
			conn = DriverManager.getConnection(csvconn);
			System.out.println(prop.getProperty("CSVSuccessMessage"));
		} catch (Exception e) {
			System.out.println("Exception found" + e);
			closeConnection();
			throw new ConnectionException(e.getMessage());
		}
	}

	private static class DAOHelper {
		private static final CSVFileConnection csvobject_INSTANCE = new CSVFileConnection();
	}
	
	public static CSVFileConnection getInstance() {
		return DAOHelper.csvobject_INSTANCE;
	}
	
	public Connection Connect() {
		
		return CSVFileConnection.conn;
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
		CSVFileConnection csv = new CSVFileConnection();
	}*/
}


