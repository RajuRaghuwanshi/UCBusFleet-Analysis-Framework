package org.iiitb.bmtc.service.businessServices;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iiitb.bmtc.exception.DataNotFoundException;
import org.iiitb.bmtc.modal.GPS_Data;
import org.iiitb.bmtc.modal.Schedule;

public class BusFrequencyService {
	Client client = ClientBuilder.newClient();
	Properties prop = new Properties();
	public BusFrequencyService() {
		try 
		{
			String propFileName = "config.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			prop.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//to check whether the route number provided is valid or not
	public boolean isRouteNo(String routeNo)
	{
		boolean rslt = false;
		WebTarget webTarget = client.target(prop.getProperty("GetAllRoutes"));
		Response response = webTarget.request(MediaType.APPLICATION_JSON).get(Response.class);
		List<String> list = response.readEntity(new GenericType<List<String>>() {});
		//System.out.println(list.toString());
		if(list.contains(routeNo))
			rslt = true;
		return rslt;
	}
	
	public Schedule getSchedule(String routeNo)
	{
		//http://localhost:8080/bmtc/scheduledroutes/
		WebTarget webTarget = client.target(MessageFormat.format(prop.getProperty("GetRouteSchedule"),routeNo));
		Response response = webTarget.request(MediaType.APPLICATION_JSON).get(Response.class);
		Schedule schedule = response.readEntity(Schedule.class);
		System.out.println(schedule);
		return schedule;
	}
	
	public boolean isBusFrequencyHigh(String routeNo)
	{
		boolean rslt = false;
		if(isRouteNo(routeNo))
		{
			Schedule schedule = getSchedule(routeNo);
			String dept_from_origin = schedule.getSchedule_departure_from_origin();
			String dept_from_destination = schedule.getScheduled_departure_from_destination();
			
			String dept_time1[] = dept_from_origin.split("[ ]*,[ ]*");
			String dept_time2[] = dept_from_destination.split("[ ]*,[ ]*");
			int len1 = dept_time1.length;
			int len2 = dept_time2.length;
			/*for(int i = 0 ; i < len ; i++)
				System.out.print(dept_time[i]+ "---");*/
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
			LocalTime t21 = LocalTime.parse(dept_time1[0],fmt);
			LocalTime t11 = LocalTime.parse(dept_time1[len1-1],fmt);
			int minutes1 = (int)ChronoUnit.MINUTES.between(t21, t11);
			
			LocalTime t22 = LocalTime.parse(dept_time2[0],fmt);
			LocalTime t12 = LocalTime.parse(dept_time2[len2-1],fmt);
			int minutes2 = (int)ChronoUnit.MINUTES.between(t12, t22);
			
			//System.out.println("Difference : " + minutes);
			
			int interval = (minutes1/len1 + minutes2/len2)/2;
			if(interval < 60)
				rslt = true;
		}
		else
		{
			throw new DataNotFoundException("route number :"+routeNo+" not exist !");
		}
		return rslt;
	}
	
	public Map<String,List<GPS_Data>> getGPSTraces(String routeNo)
	{
		//first get the deviceId of buses for particular route
		WebTarget webTarget;
		webTarget = client.target(MessageFormat.format(prop.getProperty("GetDeviceId"),routeNo));
		Response response ;
		response = webTarget.request(MediaType.APPLICATION_JSON).get(Response.class);
		List<String> deviceIds = response.readEntity(new GenericType<List<String>>() {});
		System.out.println(deviceIds);
		
		Map<String,List<GPS_Data>> deviceGPSData = new HashMap<String,List<GPS_Data>>();
		
		
		for(String deviceId : deviceIds)
		{	
			String url = MessageFormat.format(prop.getProperty("GetGPSDataByDeviceId"),deviceId);
			System.out.println(url);
			webTarget = client.target(url);
			
			List<GPS_Data> gpsData =  webTarget.request(MediaType.APPLICATION_JSON).get(new GenericType<List<GPS_Data>>() {});
			//List<GPS_Data> gpsData = response.readEntity(new GenericType<List<GPS_Data>>(){});
			System.out.println(gpsData);
			deviceGPSData.put(deviceId, gpsData);
		}
		
		return deviceGPSData;
		
		/*Set<String> deviceIDinMap = deviceGPSData.keySet();
		for(String deviceId: deviceIDinMap)
		{
			System.out.println(deviceId + deviceGPSData.get(deviceId));
		}*/
	}
	
	public static void main(String[] args) {
		BusFrequencyService bf = new BusFrequencyService();
		System.out.println(bf.isRouteNo("K-4"));
		//bf.getSchedule("600");
		//bf.checkSchedule("600");
		//bf.getGPSTraces("500");
		System.out.println(bf.isBusFrequencyHigh("K-4"));
	}
}
