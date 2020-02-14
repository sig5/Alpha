package com.example.alpha;

import java.net.*;
import java.util.logging.Logger;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.*;

import javax.net.ssl.HttpsURLConnection;

public class CricBuzzParser 
{
	String url;

	public ArrayList<Match> getMatches() {
		return Matches;
	}

	String resp;
	ArrayList<Match> Matches;
	
	public static void main(String[] args) 
	{
		CricBuzzParser cbp = new CricBuzzParser("http://mapps.cricbuzz.com/cbzios/match/livematches");
		cbp.RetrieveURL();
		cbp.Parse();
	}
	public CricBuzzParser(String url) 
	{
		this.url = url;
		Matches = new ArrayList<>();
	}
	public void RetrieveURL()
	{
		resp = getContentsOfURL(this.url);
	}
	private static String getContentsOfURL(String u)
	{
		try
		{
			URL url = new URL(u);
			String responseJsonString="";
//			System.out.println("in Cricbuzz parser"+u+"1st");
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			try {
//				System.out.println("in Cricbuzz parser"+u+"2nd");
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                            StringBuilder responseString = new StringBuilder();
				String line;
//				System.out.println("in Cricbuzz parser"+u);
				while ((line = bufferedReader.readLine()) != null) {
					responseString.append(line);
				}
//				System.out.println("in Cricbuzz parser"+u);
				bufferedReader.close();
				responseJsonString = responseString.toString();
			}finally {
				urlConnection.disconnect();
			}
			return responseJsonString;
		}
		catch(Exception except)
		{
//			System.out.println("jnakcdakcmalncsa");
			System.out.println(except.getMessage());

			return null;
		}
	}
	private static String getCommentaryURL(String mid)
	{
		String urlCommentary = "http://mapps.cricbuzz.com/cbzios/match/" + mid + "/commentary";
		return urlCommentary;
	}
	public static Commentary RetrieveCommentary(Match m)
	{
		String comm = getContentsOfURL(getCommentaryURL(m.getMatchID()));
		JSONObject jo;

		try {
			Commentary c = new Commentary(new JSONObject(comm));
			return c;
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			return null;
		}

	}
	public void putResult(String result)
	{
		this.resp = result;
	}
	public Boolean Parse()
	{
		JSONObject jo;
	
		try 
		{
//			System.out.println(resp);
			jo = new JSONObject(resp);
			JSONArray MatchesList = jo.getJSONArray("matches");
			int i;
			int j = MatchesList.length();
			for(i=0; i< j;i++)
			{
				JSONObject individualMatch= MatchesList.getJSONObject(i);
				Match m = new Match(individualMatch);
				Matches.add(m);
				String scorecard = m.getScoreCard();
				//System.out.println(scorecard);
			}
		}
		catch (JSONException e) 
		{
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
