package com.example.alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.Math.min;

public class fixtures extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);
        fixturefetcher("https://livescore-api.com/api-client/fixtures/matches.json?key=iH22RHhzVqCF2n2Y&secret=C5BdyDOW8pNmsJaLqcNhT5RPvuvySxxu");
    }
    private void fixturefetcher(final String url)
    {
        class fixturefetch extends AsyncTask<Void,Void,String>
        { String[] team1=new String[100];
            String[] team2=new String[100];
            String[] date=new String[100];
            String[] time=new String[100];

            @Override
            protected String doInBackground(Void... voids) {
                JSONArray football_fixture;


                try {
                    URL url1=new URL(url);
                    HttpURLConnection httpURLConnection=(HttpURLConnection) url1.openConnection();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    StringBuilder stringBuilder=new StringBuilder();
                    String s="";
                    while((s=bufferedReader.readLine())!=null)
                    {
                        stringBuilder.append(s);

                    }
                    bufferedReader.close();
                    JSONObject jsonObject=new JSONObject(stringBuilder.toString());
                    JSONObject jsonObject1=jsonObject.getJSONObject("data");
                    football_fixture=jsonObject1.getJSONArray("fixtures");
                    for(int i=0;i<min(5,football_fixture.length());i++)
                    {System.out.println("whyyyyyy");
                        JSONObject obj=(JSONObject)football_fixture.get(i);
                        team1[i]=obj.getString("home_name");
                        team2[i]=obj.getString("away_name");
                        date[i]=obj.getString("date");
                        time[i]=obj.getString("time");

                    }



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                TextView textView1=findViewById(R.id.t1);
                TextView textView2=findViewById(R.id.t2);
                TextView textView3=findViewById(R.id.t3);
                TextView textView4=findViewById(R.id.t4);
                TextView textView5=findViewById(R.id.t5);
                TextView textView6=findViewById(R.id.t6);
                TextView textView7=findViewById(R.id.t7);
                TextView textView8=findViewById(R.id.t8);
                TextView textView9=findViewById(R.id.t9);
                TextView textView10=findViewById(R.id.t10);
                TextView textView11=findViewById(R.id.t11);
                TextView textView12=findViewById(R.id.t12);
                TextView textView13=findViewById(R.id.t13);
                TextView textView14=findViewById(R.id.t14);
                TextView textView15=findViewById(R.id.t15);
                TextView textView16=findViewById(R.id.t16);
                TextView textView17=findViewById(R.id.t17);
                TextView textView18=findViewById(R.id.t18);
                TextView textView19=findViewById(R.id.t19);
                TextView textView20=findViewById(R.id.t20);
                textView1.setText(team1[0]);
                textView2.setText(team1[1]);
                textView3.setText(team1[2]);
                textView4.setText(team1[3]);
                textView5.setText(team1[4]);
                textView6.setText(team2[0]);
                textView7.setText(team2[1]);
                textView8.setText(team2[2]);
                textView9.setText(team2[3]);
                textView10.setText(team2[4]);
                textView11.setText(date[0]);
                textView12.setText(date[1]);
                textView13.setText(date[2]);
                textView14.setText(date[3]);
                textView15.setText(date[4]);
                textView16.setText(time[0]);
                textView17.setText(time[1]);
                textView18.setText(time[2]);
                textView19.setText(time[3]);
                textView20.setText(time[4]);

            }
        }
        fixturefetch data=new fixturefetch();
        data.execute();
    }
}
