package org.iiitb.bmtc.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.iiitb.bmtc.modal.ErrorMessage;

@Provider
public class NoContentExceptionMapper implements ExceptionMapper<NoContentException>{

	@Override
	public Response toResponse(NoContentException exp) {
		
		ErrorMessage error = new ErrorMessage(exp.getMessage()+"Exception",204);
		
		System.out.println("No Content Exception !");
		
		return Response.status(204).entity(error).type(MediaType.APPLICATION_JSON).build();
	}
}
