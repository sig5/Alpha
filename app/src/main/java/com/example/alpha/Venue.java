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
		GroundName = v.getString("name");
		Location = v.getString("location");
	}
	public void print()
	{
		System.out.println( "Ground Name " + GroundName + "- Location : " +Location + "" + "");
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
