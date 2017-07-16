package org.iiitb.bmtc.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iiitb.bmtc.exception.DataNotFoundException;
import org.iiitb.bmtc.modal.GPS_Data;
import org.iiitb.bmtc.service.dataServices.GPSDeviceServices;

@Path("/deviceId")
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class GPSDeviceResource {

	// create the instance to access the functionality of Data services
	GPSDeviceServices GPSServices = new GPSDeviceServices();

	/*@GET
	@Path("/{deviceID}")
	public Response getGPSdata_deviceno(@PathParam("deviceID") String device_no, @QueryParam("date") String date) {

		
		List<GPS_Data> listOfDeviceData = new ArrayList<>();
		
		if (date == null) {
			listOfDeviceData = GPSServices.getDeviceData(device_no);
		} else {
			listOfDeviceData = GPSServices.getDeviceData(device_no, date);
		}

		if (listOfDeviceData.isEmpty()) {
			throw new DataNotFoundException("Device Id : "+device_no+" NOT FOUND !");
		} else {
			return Response.ok().entity(new GenericEntity<List<GPS_Data>>(listOfDeviceData) {
			}).build();
		}
	}
	*/
	@GET
	@Path("/{deviceID}")
	public Response getGPSdata_deviceno(@PathParam("deviceID") String device_no, @QueryParam("date") String date,@QueryParam("lat") String latitude,@QueryParam("lon") String longitude) {

		
		List<GPS_Data> listOfDeviceData = new ArrayList<>();
		
		if (date == null) {
			listOfDeviceData = GPSServices.getDeviceData(device_no);
		} else {
			listOfDeviceData = GPSServices.getDeviceData(device_no, date);
		}

		if (listOfDeviceData.isEmpty()) {
			throw new DataNotFoundException("Device Id : "+device_no+" NOT FOUND !");
		} else {
			return Response.ok().entity(new GenericEntity<List<GPS_Data>>(listOfDeviceData) {
			}).build();
		}
	}
	
}
