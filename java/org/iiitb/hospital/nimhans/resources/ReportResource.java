package org.iiitb.hospital.nimhans.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iiitb.hospital.nimhans.modals.Hospital;
import org.iiitb.hospital.nimhans.modals.Report;
import org.iiitb.hospital.nimhans.services.ReportService;

@Path("/")
public class ReportResource {

	private ReportService reportService = new ReportService();

	/*
	 * @Path("/{hospital_id}/reports")
	 * 
	 * @GET
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response
	 * getReportData(@PathParam("hospital_id") int
	 * hospital_id, @QueryParam("type") String type) {
	 * 
	 * Report report = reportService.getReportInfo(hospital_id, type); if
	 * (report == null) { return Response.noContent().build(); } else { return
	 * Response.ok().entity(report).build(); }
	 * 
	 * }
	 */

	@Path("/reports")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response submitReportData(Report report) {

		Report reportObj = reportService.setReportInfo(report);

		if (report == null) {
			return Response.noContent().build();
		} else {
			return Response.ok().entity(reportObj).build();
		}
	}

	@Path("/reports")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatetReportData(Report report) {
		Report reportObj = reportService.setReportInfo(report);
		if (report != null) {
			return Response.noContent().build();
		} else {
			return Response.ok().entity(reportObj).build();
		}
	}

	@Path("/reports")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllHospital(@PathParam("hospital_id") int hospital_id, @QueryParam("type") String type,
			@QueryParam("self") String self) {
		
		System.out.println(type+"  "+self);
		
		if (self != null) {
			Report report = reportService.getReportInfo(hospital_id, type);
			//System.out.println(report);
			
			if (report == null) {
				return Response.noContent().build();
			} else {
				return Response.ok().entity(report).build();
			}
		} else {

			List<Hospital> listOfHospital = reportService.getReportStatus(hospital_id, type);
			if (listOfHospital.isEmpty()) {
				return Response.noContent().build();
			} else {
				return Response.ok().entity(new GenericEntity<List<Hospital>>(listOfHospital) {
				}).build();
			}
		}
	}
}
