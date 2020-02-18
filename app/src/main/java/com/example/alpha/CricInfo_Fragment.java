package com.example.alpha;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

public class CricInfo_Fragment extends Fragment implements Serializable {
    Match m;
    TextView des, series, toss, stadium, venue, umpires, thirdumpire, refree;
    String des1="Match Description: ", series1="Series: ", toss1="Toss: ", stadium1="Stadium: ", venue1="City: ", umpires1="Umpires: ", thirdumpire1="Third Umpire: ", refree1="Refree: ";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cric_info, container, false);
        des = view.findViewById(R.id.match_des);
        series = view.findViewById(R.id.series);
        toss = view.findViewById(R.id.Toss);
        stadium = view.findViewById(R.id.stadium);
        venue = view.findViewById(R.id.Venue);
        umpires = view.findViewById(R.id.umpires);
        thirdumpire = view.findViewById(R.id.thirdumpire);
        refree = view.findViewById(R.id.refree);
        data_fetcher(" ");
        return view;

    }

    private void data_fetcher(final String url) {

        final String[] comm = new String[1];
        class data_fetch extends AsyncTask<Void, Void, String> {
            String s;

            protected void onPreExecute() {
                super.onPreExecute();
//                view.setVisibility(View.VISIBLE);
            }

            @Override
            protected String doInBackground(Void... voids) {
                des1+= m.getMatchDescription();
                toss1+= m.getToss();
                series1+= m.getSeriesName();


                try {
                    URL url = new URL("https://mapps.cricbuzz.com/cbzios/match/"+m.getMatchID());
                    String responseJsonString = "";
//                    System.out.println("in Cricbuzz parser" + u + "1st");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
//                        System.out.println("in Cricbuzz parser" + u + "2nd");
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder responseString = new StringBuilder();
                        String line;
//                        System.out.println("in Cricbuzz parser" + u);
                        while ((line = bufferedReader.readLine()) != null) {
                            responseString.append(line);
                        }
//				System.out.println("in Cricbuzz parser"+u);
                        bufferedReader.close();
                        responseJsonString = responseString.toString();

                        JSONObject obj=new JSONObject(responseJsonString);
                        JSONObject objOff=obj.getJSONObject("official");
                        JSONObject objVenue=obj.getJSONObject("venue");
                        stadium1+=objVenue.getString("name");
                        venue1+=objVenue.getString("location");
                        umpires1+=objOff.getJSONObject("umpire1").getString("name")+",";
                        umpires1+=objOff.getJSONObject("umpire2").getString("name");
                        thirdumpire1+=objOff.getJSONObject("umpire3").getString("name");

                        refree1+=objOff.getJSONObject("referee").getString("name");

                    } finally {
                        urlConnection.disconnect();
                    }
                } catch (Exception except) {
//			System.out.println("jnakcdakcmalncsa");
                    System.out.println(except.getMessage() + "Ëxc");
                }


                return null;
            }


            @Override
            protected void onPostExecute(String s) {
                //v.print();
                System.out.println("fbhvhfj");
                des.append(des1);
                series.append(series1);
                toss.append(toss1);
                stadium.append(stadium1);
                venue.append(venue1);
                umpires.append(umpires1);
                thirdumpire.append(thirdumpire1);
                refree.append(refree1);
//                view.setVisibility(View.GONE);
            }
        }
        data_fetch data_fetch = new data_fetch();
        data_fetch.execute();
    }

    CricInfo_Fragment(Match m) {
        this.m = m;
    }

}
