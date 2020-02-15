package com.example.alpha;

import org.json.JSONException;
import org.json.JSONObject;

public class Venue {
	String timezone;
	String GroundName;
	String Location;
	String latitude;
	String longitutde;
	
	public Venue(JSONObject v) throws JSONException 
	{
		timezone = v.getString("timezone");
		GroundName = v.getString("name");
		Location = v.getString("location");
		latitude = v.getString("lat");
		longitutde = v.getString("long");
	}
	public void print()
	{
		System.out.println("Time Zone : " + timezone + ", Ground Name " + GroundName + ", Location : " +Location + "" + "");
	}
	public String getTimezone() {
		return timezone;
	}
	public String getGroundName() {
		return GroundName;
	}
	public String getLocation() {
		return Location;
	}
	public String getLatitude() {
		return latitude;
	}
	public String getLongitutde() {
		return longitutde;
	}
	
}
