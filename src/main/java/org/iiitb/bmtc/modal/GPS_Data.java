package org.iiitb.bmtc.modal;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GPS_Data {

	private String Device_ID;
	private double latitude;
	private double longitude;
	private String vehicle_direction;
	private double speed_KMPH;
	private Date IST_Date;

	public GPS_Data() {
	}

	public GPS_Data(String device_ID, double latitude, double longitude, String vehicle_direction, double speed_KMPH,
			Date iST_Date) {
		Device_ID = device_ID;
		this.latitude = latitude;
		this.longitude = longitude;
		this.vehicle_direction = vehicle_direction;
		this.speed_KMPH = speed_KMPH;
		this.IST_Date = iST_Date;
	}

	public String getDevice_ID() {
		return Device_ID;
	}

	public void setDevice_ID(String device_ID) {
		Device_ID = device_ID;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getVehicle_direction() {
		return vehicle_direction;
	}

	public void setVehicle_direction(String vehicle_direction) {
		this.vehicle_direction = vehicle_direction;
	}

	public double getSpeed_KMPH() {
		return speed_KMPH;
	}

	public void setSpeed_KMPH(double speed_KMPH) {
		this.speed_KMPH = speed_KMPH;
	}

	public Date getIST_Date() {
		return IST_Date;
	}

	public void setIST_Date(Date iST_Date) {
		IST_Date = iST_Date;
	}
	
	//toString Method
	public String toString(){
		return "deviceId : " + Device_ID + " latitude : " + latitude + " longitude : " + longitude + " vehicle_direction : " 
				+ vehicle_direction + " speed_KMPH : " + speed_KMPH+ " IST_Date : " + IST_Date;
	}
}
