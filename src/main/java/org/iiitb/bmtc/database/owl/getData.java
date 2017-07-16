package org.iiitb.bmtc.database.owl;


import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.iterator.ExtendedIterator;

//import com.github.andrewoma.dexx.collection.ArrayList;
//import com.github.andrewoma.dexx.collection.List;

import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ArrayList;

class busStop
{
	String latitude;
	String longitude;
	String busStopName;
}
public class getData 
{
static OntModel m= ModelFactory.createOntologyModel();
	
	static Map<String,ArrayList>classInstance=new HashMap<String,ArrayList>();
    static Map<String,ArrayList>instanceList=new HashMap<String,ArrayList>();
    static Map<String,ArrayList<busStop>>instanceInfo=new HashMap<String,ArrayList<busStop>>();
    //static Map<String ,>
	
    public static ArrayList<String> getRouteList(String route)
    {
    	m.read("bmtc.owl");
    	ExtendedIterator classes = m.listClasses();
    	ArrayList<String>arr=new ArrayList<String>();
    	
    	while(classes.hasNext())
    	{
    		 OntClass thisClass = (OntClass) classes.next();
		     if(thisClass.toString().equals(route))
		     {
		    	 
		     }
    	}
    	return arr;
    	
    }
	public static ArrayList<String> getPropertyList(String instanceName)
	{
		// TODO Auto-generated method stub
		         m.read("bmtc.owl");
				//m.write(System.out);
			    ExtendedIterator classes = m.listClasses();
			    
			    
			   
			    
			    while (classes.hasNext())
			    {

			      OntClass thisClass = (OntClass) classes.next();
			      System.out.println("Found class: " + thisClass.toString());
			      String cls=thisClass.toString();
			      //System.out.println(cls.substring(cls.lastIndexOf('#')+1));
			      cls=cls.substring(cls.lastIndexOf('#')+1);
			      
			      
			      ArrayList<String> instList=new ArrayList<String>();
			      ExtendedIterator instances = thisClass.listInstances();
			      if(instances.hasNext())
			      {
			    	 System.out.println("Following are the instance of this class:");
			    	  
			    	  while (instances.hasNext())
				      {

				          Individual thisInstance = (Individual) instances.next();
				    	 System.out.println("  Found instance: " + thisInstance.toString());
				    	  String inst=thisInstance.toString();
				    	  //System.out.println(inst.substring(inst.lastIndexOf('#')+1));
				    	  inst=inst.substring(inst.lastIndexOf('#')+1);
				    	  instList.add(inst);
				    	  
				        StmtIterator pr=thisInstance.listProperties();
				        ArrayList<String>propList=new ArrayList<String>();
				       
				        while(pr.hasNext())
				        {
				        	Statement s=pr.next();
				        	System.out.println(" Found property: " + s.toString());
				        	String prop=s.toString();
				        	String first="";
				        	String second="";
				        	if(prop.indexOf("#has", 0)!=-1)
				        	{
				        	 prop=prop.substring(prop.indexOf("#has", 0));
				        	 prop=prop.substring(0,prop.indexOf(','));
				        	 //int lenFirst=first.length();
				        	 //if(prop.charAt(lenFirst+1))
				        	 
				        	 propList.add(prop);
				        	}
				        	
				        	
				        }
				        
				        instanceList.put(inst, propList);
				        //instance end
				        

				      }
			    	  
			    	  classInstance.put(cls,instList);
			      }

			      

			    }//end of main while
			    
			    for(Entry<String,ArrayList> entry : classInstance.entrySet())
			    {
			    	if(entry.getValue().size()==0)
			    	{
			    		ArrayList<String>li1=new ArrayList<String>();
			    		li1.add("NA");
			    		classInstance.put(entry.getKey(), li1);
			    	}
			    }
			    //print the key value for class and instance
			    
			    for(Entry<String,ArrayList> entry : instanceList.entrySet())
			    {
			    	if(entry.getValue().size()==0)
			    	{
			    		ArrayList<String>li2=new ArrayList<String>();
			    		li2.add("NA");
			    		instanceList.put(entry.getKey(), li2);
			    	}
			    }
			    
			  
			      
		return instanceList.get(instanceName);
	}
	public static void main(String[]args)
	{
		getInstanceList("");
	}
	public static ArrayList<String> getInstanceList(String className) 
	{
		System.out.println("read owl1");
		m.read("bmtc.owl");
		System.out.println("read owl");
		   
	    ExtendedIterator classes = m.listClasses();
	    
	   
	    
	    while (classes.hasNext())
	    {

	      OntClass thisClass = (OntClass) classes.next();
	     System.out.println("Found class: " + thisClass.toString());
	      String cls=thisClass.toString();
	      //System.out.println(cls.substring(cls.lastIndexOf('#')+1));
	      cls=cls.substring(cls.lastIndexOf('#')+1);
	      
	      
	      ArrayList<String> instList=new ArrayList<String>();
	      ExtendedIterator instances = thisClass.listInstances();
	      if(instances.hasNext())
	      {
	    	  System.out.println("Following are the instance of this class:");
	    	  //int cnt=0;
	    	  while (instances.hasNext())
		      {

		          Individual thisInstance = (Individual) instances.next();
		    	  System.out.println("  Found instance: " + thisInstance.toString());
		    	  String inst=thisInstance.toString();
		    	  //System.out.println(inst.substring(inst.lastIndexOf('#')+1));
		    	  inst=inst.substring(inst.lastIndexOf('#')+1);
		    	  instList.add(inst);
		    	  
		        StmtIterator pr=thisInstance.listProperties();
		        ArrayList<String>propList=new ArrayList<String>();
		        
		        while(pr.hasNext())
		        {
		        	Statement s=pr.next();
		        	System.out.println(" Found property: " + s.toString());
		        	String prop=s.toString();
		        	if(prop.indexOf("#has", 0)!=-1)
		        	{
		        		
		        	 prop=prop.substring(prop.indexOf("#has", 0));
		        	 prop=prop.substring(0,prop.indexOf(','));
		        	 propList.add(prop);
		        	}
		        	
		        	
		        }
		        
		        instanceList.put(inst, propList);
		        //instance end
		        
		        //cnt++;
		        //if(cnt>20)
		        	//break;
		      }
	    	  
	    	  classInstance.put(cls,instList);
	      }

	      

	    }//end of main while
	    
	    for(Entry<String,ArrayList> entry : classInstance.entrySet())
	    {
	    	if(entry.getValue().size()==0)
	    	{
	    		ArrayList<String>li1=new ArrayList<String>();
	    		li1.add("NA");
	    		classInstance.put(entry.getKey(), li1);
	    	}
	    }
	    //print the key value for class and instance
	    
	    for(Entry<String,ArrayList> entry : instanceList.entrySet())
	    {
	    	if(entry.getValue().size()==0)
	    	{
	    		ArrayList<String>li2=new ArrayList<String>();
	    		li2.add("NA");
	    		instanceList.put(entry.getKey(), li2);
	    	}
	    }
	    
	   
	    
	    return classInstance.get(className);
	}//function ends
}
