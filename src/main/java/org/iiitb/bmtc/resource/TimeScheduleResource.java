package org.iiitb.bmtc.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.iiitb.bmtc.exception.NoContentException;
import org.iiitb.bmtc.modal.JString;
import org.iiitb.bmtc.service.dataServices.ScheduleService;

@Path("/")
public class TimeScheduleResource {
	ScheduleService sservice = new ScheduleService();

	@GET
	@Path("/departure-from-origintiming")
	public Response getScheduledDeparture_Org(@PathParam("routeNo") String routeNo) {
		System.out.println("departure-from-origin");

		List<JString> listOfdeptTime = sservice.getScheduledDeparture_Org(routeNo);

		if (listOfdeptTime.isEmpty()) {
			throw new NoContentException("data not available Departure From Origin :"+routeNo);
		} else {
			return Response.ok().entity(new GenericEntity<List<JString>>(listOfdeptTime) {
			}).build();
		}

	}

	@GET
	@Path("/arrival-at-destinationtiming")
	public Response getScheduledArrival_Dest(@PathParam("routeNo") String routeNo) {
		System.out.println("arrival-at-destination");

		List<JString> listOfarrtTime = sservice.getScheduledArrival_Dest(routeNo);

		if (listOfarrtTime.isEmpty()) {
			throw new NoContentException("data not available Arrival at Destination :"+routeNo);
		} else {

			return Response.ok().entity(new GenericEntity<List<JString>>(listOfarrtTime) {
			}).build();
		}

	}

	@GET
	@Path("/departure-from-destinationtiming")
	public Response getScheduledDeparture_Dest(@PathParam("routeNo") String routeNo) {
		System.out.println("departure-from-destination");

		List<JString> listOfdeptTime = sservice.getScheduledDeparture_Dest(routeNo);
		if (listOfdeptTime.isEmpty()) {
			throw new NoContentException("data not available Departure From Destination :"+routeNo);
		} else {
			return Response.ok().entity(new GenericEntity<List<JString>>(listOfdeptTime) {
			}).build();
		}
	}

	@GET
	@Path("/arrival-at-origintiming")
	public Response getScheduledArrival_Org(@PathParam("routeNo") String routeNo) {
		System.out.println("arrival-at-origin");

		List<JString> listOfarrTime = sservice.getScheduledArrival_Org(routeNo);
		if (listOfarrTime.isEmpty()) {
			throw new NoContentException("data not available Arrival at origin :"+routeNo);
		} else {
			return Response.ok().entity(new GenericEntity<List<JString>>(listOfarrTime) {
			}).build();
		}
	}
}
