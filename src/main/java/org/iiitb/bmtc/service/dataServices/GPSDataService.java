package org.iiitb.bmtc.service.dataServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.iiitb.bmtc.database.sql.DataAccessObject;
import org.iiitb.bmtc.modal.GPSData;

public class GPSDataService {
	public ArrayList<GPSData> getGPSDataForBusStop(String RouteNo, String direction, String date,
			String busStoplatitude, String busStoplongitude, String busStopName, int busStopNumber) {

		Connection connection = DataAccessObject.getInstance().Connect();

		ArrayList<GPSData> listOfGPSdata = new ArrayList<>();

		PreparedStatement preparestatement;

		String sql = "SELECT DeviceId,latitude,longitude,time,direction,"
				+ "(7912 * ASIN(SQRT( POWER(SIN((? - abs(latitude)) * pi()/180 / 2), 2) + COS(? * pi()/180 ) * COS(abs(latitude) * pi()/180) * POWER(SIN((?-LONGITUDE) * pi()/180 / 2), 2) )))"
				+ "  as distance FROM " + RouteNo + " WHERE  direction = ? and date = ? having  distance < 0.0534";

		try {

			preparestatement = connection.prepareStatement(sql);

			preparestatement.setObject(1, Double.parseDouble(busStoplatitude));

			preparestatement.setObject(2, Double.parseDouble(busStoplatitude));

			preparestatement.setObject(3, Double.parseDouble(busStoplongitude));

			preparestatement.setObject(4, direction);

			preparestatement.setObject(5, date);

			ResultSet rs = preparestatement.executeQuery();

			GPSData oldObject = null;

			while (rs.next()) {

				GPSData newObj = new GPSData(rs.getInt("deviceId"), rs.getString("latitude"), rs.getString("longitude"),
						rs.getString("direction"), rs.getDouble("distance"), rs.getString("time"), date, busStopName,
						busStopNumber);

				if (!newObj.equals(oldObject)) {
					listOfGPSdata.add(newObj);
				}

				oldObject = newObj;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		BunchingService service = new BunchingService();

		return service.findbunchingOfGivenData(listOfGPSdata);
	}

	public ArrayList<GPSData> getOneWeekGPSDataForBusStop(String RouteNo, String direction, String date,
			String busStoplatitude, String busStoplongitude, String busStopName, int busStopNumber) {

		ArrayList<GPSData> list = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");

		Calendar cal = Calendar.getInstance();

		for (int i = 1; i <= 7; i++) {

			list.addAll(
					getGPSDataForBusStop(RouteNo, direction, date, busStoplatitude, busStoplongitude, busStopName, i));

			try {
				Date d = sdf.parse(date);
				cal.setTime(d);
				cal.add(Calendar.DATE, 1);
				d = cal.getTime();
				date = sdf.format(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println("list Size :" + list.size());
			System.out.println("next Date :" + date);

		}

		return list;

	}

}
