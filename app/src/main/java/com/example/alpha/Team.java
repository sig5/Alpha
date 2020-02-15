package com.example.alpha;

import org.json.JSONException;
import org.json.JSONObject;

public class Team 
{
	String id;
	String name;
	String s_name;
	JSONObject t;
	
	public Team(JSONObject team) throws JSONException 
	{
		t=team;
		id = get("id");
		name = get("name");
		s_name = get("s_name");
	}
	private String get(String string) throws JSONException
	{
		return t.getString(string);
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getS_name() {
		return s_name;
	}
	public JSONObject getT() {
		return t;
	}
	
}
