package org.iiitb.bmtc.modal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Schedule {
	
	private String routeNo;
	private String distance;
	private String origin;
	private String destination;
	private String map_json;
	private String schedule_departure_from_origin;
	private String scheduled_arrival_at_destination;
	private String scheduled_departure_from_destination;
	private String scheduled_arrival_at_origin;
	
	public Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Schedule(String routeNo, String distance, String origin, String destination, String map_json,
			String schedule_departure_from_origin, String scheduled_arrival_at_destination,
			String scheduled_departure_from_destination, String scheduled_arrival_at_origin) {
		super();
		this.routeNo = routeNo;
		this.distance = distance;
		this.origin = origin;
		this.destination = destination;
		this.map_json = map_json;
		this.schedule_departure_from_origin = schedule_departure_from_origin;
		this.scheduled_arrival_at_destination = scheduled_arrival_at_destination;
		this.scheduled_departure_from_destination = scheduled_departure_from_destination;
		this.scheduled_arrival_at_origin = scheduled_arrival_at_origin;
	}
	public String getRouteNo() {
		return routeNo;
	}
	public void setRouteNo(String routeNo) {
		this.routeNo = routeNo;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getMap_json() {
		return map_json;
	}
	public void setMap_json(String map_json) {
		this.map_json = map_json;
	}
	public String getSchedule_departure_from_origin() {
		return schedule_departure_from_origin;
	}
	public void setSchedule_departure_from_origin(String schedule_departure_from_origin) {
		this.schedule_departure_from_origin = schedule_departure_from_origin;
	}
	public String getScheduled_arrival_at_destination() {
		return scheduled_arrival_at_destination;
	}
	public void setScheduled_arrival_at_destination(String scheduled_arrival_at_destination) {
		this.scheduled_arrival_at_destination = scheduled_arrival_at_destination;
	}
	public String getScheduled_departure_from_destination() {
		return scheduled_departure_from_destination;
	}
	public void setScheduled_departure_from_destination(String scheduled_departure_from_destination) {
		this.scheduled_departure_from_destination = scheduled_departure_from_destination;
	}
	public String getScheduled_arrival_at_origin() {
		return scheduled_arrival_at_origin;
	}
	public void setScheduled_arrival_at_origin(String scheduled_arrival_at_origin) {
		this.scheduled_arrival_at_origin = scheduled_arrival_at_origin;
	}
	//toString method
	public String toString(){
		return "routeno : " + routeNo + "\ndistance : " + distance + "\norigin : " + origin + "\ndestination : " + destination + "\nschedule_departure_from_origin : " + schedule_departure_from_origin;
	}
}
