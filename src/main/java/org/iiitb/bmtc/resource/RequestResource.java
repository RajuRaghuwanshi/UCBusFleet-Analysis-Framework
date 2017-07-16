package org.iiitb.bmtc.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iiitb.bmtc.exception.NoContentException;
import org.iiitb.bmtc.modal.JString;
import org.iiitb.bmtc.service.dataServices.BusRequestService;

@Path("/requestedroutes")
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class RequestResource {

	BusRequestService service = new BusRequestService();

	@GET
	public Response getAllRoutes() {

		List<JString> listOfRoute = service.getAllRoute();

		if (listOfRoute.isEmpty()) {
			throw new NoContentException("NO route available !");
		} else {
			return Response.ok().entity(new GenericEntity<List<JString>>(listOfRoute) {
			}).build();
		}

	}

	@GET
	@Path("/{routeNo}/deviceId")
	public Response getdeviceID(@PathParam("routeNo") String routeNumber) {

	
		List<JString> listOfRoute = service.getDeviceID(routeNumber);

		if (listOfRoute.isEmpty()) {
			throw new NoContentException("Device not available for this route :"+routeNumber);
		} else {
			return Response.ok().entity(new GenericEntity<List<JString>>(listOfRoute) {
			}).build();
		}

	}

}
