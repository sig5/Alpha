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
        public String over;
        @Override
        public String toString()
                {
                    if(over!=null)
            return over+" : "+comm;
                    return comm;
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
                if(comm_line.has("o_no"))
                    cm.over=comm_line.getString("o_no");
                comm_lines.add(cm);
            }
        }
    }
}
