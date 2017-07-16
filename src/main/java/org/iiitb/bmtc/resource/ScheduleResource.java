package org.iiitb.bmtc.resource;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iiitb.bmtc.exception.DataNotFoundException;
import org.iiitb.bmtc.exception.NoContentException;
import org.iiitb.bmtc.modal.BusStop;
import org.iiitb.bmtc.modal.JString;
import org.iiitb.bmtc.modal.Schedule;
import org.iiitb.bmtc.service.dataServices.ScheduleService;

@Path("/scheduledroutes")
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class ScheduleResource {

	ScheduleService sservice = new ScheduleService();

	@GET
	public Response getAllRoute() {

		List<JString> listOfRoute = sservice.getAllRoute();

		if (listOfRoute.isEmpty()) {
			throw new NoContentException("NO route available !");
		} else {
			return Response.ok().entity(new GenericEntity<List<JString>>(listOfRoute) {
			}).build();
		}

	}

	//get route information
	@GET
	@Path("/{routeNo}")
	public Response getSchedule(@PathParam("routeNo") String routeNo) {
		
		Schedule schedule = sservice.getSchedule(routeNo);
		
		if(schedule == null){
			throw new DataNotFoundException("route number :"+routeNo+" not exist !");
		}else{
		return Response.ok().entity(schedule).build();
		}
		
	}

	//get inter-busStops
	@GET
	@Path("/{routeNo}/busstops")
	public Response getIntermediateStops(@PathParam("routeNo") String routeNo) {
		
			//List<JString> listOfinterStop = sservice.getIntermediateStops(routeNo);
		List<BusStop> listOfinterStop = sservice.getIntermediateBusStop(routeNo);
		
			if(listOfinterStop.isEmpty()){
				throw new NoContentException("No intermediate bus Stops for this route :"+routeNo);
			}else{
			
			return Response.ok().entity(new GenericEntity<List<BusStop>>(listOfinterStop) {
			}).build();
			}
			
		
	}

	// create a separate sub-resource for all services
	@Path("/{routeNo}")
	public TimeScheduleResource getScheduleTime() {

		return new TimeScheduleResource();
	}
}
