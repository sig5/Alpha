package com.example.alpha;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.ArrayList;

public class Cric_Commentary extends Fragment implements Serializable {
    Match m;
    TextView sv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cric_commentary,container,false);
        sv=view.findViewById(R.id.commentary);


        String match_id=m.getMatchID();
        String url="http://mapps.cricbuzz.com/cbzios/match/" + match_id + "/commentary";
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
        class data_fetch extends AsyncTask<Void, Void, String> {
            String s;

            protected void onPreExecute() {
                super.onPreExecute();
//                view.setVisibility(View.VISIBLE);
            }

            @Override
            protected String doInBackground(Void... voids) {
//
                Commentary c=m.getCommentary();
                comm[0] =c.toString();
                System.out.println(c);

                return null;
            }


            @Override
            protected void onPostExecute(String s) {

                sv.setText(comm[0]);
//                view.setVisibility(View.GONE);
            }
        }
        data_fetch data_fetch = new data_fetch();
        data_fetch.execute();
    }

    Cric_Commentary(Match m)
    {
        this.m=m;
    }

}
