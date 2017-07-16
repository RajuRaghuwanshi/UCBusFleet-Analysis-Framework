package org.iiitb.bmtc.service.dataServices;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.iiitb.bmtc.database.sql.DataAccessObject;
import org.iiitb.bmtc.modal.BusStop;


public class BusStopServices {

	public BusStopServices() {
	}

	public List<BusStop> getBusStop(String routeNumber) {

		// same database connection used here
		Connection connection = DataAccessObject.getInstance().Connect();
		PreparedStatement preparestatement;

		List<BusStop> listOfbusStops = new ArrayList<>();

		String sql = "SELECT latitude, longitude, busstop from bmtcbusinfo where route_no Like ?";

		try {
			preparestatement = connection.prepareStatement(sql);

			preparestatement.setString(1, routeNumber + "%");

			System.out.println(preparestatement.toString());
			ResultSet rs = preparestatement.executeQuery();

			while (rs.next()) {

				BusStop newObj = new BusStop(rs.getString("latitude"), rs.getString("longitude"),
						rs.getString("busstop"), null, null);

				listOfbusStops.add(newObj);
			}

			String sourceStopName = listOfbusStops.get(0).getBusStopName();

			int size = listOfbusStops.size();

			String destinatonStopName = listOfbusStops.get(size - 1).getBusStopName();

			listOfbusStops.get(0).setSourceToDestination(sourceStopName);
			listOfbusStops.get(0).setDestinationToSource(destinatonStopName);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfbusStops;
	}
}