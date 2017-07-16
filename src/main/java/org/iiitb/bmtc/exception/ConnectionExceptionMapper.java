package org.iiitb.bmtc.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.iiitb.bmtc.modal.ErrorMessage;

@Provider
public class ConnectionExceptionMapper implements ExceptionMapper<ConnectionException>{

	@Override
	public Response toResponse(ConnectionException exception) {

		System.out.println("DataBaseConnection Exception!");
		ErrorMessage error = new ErrorMessage(exception.getMessage()+"Exception", 503);

		return Response.status(503).entity(error).type(MediaType.APPLICATION_JSON).build();
	}
	
}
