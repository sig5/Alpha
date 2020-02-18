package com.example.alpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

public class Cric_Match_Details extends AppCompatActivity implements Serializable {
    Match match;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cric__match__details);
        BottomNavigationView bnv = findViewById(R.id.bottom_nav_cric_match_details);
        Intent i=getIntent();
        System.out.println("brjnfksfkanfc");
        Cricket_live_scores obj= (Cricket_live_scores) i.getParcelableExtra("matchkey");
        match=obj.m;
        if(match==null)
            System.out.println("YES");
        System.out.println(match.getStatus());
        bnv.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cric_match_details, new Cric_Commentary(match), "L").commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;


            switch (menuItem.getItemId()) {
                case R.id.nav_cric_2: {
                    selectedFragment = new Cric_Commentary(match);

                    if (getSupportFragmentManager().findFragmentByTag("L") == null) {
                        getSupportFragmentManager().beginTransaction().add(R.id.fragment_cric_match_details, selectedFragment, "L").commit();
                        if (getSupportFragmentManager().findFragmentByTag("I") != null)
                            getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("I")).commit();


                    } else {
                        getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("L")).commit();
                        if (getSupportFragmentManager().findFragmentByTag("I") != null)
                            getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("I")).commit();
                    }

                }

                break;
                case R.id.nav_cric_1: {


                    selectedFragment = new CricInfo_Fragment(match);
                    if (getSupportFragmentManager().findFragmentByTag("I") == null) {
                        getSupportFragmentManager().beginTransaction().add(R.id.fragment_cric_match_details, selectedFragment, "I").commit();
                        if (getSupportFragmentManager().findFragmentByTag("L") != null)
                            getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("L")).commit();
                    } else {
                        getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("I")).commit();
                        if (getSupportFragmentManager().findFragmentByTag("L") != null)
                            getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("L")).commit();


                    }

                }
                break;
            }
            return true;
        }

    };
}
