package com.example.alpha;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static java.lang.Math.min;

public class FootballFragment extends Fragment {
    ProgressBar pb;

    ArrayList<f_matchlive> fls = new ArrayList<>();
    RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.fragment_football, container, false);
        pb = view.findViewById(R.id.progress2);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = (RecyclerView) view.findViewById(R.id.recyclerView);

        LinearLayoutManager RecyclerViewLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(RecyclerViewLayoutManager);
        LinearLayoutManager HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(HorizontalLayout);
        SnapHelper linearSnapHelper = new PagerSnapHelper();
        linearSnapHelper.attachToRecyclerView(rv);
        jsonfetcher("https://livescore-api.com/api-client/scores/live.json?key=iH22RHhzVqCF2n2Y&secret=C5BdyDOW8pNmsJaLqcNhT5RPvuvySxxu", pb);
    }

    private void jsonfetcher(final String url, final View view) {
        class jsonfetch extends AsyncTask<Void, Void, String> {
            JSONArray football_match;
            String[] scores = new String[100];
            String[] team1 = new String[100];
            String[] team2 = new String[100];
            String[] possession = new String[100];
            String[] penalty = new String[100];
            String[] red = new String[100];
            String[] yellow = new String[100];
            String[] fauls = new String[100];
            String[] corner = new String[100];
            String[] id = new String[100];

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                view.setVisibility(View.VISIBLE);
            }

            @Override
            protected String doInBackground(Void... voids) {

                try {
                    URL f_url = new URL(url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) f_url.openConnection();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();

                    String s = "";
                    while ((s = bufferedReader.readLine()) != null) {
                        stringBuilder.append(s);
                    }

                    bufferedReader.close();

                    JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    football_match = jsonObject1.getJSONArray("match");


                    for (int i = 0; i < min(5, football_match.length()); i++) {

                        JSONObject dummy = (JSONObject) football_match.get(i);
                        scores[i] = dummy.getString("score");
                        team1[i] = dummy.getString("home_name");
                        team2[i] = dummy.getString("away_name");
                        id[i] = dummy.getString("id");
                        String url1 = "https://live-score-api.com/api-client/matches/stats.json?match_id=" + id[i] + "&key=iH22RHhzVqCF2n2Y&secret=C5BdyDOW8pNmsJaLqcNhT5RPvuvySxxu";
                        URL url3 = new URL(url1);
                        HttpURLConnection httpURLConnectionstats = (HttpURLConnection) url3.openConnection();
                        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(httpURLConnectionstats.getInputStream()));
                        StringBuilder stringBuilderstats = new StringBuilder();
                        s = "";
                        while ((s = bufferedReader1.readLine()) != null) {
                            stringBuilderstats.append(s);
                            System.out.println(s);

                        }
                        bufferedReader1.close();
                        System.out.println(stringBuilderstats.toString());
                        JSONObject jsonObject2 = new JSONObject(stringBuilderstats.toString());
                        if (jsonObject2.toString().equals("{\"success\":true,\"data\":[]}")) {
                            continue;
                        }
                        // commit
                        else {
                            JSONObject jsonObject1stats = jsonObject2.getJSONObject("data");
                            red[i] = jsonObject1stats.getString("red_cards");
                            yellow[i] = jsonObject1stats.getString("yellow_cards");
                            penalty[i] = jsonObject1stats.getString("penalties");
                            possession[i] = jsonObject1stats.getString("possesion");
                            corner[i] = jsonObject1stats.getString("corners");
                            fauls[i] = jsonObject1stats.getString("fauls");
                        }


                    }
                    for (int i = 0; i < min(5, scores.length); i++) {
                        fls.add(new f_matchlive(team1[i], team2[i], scores[i], penalty[i], yellow[i], red[i], possession[i], fauls[i], corner[i]));
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
                f_custom_adapter custom_adapter = new f_custom_adapter(fls);
                rv.setAdapter(custom_adapter);
                view.setVisibility(View.GONE);
            }
        }
        jsonfetch sonfetch = new jsonfetch();
        sonfetch.execute();

    }

}
