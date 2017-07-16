package org.iiitb.bmtc.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.iiitb.bmtc.modal.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exp) {

		System.out.println("Data not found Exception!");
		ErrorMessage error = new ErrorMessage(exp.getMessage()+"Exception", 404);

		return Response.status(404).entity(error).type(MediaType.APPLICATION_JSON).build();
	}

}
