package org.iiitb.hospital.nimhans.resources;

import javax.ws.rs.Path;

@Path("/hospitals")
public class HospitalResource {

	@Path("/{hospital_id}")
	public ReportResource getHospitalReport() {
		return new ReportResource();
	}

}