package com.example.alpha;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data_fetcher("https://cricapi.com/api/cricket/?apikey=Bd8wF5XUVGRFmScoOnpJ5aEh93d2");
         recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        ArrayList<String> a=new ArrayList<String>();
        a.add("India");
        a.add("Australia");
        a.add("England");
        Custom_adapter ca =new Custom_adapter(a);

        LinearLayoutManager RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        Custom_adapter RecyclerViewHorizontalAdapter = new Custom_adapter(a);

        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);

        recyclerView.setAdapter(RecyclerViewHorizontalAdapter);

        SnapHelper linearSnapHelper = new PagerSnapHelper();
        linearSnapHelper.attachToRecyclerView(recyclerView);

    }

    private void data_fetcher(final String url)
    {
    class data_fetch extends AsyncTask<Void,Void,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url_score = new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection) url_score.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    System.out.println(stringBuilder.toString());
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject reader=new JSONObject(s);
                JSONArray live_score_array=reader.getJSONArray("data");
                String[] mydata=new String[live_score_array.length()];
                for(int i=0;i<live_score_array.length();i++)
                {
                    JSONObject jsonObject=live_score_array.getJSONObject(i);
                     mydata[i]=jsonObject.getString("title");
                }
                Custom_adapter myadapter =new Custom_adapter(Arrays.asList(mydata));
                recyclerView.setAdapter(myadapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    data_fetch data_fetch=new data_fetch();
    data_fetch.execute();
    }
}
