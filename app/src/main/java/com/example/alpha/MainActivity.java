package com.example.alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void data_fetcher(URL url)
    {
        class fetch extends AsyncTask<Void,Void,String>{

            protected String doInBackground(Void... urls) {
                try{
                    URL url=new URL()
                }
                return null;
            }

        }
    }

}