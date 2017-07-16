package org.iiitb.bmtc.service.dataServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.bmtc.database.sql.DataAccessObject;
import org.iiitb.bmtc.exception.ConnectionException;
import org.iiitb.bmtc.modal.GPS_Data;

public class GPSDeviceServices {

	List<GPS_Data> listOfGPSdata = new ArrayList<>();

	public List<GPS_Data> getDeviceData(String device_no) {

		// Establish a database connection (singleton class)
		Connection connection = DataAccessObject.getInstance().Connect();

		PreparedStatement preparestatement;

		String sql = "SELECT * from tbl_name WHERE device_id = ?";

		try {

			preparestatement = connection.prepareStatement(sql);

			preparestatement.setString(1, device_no);

			// System.out.println(preparestatement.toString());
			ResultSet rs = preparestatement.executeQuery();

			while (rs.next()) {

				GPS_Data newObj = new GPS_Data(rs.getString(1), rs.getDouble(3), rs.getDouble(4), rs.getString(5),
						rs.getDouble(6), rs.getDate(7));

				listOfGPSdata.add(newObj);
			}

		} catch (SQLException e) {
			//e.printStackTrace();
			throw new ConnectionException(e.getMessage());
		}

		// System.out.println(listOfGPSdata.size());

		return listOfGPSdata;

	}

	public List<GPS_Data> getDeviceData(String device_no, String date) {

		// Establish a database connection (singleton class)
		Connection connection = DataAccessObject.getInstance().Connect();

		PreparedStatement preparestatement;

		String sql = "SELECT * from tbl_name WHERE  device_id = ? and ist_date LIKE ?";
		try {
			preparestatement = connection.prepareStatement(sql);
			preparestatement.setString(1, device_no);
			preparestatement.setString(2, date + "%");
			ResultSet rs = preparestatement.executeQuery();

			while (rs.next()) {
				GPS_Data newObj = new GPS_Data(rs.getString(1), rs.getDouble(3), rs.getDouble(4), rs.getString(5),
						rs.getDouble(6), rs.getDate(7));

				listOfGPSdata.add(newObj);
			}
		} catch (SQLException e) {
			throw new ConnectionException(e.getMessage());
		}
		
		return listOfGPSdata;
	}
	
	
	
	//test
	public List<GPS_Data> getDeviceData(String device_no,String date,String lat,String lon) {

		// Establish a database connection (singleton class)
		Connection connection = DataAccessObject.getInstance().Connect();

		PreparedStatement preparestatement;

		String sql = "SELECT * from tbl_name WHERE device_id = ?";

		try {

			preparestatement = connection.prepareStatement(sql);

			preparestatement.setString(1, device_no);

			// System.out.println(preparestatement.toString());
			ResultSet rs = preparestatement.executeQuery();

			while (rs.next()) {

				GPS_Data newObj = new GPS_Data(rs.getString(1), rs.getDouble(3), rs.getDouble(4), rs.getString(5),
						rs.getDouble(6), rs.getDate(7));

				listOfGPSdata.add(newObj);
			}

		} catch (SQLException e) {
			//e.printStackTrace();
			throw new ConnectionException(e.getMessage());
		}

		// System.out.println(listOfGPSdata.size());

		return listOfGPSdata;

	}

}
