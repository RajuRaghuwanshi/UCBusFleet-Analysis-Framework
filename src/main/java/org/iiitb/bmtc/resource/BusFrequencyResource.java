package org.iiitb.bmtc.resource;


import org.iiitb.bmtc.service.businessServices.BusFrequencyService;

public class BusFrequencyResource {
	
	
	public void getAllGPS_DataForRoute(String routeNo){
		
		BusFrequencyService busfreqService = new BusFrequencyService();
		
		boolean isBusFreq  = busfreqService.isBusFrequencyHigh(routeNo);
		if(isBusFreq){
			
			//Map<String, List<GPS_Data>>deviceGPSData = busfreqService.getGPSTraces("K-4");
			
		}else{
			System.out.println("increase !");
		}
		
	}
	
}
