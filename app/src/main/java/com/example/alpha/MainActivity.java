package com.example.alpha;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data_fetcher("https://cricapi.com/api/cricket/?apikey=Bd8wF5XUVGRFmScoOnpJ5aEh93d2");
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
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

    private void data_fetcher(final String urlweb)
    {
    class data_fetch extends AsyncTask<Void,Void,String>
    {String json;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder builder = null;
            try {
                URL url = new URL(urlweb);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                builder = new StringBuilder();
                while ((json = bufferedReader.readLine()) != null) {
                    builder.append(json);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return builder.toString().trim();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<Cricket_live_scores> cricket_live_scores = null;
            try {
                JSONArray jsonArray=new JSONArray(s);
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    cricket_live_scores.add(jsonObject.getString(""));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
    data_fetch data_fetch=new data_fetch();
    data_fetch.execute();
    }
}
