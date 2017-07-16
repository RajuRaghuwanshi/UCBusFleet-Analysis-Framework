package org.iiitb.bmtc.service.dataServices;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.iiitb.bmtc.database.csv.CSVFileConnection;
import org.iiitb.bmtc.exception.ConnectionException;
import org.iiitb.bmtc.modal.JString;

public class BusRequestService {

	public BusRequestService() {
	}

	public List<JString> getAllRoute() {

		Connection conn = CSVFileConnection.getInstance().Connect();
		List<JString> list = new ArrayList<JString>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String path = getFilePath("bus_request.csv");
			String query = "Select RouteNumber, count(*) as occurance from " + path + " where TypeOfRequest='Increase Frequency' and RouteNumber != 'no existing match' group by RouteNumber order by occurance desc";
			ResultSet rset = stmt.executeQuery(query);
			while (rset.next()) {
				// System.out.println(rset.getString(1)+"---"+rset.getString(2));
				list.add(new JString(rset.getString(1)));
			}
		} catch (SQLException e) {
			throw new ConnectionException(e.getMessage());
		}

		return list;

	}

	public List<JString> getDeviceID(String routeNo) {
		//CSVFileConnection brconn = new CSVFileConnection();
		Connection conn = CSVFileConnection.getInstance().Connect();
		List<JString> deviceId = new ArrayList<>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String path = getFilePath("route_no_device_id.csv");
			ResultSet rset = stmt
					.executeQuery("Select DEVICE_ID from "+ path +" where Route_No ='" + routeNo + "'");
			while (rset.next()) {
				deviceId.add(new JString(rset.getString(1)));
			}
		} catch (SQLException e) {
			//brconn.releaseConnection();
			throw new ConnectionException(e.getMessage());
		}
		return deviceId;
	}
	
	//To get the path of file dynamically
	public String getFilePath(String fileName)
	{
		//Testing - Start
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		//System.out.println("Hey ...." + file.getAbsolutePath());
		String path = file.getAbsolutePath();
		path = path.substring(0, path.length()-4);
		System.out.println("Byy........" + path);
		return path;
		//Testing - End
	}
	
	/*public static void main(String[] args) {
		BusRequestService brs = new BusRequestService();
		System.out.println(brs.getDeviceID("500"));
	}*/
}
