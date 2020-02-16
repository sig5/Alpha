package com.example.alpha;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Cric_Commentary extends Fragment implements Serializable {
    Match m;
    TextView sv, bat1n, bat2n, bowln, bat1r, bat2r, bat16, bat14, bat1b, bat1sr, bat26, bat24, bat2b, bat2sr, bowlo, bowlr, bowlm, bowlw, bowle;
    TextView score, Status, ps, crr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cric_commentary, container, false);
        sv = view.findViewById(R.id.commentary);
        score = view.findViewById(R.id.Score);
        Status = view.findViewById(R.id.Status);
        ps = view.findViewById(R.id.Partnership);
        crr = view.findViewById(R.id.Crr);
        bat1n = view.findViewById(R.id.Batsman1);
        bat1r = view.findViewById(R.id.runs1);
        bat16 = view.findViewById(R.id.six1);
        bat14 = view.findViewById(R.id.four1);
        bat1b = view.findViewById(R.id.balls1);
        bat1sr = view.findViewById(R.id.sr1);
        bat2n = view.findViewById(R.id.Batsmen2);
        bat2r = view.findViewById(R.id.runs2);
        bat26 = view.findViewById(R.id.six2);
        bat24 = view.findViewById(R.id.four2);
        bat2b = view.findViewById(R.id.balls2);
        bat2sr = view.findViewById(R.id.sr2);
        bowln = view.findViewById(R.id.Bowler);
        bowlm = view.findViewById(R.id.maidens);
        bowlo = view.findViewById(R.id.overs);
        bowlr = view.findViewById(R.id.bruns);
        bowlw = view.findViewById(R.id.wickets);
        bowle = view.findViewById(R.id.er1);

        String match_id = m.getMatchID();
        String url = "http://mapps.cricbuzz.com/cbzios/match/" + match_id + "/commentary";
        data_fetcher(" ");


//        Commentary com=m.getCommentary();
//        if(m==null)
//            System.out.println("qwerty");
//        System.out.println(m.getScoreCard());
//        String commen = com.comm;
//      tv.setText(m.getScoreCard());
        return view;
    }

    private void data_fetcher(final String url) {

        final String[] comm = new String[1];
        final String[] urlcomm = new String[1];
        final String[] miniscore = new String[1];
        final String[] status = new String[1];
        final String[] scorecard = new String[1];
        class data_fetch extends AsyncTask<Void, Void, String> {
            String s;

            protected void onPreExecute() {
                super.onPreExecute();
//                view.setVisibility(View.VISIBLE);
            }

            @SuppressLint("WrongThread")
            @Override
            protected String doInBackground(Void... voids) {
//
                Commentary c = m.getCommentary();

                comm[0] = c.toString();

                urlcomm[0] = "https://mapps.cricbuzz.com/cbzios/match/" + m.getMatchID() + "/commentary";
                miniscore[0] = CricBuzzParser.getContentsOfURL(urlcomm[0]);
                status[0] = m.getStatus();
                scorecard[0] = m.getScorecard();

                return null;
            }


            @Override
            protected void onPostExecute(String s) {
                //String str="";

                try {
                    JSONObject ms = new JSONObject(miniscore[0]);
                    String t1 = "";
                    String str = scorecard[0];
                    int i = 0;
                    for (i = 0; i < str.length(); i++) {
                        if (str.charAt(i) == '-')
                            break;
                        t1 = t1 + str.charAt(i);
                    }
                    i++;
                    String t2 = str.substring(i);
                    score.setText(t1 + "\n" + t2);
                    ps.setText("P'ship : " + ms.getString("prtshp"));
                    crr.setText("CRR : " + ms.getString("crr"));
                    Status.setText(status[0]);
                    JSONArray bat = ms.getJSONArray("batsman");

                    JSONArray bowl = ms.getJSONArray("bowler");

                    if (bat.length() == 2) {
                        JSONObject obj = bat.getJSONObject(0);
                        if (obj.getString("strike") == "1")
                            bat1n.setText(obj.getString("name") + "*");
                        else
                            bat1n.setText(obj.getString("name"));
                        bat1r.setText(obj.getString("r"));
                        bat1b.setText(obj.getString("b"));
                        bat14.setText(obj.getString("4s"));
                        bat16.setText(obj.getString("6s"));
                        float r = Integer.parseInt(obj.getString("r"));
                        float b = Integer.parseInt(obj.getString("b"));
                        String sr = String.valueOf((r / b) * 100);
                        if (sr.length() >= 4)
                            bat1sr.setText(sr.substring(0, 4));
                        else
                            bat1sr.setText(sr);
                        JSONObject obj1 = bat.getJSONObject(1);
                        if (obj1.getString("strike") == "1")
                            bat2n.setText(obj1.getString("name") + "*");
                        else
                            bat2n.setText(obj1.getString("name"));
                        bat2r.setText(obj1.getString("r"));
                        bat2b.setText(obj1.getString("b"));
                        bat24.setText(obj1.getString("4s"));
                        bat26.setText(obj1.getString("6s"));
                        float r1 = Integer.parseInt(obj1.getString("r"));
                        float b1 = Integer.parseInt(obj1.getString("b"));
                        String sr1 = String.valueOf((r1 / b1) * 100);
                        if (sr1.length() >= 4)
                            bat2sr.setText(sr1.substring(0, 4));
                        else
                            bat2sr.setText(sr1);
                    }
                    if (bat.length() == 1) {
                        JSONObject obj = bat.getJSONObject(0);
                        if (obj.getString("strike") == "1")
                            bat1n.setText(obj.getString("name") + "*");
                        else
                            bat1n.setText(obj.getString("name"));
                        bat1r.setText(obj.getString("r"));
                        bat1b.setText(obj.getString("b"));
                        bat14.setText(obj.getString("4s"));
                        bat16.setText(obj.getString("6s"));
                        float r = Integer.parseInt(obj.getString("r"));
                        float b = Integer.parseInt(obj.getString("b"));
                        String sr = String.valueOf((r / b) * 100);
                        if (sr.length() >= 4)
                            bat1sr.setText(sr.substring(0, 4));
                        else
                            bat1sr.setText(sr);

                    }
                    JSONObject obj2 = bowl.getJSONObject(0);
                    bowlw.setText(obj2.getString("w"));
                    bowlr.setText(obj2.getString("r"));
                    bowlm.setText(obj2.getString("m"));
                    bowln.setText(obj2.getString("name"));
                    bowlo.setText(obj2.getString("o"));
                    float r = Integer.parseInt(obj2.getString("r"));
                    float o = Float.parseFloat(obj2.getString("o"));
//                    double
                    String er = String.valueOf((r / o));
                    if (er.length() >= 4)
                        bowle.setText(er.substring(0, 4));
                    else
                        bowle.setText(er);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String[] lines = string_functions.split_Score(comm[0]);
                //System.out.println(HtmlCompat.fromHtml(comm[0], 0));
                for (int i = 0; i < lines.length; i++) {
                    sv.append("\n\n");
                    sv.append(HtmlCompat.fromHtml(lines[i], 0));
                }

//                view.setVisibility(View.GONE);
            }
        }
        data_fetch data_fetch = new data_fetch();
        data_fetch.execute();
    }

    Cric_Commentary(Match m) {
        this.m = m;
    }

}
