package com.example.alpha;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static android.widget.Toast.makeText;
import static com.example.alpha.string_functions.isStarted;
import static com.example.alpha.string_functions.split_Score;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bnv = findViewById(R.id.bottom_nav);
        bnv.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new CricketFragment(),"C").commit();
    }

    TreeMap<Integer, Fragment> map = new TreeMap<Integer, Fragment>();
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    {

                        switch (menuItem.getItemId()) {
                            case R.id.nav_cricket: {
                                selectedFragment = new CricketFragment();

                                if (getSupportFragmentManager().findFragmentByTag("C") == null) {
                                    getSupportFragmentManager().beginTransaction().add(R.id.fragment, selectedFragment, "C").commit();
                                    if (getSupportFragmentManager().findFragmentByTag("F") != null)
                                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("F")).commit();
                                    if (getSupportFragmentManager().findFragmentByTag("N") != null)
                                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("N")).commit();
                                } else {
                                    getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("C")).commit();
                                    if (getSupportFragmentManager().findFragmentByTag("F") != null)
                                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("F")).commit();
                                    if (getSupportFragmentManager().findFragmentByTag("N") != null)
                                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("N")).commit();
                                }

                            }

                            break;
                            case R.id.nav_football: {


                                selectedFragment = new FootballFragment();
                                if (getSupportFragmentManager().findFragmentByTag("F") == null) {
                                    getSupportFragmentManager().beginTransaction().add(R.id.fragment, selectedFragment, "F").commit();
                                    if (getSupportFragmentManager().findFragmentByTag("N") != null)
                                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("N")).commit();
                                    if (getSupportFragmentManager().findFragmentByTag("C") != null) {
                                        System.out.println("hum aaye yaha");
                                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("C")).commit();

                                    }
                                } else {
                                    getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("F")).commit();
                                    if (getSupportFragmentManager().findFragmentByTag("N") != null)
                                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("N")).commit();
                                    if (getSupportFragmentManager().findFragmentByTag("C") != null) {
                                        System.out.println("hum aaye yaha");
                                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("C")).commit();
                                    }
                                }

                            }
                            break;
                            case R.id.nav_news: {
                                selectedFragment = new NewsFragment();
                                if (getSupportFragmentManager().findFragmentByTag("N") == null) {
                                    getSupportFragmentManager().beginTransaction().add(R.id.fragment, selectedFragment, "N").commit();
                                    if (getSupportFragmentManager().findFragmentByTag("F") != null)
                                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("F")).commit();
                                    if (getSupportFragmentManager().findFragmentByTag("C") != null)
                                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("C")).commit();
                                } else {
                                    getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("N")).commit();
                                    if (getSupportFragmentManager().findFragmentByTag("F") != null)
                                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("F")).commit();
                                    if (getSupportFragmentManager().findFragmentByTag("C") != null)
                                        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("C")).commit();
                                }
                            }
                            break;
                        }
//                        System.out.println(getSupportFragmentManager().getBackStackEntryCount());


                        {


                            return true;
                        }
                    }


                }
            };
}