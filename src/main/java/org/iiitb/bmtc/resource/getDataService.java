package org.iiitb.bmtc.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iiitb.bmtc.database.owl.getData;

@Path("/call")
public class getDataService 
{

	@GET
	@Path("/get/{classname}")
	@Produces(MediaType.TEXT_PLAIN)
	
	public String getInstances(@PathParam ("classname") String classname)
	
	{
		System.out.println(classname);
		getData object = new getData();
		List<String> arr=null;
		if(classname.equals("Route")==true)
			arr = object.getInstanceList(classname);
		else
			arr=object.getRouteList(classname);
			
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<arr.size()-1; i++){
			sb.append(arr.get(i)+",");
		}
		sb.append(arr.get(arr.size()-1));
		
		System.out.println(arr.get(0));

		return sb.toString();		
	}
	
	
	
}
