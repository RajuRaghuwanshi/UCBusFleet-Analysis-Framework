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
import org.iiitb.bmtc.exception.DataNotFoundException;
import org.iiitb.bmtc.modal.BusStop;
import org.iiitb.bmtc.modal.JString;
import org.iiitb.bmtc.modal.Schedule;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScheduleService {

	public ScheduleService() {
	}

	public List<JString> getAllRoute() {
		// CSVFileConnection csvfconn = new CSVFileConnection();
		System.out.println("asjvcjvc");
		Connection conn = CSVFileConnection.getInstance().Connect();
		System.out.println("asjvcjvc");
		Statement stmt;
		List<JString> list = new ArrayList<>();
		try {
			String path = getFilePath("BMTCScheduledata.csv");
			stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("Select route_no from " + path );

			while (rset.next()) {
				list.add(new JString(rset.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//conn.closeConnection();
			throw new ConnectionException(e.getMessage());
		}

		return list;

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

	public Schedule getSchedule(String routeNo) {
		// CSVFileConnection csvfconn = new CSVFileConnection();
		Schedule sch = null;
		try {
			Connection conn = CSVFileConnection.getInstance().Connect();
			Statement stmt = conn.createStatement();
			String path = getFilePath("BMTCScheduledata.csv");
			ResultSet rset = stmt.executeQuery("Select * from " + path + " where route_no = '" + routeNo + "'");
			if(rset.next()) {
				sch = new Schedule();
				sch.setRouteNo(rset.getString("route_no"));
				sch.setDistance(rset.getString("distance"));
				sch.setOrigin(rset.getString("origin"));
				sch.setDestination(rset.getString("destination"));
				sch.setMap_json(rset.getString("map_json_content"));
				sch.setSchedule_departure_from_origin(rset.getString("scheduled_departure_from_origin"));
				sch.setScheduled_arrival_at_destination(rset.getString("scheduled_arrival_at_destination"));
				sch.setScheduled_departure_from_destination(rset.getString("scheduled_departure_from_destination"));
				sch.setScheduled_arrival_at_origin(rset.getString("scheduled_arrival_at_origin"));
			}
		} catch (Exception e) {
			throw new ConnectionException(e.getMessage());
		}
		return sch;
	}

	public List<BusStop> getIntermediateBusStop(String routeNo){
		Schedule sch = getSchedule(routeNo);
		String jsonObj = sch.getMap_json();
		JSONParser parser = new JSONParser();
		List<BusStop> busStopDetailsList = new ArrayList<>();
		JSONArray arr;
		try 
		{
			arr = (JSONArray) parser.parse(jsonObj);
			for(Object o: arr)
			{
				JSONObject obj = (JSONObject) o;
				BusStop busStopdetail = new BusStop();
				busStopdetail.setBusStopName((String)obj.get("busstop"));
				JSONArray latlonArr = (JSONArray) obj.get("latlons");
				busStopdetail.setLatitude((String)latlonArr.get(0));
				busStopdetail.setLongitude((String)latlonArr.get(1));
				busStopDetailsList.add(busStopdetail);
				//System.out.println(busStopdetail);
			}
			
			busStopDetailsList.get(0).setSourceToDestination("nagarabhavi bda complex nagarabhavi 2nd stage beside bda complex nagarabhavi");
			busStopDetailsList.get(0).setDestinationToSource("cv raman nagara");			
			System.out.println(busStopDetailsList.get(0));
		}
		catch (ParseException e) 
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return busStopDetailsList;
			
	}
	public String getOrigin(String routeNo) {

		Schedule schedule = getSchedule(routeNo);
		if (schedule == null) {
			throw new DataNotFoundException("route number :"+routeNo+" not exist !");
		}

		String originName = schedule.getOrigin();
		return originName;
	}

	public String getDestination(String routeNo) {

		Schedule schedule = getSchedule(routeNo);
		if (schedule == null) {
			throw new DataNotFoundException("route number :"+routeNo+" not exist !");
		}

		String destinationName = schedule.getDestination();
		return destinationName;
	}

	// This method was used when we were using JSON data from csv files.
	/*
	 * public List<String> getIntermediateStops(String routeNo) { List<String>
	 * list = new ArrayList<>(); Schedule sch; try { ScheduleService sservice =
	 * new ScheduleService(); sch = sservice.getSchedule(routeNo); String
	 * mapJson = sch.getMap_json(); JSONParser jsonParser = new JSONParser();
	 * JSONArray jsonArray = (JSONArray)jsonParser.parse(mapJson); Iterator itr
	 * = jsonArray.iterator(); while(itr.hasNext()) { JSONObject jsonObject =
	 * (JSONObject)itr.next(); String stop = (String)jsonObject.get("busstop");
	 * list.add(stop); } //return sch.getDestination(); } catch (Exception e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); //return null; }
	 * return list; }
	 */

	public List<JString> getIntermediateStops(String routeNo) {
		List<JString> list = new ArrayList<>();
		try {
			Document doc = Jsoup
					.connect("http://www.mybmtc.com/route/search/" + routeNo + "/0?route_id&qt-home_quick_tab_bottom=0")
					.get();
			Document doc2 = null;
			Elements links = doc.select("a[href]");

			for (Element link : links) {

				if (link.text().equals("Bus Stops")) {
					doc2 = Jsoup.connect(link.attr("href")).get();
					// System.out.println("Hello.................");
					break;
				}
			}

			Elements liitems = doc2.select("li");
			for (Element link : liitems) {

				list.add(new JString(link.text()));
			}

		} catch (Exception e) {
			throw new ConnectionException(e.getMessage());
		}
		return list;
	}

	public List<JString> getScheduledDeparture_Org(String routeNo) {

		Schedule schedule = getSchedule(routeNo);

		if (schedule == null) {
			throw new DataNotFoundException("route number :"+routeNo+" not exist !");
		}

		String depTime[] = schedule.getSchedule_departure_from_origin().split(",");

		List<JString> list = new ArrayList<>();
		for (String s : depTime)
			list.add(new JString(s.trim()));

		return list;
	}

	public List<JString> getScheduledArrival_Dest(String routeNo) {

		Schedule schedule = getSchedule(routeNo);
		if (schedule == null) {
			throw new DataNotFoundException("route number :"+routeNo+" not exist !");
		}

		String arrTime[] = schedule.getScheduled_arrival_at_destination().split(",");

		List<JString> list = new ArrayList<>();
		for (String s : arrTime)
			list.add(new JString(s.trim()));

		return list;
	}

	public List<JString> getScheduledDeparture_Dest(String routeNo) {

		/*
		 * Schedule sch; try { ScheduleService sservice = new ScheduleService();
		 * sch = sservice.getSchedule(routeNo); String schDepOri =
		 * sch.getScheduled_departure_from_destination(); String depTime[] =
		 * schDepOri.split(","); for (int i = 0; i < depTime.length; i++)
		 * list.add(new JString(depTime[i]));
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		Schedule schedule = getSchedule(routeNo);
		if (schedule == null) {
			throw new DataNotFoundException("route number :"+routeNo+" not exist !");
		}

		String depTime[] = schedule.getScheduled_departure_from_destination().split(",");
		List<JString> list = new ArrayList<>();
		for (String s : depTime)
			list.add(new JString(s.trim()));

		return list;
	}

	public List<JString> getScheduledArrival_Org(String routeNo) {
		Schedule schedule = getSchedule(routeNo);
		if (schedule == null) {
			throw new DataNotFoundException("route number :"+routeNo+" not exist !");
		}

		String arrTime[] = schedule.getScheduled_arrival_at_origin().split(",");
		List<JString> list = new ArrayList<>();
		for (String s : arrTime)
			list.add(new JString(s.trim()));

		return list;

	}
	
	public static void main(String[] args) {
		ScheduleService ss = new ScheduleService();
		System.out.println(ss.getIntermediateBusStop("K-4"));
	}
}
