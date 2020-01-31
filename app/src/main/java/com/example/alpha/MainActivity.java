package com.example.alpha;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;

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
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

    public class MainActivity extends AppCompatActivity {
        RecyclerView recyclerView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            data_fetcher("https://cricapi.com/api/cricket/?apikey=Bd8wF5XUVGRFmScoOnpJ5aEh93d2");
            recyclerView=(RecyclerView) findViewById(R.id.recyclerview);

            LinearLayoutManager RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(RecyclerViewLayoutManager);
            LinearLayoutManager HorizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(HorizontalLayout);
            SnapHelper linearSnapHelper = new PagerSnapHelper();
            linearSnapHelper.attachToRecyclerView(recyclerView);
        }
        public static String search(String txt, String[] pat)
        {
            for(String x:pat)
            {
                int M = x.length();
                int N = txt.length();
                for (int i = 0; i <= N - M; i++)
                {
                    int j;
                    for (j = 0; j < M; j++)
                    if (txt.charAt(i + j) != x.charAt(j))
                    break;

                    if (j == M)
                    return x;
                }
            }
        return null;
        }

        public static String[] split_Score(String data)
        {
            String[] str = data.split(" v ");
            return str;
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
                    String[] check={"India","Australia","England","Pakistan","South Africa","New Zealand","West Indies",
                    "Sri Lanka","Bangladesh","Zimbabve","Afghanistan","Ireland"};
                    Map<String,Integer> preference_Order=new HashMap<>();
                    preference_Order.put("India",1);
                    preference_Order.put("Australia",2);
                    preference_Order.put("England",3);
                    preference_Order.put("Pakistan",4);
                    preference_Order.put("New Zealand",5);
                    preference_Order.put("South Africa",6);
                    preference_Order.put("West Indies",7);
                    preference_Order.put("Sri Lanka",8);
                    preference_Order.put("Bangladesh",9);
                    preference_Order.put("Zimbabwe",10);
                    preference_Order.put("Afghanistan",11);
                    preference_Order.put("Ireland",12);


                    ArrayList<Cricket_live_scores> cls=new ArrayList<Cricket_live_scores>();
                    TreeMap<Integer,String[]> map=new TreeMap<>();

                    try {
                        JSONObject reader=new JSONObject(s);
                        JSONArray live_score_array=reader.getJSONArray("data");
                        String[] mydata=new String[live_score_array.length()];
                        for(int i=0;i<live_score_array.length();i++)
                        {
                            JSONObject jsonObject=live_score_array.getJSONObject(i);
                            mydata[i]=jsonObject.getString("description");

                            String[] teams=split_Score(mydata[i]);
                            if(search(teams[0],check)!=null  && search(teams[1],check)!=null) {
                                teams[0] = teams[0].replace("&amp;", "&");
                                teams[1] = teams[1].replace("&amp;", "&");
                                String t1 = search(teams[0], check);
                                String t2 = search(teams[1], check);
                                map.put(Math.min(preference_Order.get(t1), preference_Order.get(t2)), teams);
                            }
                        }
                        int count=0;
                        for(Map.Entry m:map.entrySet())
                        {
                            if(count>=5)
                                break;
                            String[] arr= (String[]) m.getValue();
                            cls.add(new Cricket_live_scores(arr[0],arr[1]));
                            count++;
                        }
                        Custom_adapter myadapter =new Custom_adapter(cls);
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
