package com.example.alpha;

import org.json.JSONException;
import org.json.JSONObject;

public class Innings {

	String innscore;
	String wickets;
	String overs;
	String name;
	public Innings(JSONObject inn, String name) throws JSONException {
		
		innscore = inn.getString("score");
		wickets = inn.getString("wkts");
		overs = inn.getString("overs");
		this.name = name;
	}

	public String getInnscore() {
		return innscore;
	}
	public String getName() {
		return name;
	}

	public String getWickets() {
		return wickets;
	}

	public String getOvers() {
		return overs;
	}
	
}
