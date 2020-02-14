package com.example.alpha;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Commentary
{
    class CommPOJO{
        public String timestamp;
        public String comm;

        @Override
        public String toString()
        {
            return "Time : " + timestamp + ", " + "Commentary : " + comm;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CommPOJO temp:
             comm_lines) {
            sb.append(temp.toString() + "\n");
        }
        return sb.toString();
    }

    ArrayList<CommPOJO> comm_lines;

    public Commentary(JSONObject jsonObject) throws JSONException
    {
        comm_lines = new ArrayList<>();
        JSONArray commlinesArray = jsonObject.getJSONArray("comm_lines");
        int i;
        int j = commlinesArray.length();
        for(i=0; i< j;i++)
        {
            JSONObject comm_line= commlinesArray.getJSONObject(i);
            if (!comm_line.has("link") && comm_line.has("comm") && comm_line.has("timestamp"))
            {
                CommPOJO cm = new CommPOJO();
                cm.timestamp = comm_line.getString("timestamp");
                cm.comm = comm_line.getString("comm");
                comm_lines.add(cm);

            }

            //System.out.println(scorecard);
        }

    }
}
