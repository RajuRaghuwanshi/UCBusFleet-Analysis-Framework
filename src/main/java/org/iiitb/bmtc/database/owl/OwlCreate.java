package org.iiitb.bmtc.database.owl;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.jena.atlas.iterator.Iter;
import org.apache.jena.datatypes.xsd.impl.XSDFloat;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntDocumentManager;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.ontology.impl.OntologyImpl;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.XSD;
import org.iiitb.bmtc.modal.BusStop;
//import com.opencsv.CSVReader;

public class OwlCreate
{
    static String ns= "http://www.semanticweb.org/owl/ontologies/bmtc/#";        
    public static void main (String args[]) throws IOException 
    {
        create_ontology();
    }
    static void create_ontology() throws IOException
    {
        OntModel M = ModelFactory.createOntologyModel();
        /*OntClass Stop = M.createClass( ns + "Stop");
        OntClass Origin = M.createClass( ns + "Origin");
        OntClass Destination = M.createClass( ns + "Destination");*/
        OntClass Route = M.createClass( ns + "Route");
        OntClass IntermediateStop = M.createClass( ns + "IntermediateStop");
        ObjectProperty hasIntermediateStop = M.createObjectProperty(ns+"hasIntermediateStop");
        DatatypeProperty hasLatitude = M.createDatatypeProperty(ns+"hasLatitude");
        DatatypeProperty hasLongitude = M.createDatatypeProperty(ns+"hasLongitude");
        DatatypeProperty hasOrigin = M.createDatatypeProperty(ns+"hasOrigin");
        DatatypeProperty hasDestination = M.createDatatypeProperty(ns+"hasDestination");
        hasIntermediateStop.setDomain(Route);
        hasIntermediateStop.setRange(IntermediateStop);
        hasLatitude.setDomain(IntermediateStop);
        hasLatitude.setRange(XSD.xstring);
        hasLongitude.setDomain(IntermediateStop);
        hasLongitude.setRange(XSD.xstring);
        hasOrigin.setDomain(Route);
        hasDestination.setRange(XSD.xstring);
        hasOrigin.setDomain(Route);
        hasDestination.setRange(XSD.xstring);
        Client client = ClientBuilder.newClient();
        //CSVReader reader = new CSVReader(new FileReader("file.csv"));
        WebTarget webTarget = client.target("http://localhost:8080/bmtc/api/requestedroutes");
        Response response = webTarget.request(MediaType.APPLICATION_JSON).get(Response.class);
        List<String> route = response.readEntity(new GenericType<List<String>>() {});
        //System.out.println("output size of routes="+route.size());     
        for(int i=0;i<route.size();i++)
        {
        	WebTarget webTarget1 = client.target("http://localhost:8080/bmtc/api/scheduledroutes/"+route.get(i)+"/busstops");
            Response response1 = webTarget1.request(MediaType.APPLICATION_JSON).get(Response.class);
            List<BusStop> list = null;
            String route_no=route.get(i);
            Individual r=Route.createIndividual(ns+route_no);
            if(response1.getStatus()!=404)
            {
               //System.out.println("has intermediate stops="+list);
               list = response1.readEntity(new GenericType<List<BusStop>>() {});
               for(int j=0;j<list.size();j++)
               {
            	   BusStop stop= list.get(j);
            	   String inter=stop.getBusStopName();
            	   Individual intermediate=IntermediateStop.createIndividual(ns+inter);
            	   String latitude=stop.getLatitude();
            	   String longitude=stop.getLongitude();
            	   String origin = list.get(0).getSourceToDestination();                   
                   String destination = list.get(0).getDestinationToSource();
                   intermediate.addProperty(hasLatitude, latitude);
                   intermediate.addProperty(hasLongitude, longitude);
                   r.addProperty(hasIntermediateStop, intermediate);
                   r.addProperty(hasOrigin, origin);
                   r.addProperty(hasDestination, destination);
               }               
            }
        }
        
        
        //Iterator<String> itr = list.iterator();
        //System.out.println(list.toString());
        /*if(list.contains(routeNo))
        rslt = true;
        return rslt;
        String [] nextLine;
        nextLine = reader.readNext();
        int count=0;
        while ((nextLine = reader.readNext()) != null && count<20) 
        {
            String route_no=nextLine[0];
            String distance= nextLine[1];
            count++;
            String s;
            s=nextLine[2];
            s=s.replace(" ","_");
            Individual origin=Origin.createIndividual(ns+s);
            s=nextLine[3];
            s=s.replace(" ","_");
            Individual destination=Destination.createIndividual(ns+s);
            Individual r=Route.createIndividual(ns+route_no);
            r.addProperty(hasDistance, distance);
            r.addProperty(hasOrigin, origin);
            r.addProperty(hasDestination, destination);
        }*/
        M.write( System.out, "N3" );
        FileWriter out = null;
        try {
          out = new FileWriter( "bmtc.owl" );
          M.write( out, "RDF/XML" );
        }
        finally {
          if (out != null) {
            try {out.close();} catch (IOException ignore) {}
          }
        }
    }       
}
