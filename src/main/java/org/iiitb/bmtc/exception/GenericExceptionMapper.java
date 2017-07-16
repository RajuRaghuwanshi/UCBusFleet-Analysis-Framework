package org.iiitb.bmtc.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.iiitb.bmtc.modal.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {


	@Override
	public Response toResponse(Throwable exp) {
		ErrorMessage error = new ErrorMessage("Oops, URL not exist", 404);
		
		System.out.println("Generic Exception !");
		
		return Response.status(404).entity(error).type(MediaType.APPLICATION_JSON).build();
		
	}
}
