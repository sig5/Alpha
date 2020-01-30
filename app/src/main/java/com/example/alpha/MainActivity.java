package com.example.alpha;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

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
