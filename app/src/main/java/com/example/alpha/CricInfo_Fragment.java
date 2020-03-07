package com.example.alpha;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.example.alpha.Info.squads;

//jbjf
public class CricInfo_Fragment extends Fragment implements Serializable {
    Match m;
    TextView tname1,tname2;
    ListView lv,lvt1,lvt2;
    String t1="",t2="";
//    String[] squad1=new String[25];
    ArrayList<String> squad1=new ArrayList<>();

    ArrayList<String> squad2=new ArrayList<>();
    CardView c1,c2;
    String des1="Match Description: ", series1="Series: ", toss1="Toss: ", stadium1="Stadium: ", venue1="City: ", umpires1="Umpires: ", thirdumpire1="Third Umpire: ", refree1="Refree: ";
  //jbhvhbbfhb
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cric_info, container, false);
        lv=view.findViewById(R.id.details);
        lvt1=view.findViewById(R.id.squad1);
        lvt2=view.findViewById(R.id.squad2);
        tname1=view.findViewById(R.id.name1);

        c1=view.findViewById(R.id.cd1);
        c2=view.findViewById(R.id.cd2);
        tname2=view.findViewById(R.id.name2);
        data_fetcher(" ");
        return view;

    }



    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tname1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lvt1.getVisibility()==View.GONE)
                {
                    lvt1.setVisibility(View.VISIBLE);
                }
                else
                {
                    lvt1.setVisibility(View.GONE);
//                    tname1.setVisibility(View.VISIBLE);
                }
            }
        });

        tname2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(lvt2.getVisibility()==View.GONE)
                {
                    lvt2.setVisibility(View.VISIBLE);
//                    tname2.setVisibility(View.GONE);
                }
                else
                {
                    lvt2.setVisibility(View.GONE);
//                    tname2.setVisibility(View.VISIBLE);
                }


            }
        });

    }




  //vgvgfujd
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
                des1+= m.getMatchDescription();
                toss1+= m.getToss();
                series1+= m.getSeriesName();


                try {
                    URL url = new URL("https://mapps.cricbuzz.com/cbzios/match/"+m.getMatchID());
                    String responseJsonString = "";
//                    System.out.println("in Cricbuzz parser" + u + "1st");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
//                        System.out.println("in Cricbuzz parser" + u + "2nd");
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder responseString = new StringBuilder();
                        String line;
//                        System.out.println("in Cricbuzz parser" + u);
                        while ((line = bufferedReader.readLine()) != null) {
                            responseString.append(line);
                        }
//				System.out.println("in Cricbuzz parser"+u);
                        bufferedReader.close();
                        responseJsonString = responseString.toString();

                        String[] squad=squads(responseJsonString);
                        t1=squad[0];

                        int j=0,i=1;
                        while(squad[i]!="SPLIT")
                        {
                            squad1.add(squad[i]);
                            i++;

                        }


                        i++;
                        t2=squad[i];
                        i++;
                        while(i<squad.length && squad[i]!=null)
                        {
                            squad2.add(squad[i]);
                            i++;
                        }
                        JSONObject obj=new JSONObject(responseJsonString);
                        JSONObject objOff=obj.getJSONObject("official");
                        JSONObject objVenue=obj.getJSONObject("venue");
                        stadium1+=objVenue.getString("name");
                        venue1+=objVenue.getString("location");
                        umpires1+=objOff.getJSONObject("umpire1").getString("name")+",";
                        umpires1+=objOff.getJSONObject("umpire2").getString("name");



                        System.out.println(squad1);

                        System.out.println(squad2);

                        thirdumpire1+=objOff.getJSONObject("umpire3").getString("name");
                        refree1+=objOff.getJSONObject("referee").getString("name");

                    } finally {
                        urlConnection.disconnect();
                    }
                } catch (Exception except) {
                    System.out.println(except.getMessage() + "Ëxc");
                }


                return null;
            }


            @Override
            protected void onPostExecute(String s) {

                System.out.println("yaha");
                String[] details=new String[8];
                details[0]=(des1);

                details[1]=(series1);
                details[2]=(toss1);
                details[3]=(stadium1);
                details[4]=(venue1);
                details[5]=(umpires1);
                details[6]=(thirdumpire1);
                details[7]=(refree1);
                tname1.setText(t1);
                tname2.setText(t2);
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),R.layout.row,R.id.row,details);

                ArrayAdapter<String> adapter1=new ArrayAdapter<String>(getContext(),R.layout.row,R.id.row,squad1);
                ArrayAdapter<String> adapter2=new ArrayAdapter<String>(getContext(),R.layout.row,R.id.row,squad2);
                lv.setAdapter(adapter);
                lvt1.setAdapter(adapter1);
                lvt2.setAdapter(adapter2);

//                view.setVisibility(View.GONE);
            }
        }
        data_fetch data_fetch = new data_fetch();
        data_fetch.execute();
    }




    CricInfo_Fragment(Match m) {
        this.m = m;
    }

}
