package org.iiitb.hospital.nimhans.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iiitb.hospital.nimhans.modals.Hospital;
import org.iiitb.hospital.nimhans.modals.Login;
import org.iiitb.hospital.nimhans.services.DoctorService;
import org.iiitb.hospital.nimhans.services.HospitalService;

@Path("/")
public class DoctorResource {

	private DoctorService doctorService = new DoctorService();
	private HospitalService hospitalService = new HospitalService();

	@Path("/validate")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response validateUserName(@QueryParam("user") String userName) {
		
		int result = doctorService.validateUserName(userName);
		if (result == 0) {
			return Response.noContent().build();
		} else {
			return Response.ok().entity(result).build();
		}
		
	}

	@Path("/doctors")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response doctorLogin(Login login) {
		
		System.out.println(login.getEmailID()+"  "+login.getPassword());
		
		Hospital hospital = hospitalService.getHospitalDoctorinfo(login.getEmailID(),login.getPassword());
		
		System.out.println(hospital);
		
		if (hospital == null) {
			return Response.noContent().build();
		} else {
			return Response.ok().entity(hospital).build();
		}
	}
}
