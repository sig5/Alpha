package com.example.alpha;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data_fetcher("https://cricapi.com/api/cricket/?apikey=Bd8wF5XUVGRFmScoOnpJ5aEh93d2");
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),2);
//        recyclerView.setLayoutManager(gridLayoutManager);
        ArrayList<String> a=new ArrayList<String>();
        a.add("India");
        a.add("Australia");
        a.add("England");
        Custom_adapter ca =new Custom_adapter(a);
        recyclerView.setAdapter(ca);



//        recyclerView = findViewById(R.id.recyclerview1);

        LinearLayoutManager RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
//        AddItemsToRecyclerViewArrayList();

        Custom_adapter RecyclerViewHorizontalAdapter = new Custom_adapter(a);

        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);

        recyclerView.setAdapter(RecyclerViewHorizontalAdapter);


    }

    private void data_fetcher(String url)
    {
    class data_fetch extends AsyncTask<Void,Void,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
    data_fetch data_fetch=new data_fetch();
    data_fetch.execute();
    }
}
