package com.example.auntification;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SessionActivity extends AppCompatActivity {
        private RequestQueue mQueue;
        TextView usd;
        TextView eur;
        SharedPreferences sPref1;
        SharedPreferences sPref2;
        final String SAVED_TEXT1 = "saved_text";
        final String SAVED_TEXT2 = "saved_text";
        private SwipeRefreshLayout swipeContainer;
                @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_session);
                mQueue = Volley.newRequestQueue(this);
                usd = (TextView) findViewById(R.id.textView9);
                eur = (TextView) findViewById(R.id.textView11);
                usd.setText("  .    ");
                eur.setText("  .    ");
                if(isNetworkConnected()) {
                        MyTask mt = new MyTask();
                        mt.execute();
                        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
                        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                @Override
                                public void onRefresh() {
                                        new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                        if (isNetworkConnected()) {
                                                                usd.setText("  .    ");
                                                                eur.setText("  .    ");
                                                                MyTask mt = new MyTask();
                                                                mt.execute();
                                                                swipeContainer.setRefreshing(false);
                                                        } else {
                                                                Toast toast = Toast.makeText(getApplicationContext(), "No internet connection!", Toast.LENGTH_SHORT);
                                                                toast.show();
                                                                swipeContainer.setRefreshing(false);
                                                        }
                                                }
                                        }, 1000); // Delay in millis
                                }
                        });
                }
                else {
                        sPref1 = getSharedPreferences("MyPref1", MODE_PRIVATE);
                        String savedText1 = sPref1.getString(SAVED_TEXT1, "");
                        sPref2 = getSharedPreferences("MyPref2", MODE_PRIVATE);
                        String savedText2 = sPref2.getString(SAVED_TEXT2, "");
                        if(savedText1.length() > 0 && savedText2.length()>0) {
                                usd.setText(savedText1);
                                eur.setText(savedText2);
                        }
                        Toast toast = Toast.makeText(getApplicationContext(), "No internet connection!", Toast.LENGTH_SHORT);
                        toast.show();
                }
        }
        private boolean isNetworkConnected() {
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
        }

        private class MyTask extends AsyncTask<Void,Void,ArrayList<String>> {
                @Override
                protected ArrayList<String> doInBackground(Void... params) {
                        ArrayList<String> array = new ArrayList<String>();
                        String url = "https://www.cbr-xml-daily.ru/daily_json.js";
                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                        String USD = null;
                                        String EUR = null;
                                        try {
                                                JSONObject jsonObject = response.getJSONObject("Valute");
                                                JSONObject jsonObject1 = jsonObject.getJSONObject("USD");
                                                USD = jsonObject1.getString("Value");
                                                JSONObject jsonObject2 = jsonObject.getJSONObject("EUR");
                                                EUR = jsonObject2.getString("Value");
                                        } catch (JSONException e) {
                                                e.printStackTrace();
                                        }
                                        usd.setText(USD);
                                        eur.setText(EUR);
                                        sPref1 = getSharedPreferences("MyPref1", MODE_PRIVATE);
                                        SharedPreferences.Editor ed1 = sPref1.edit();
                                        ed1.putString(SAVED_TEXT1, usd.getText().toString());
                                        ed1.commit();
                                        sPref2 = getSharedPreferences("MyPref2", MODE_PRIVATE);
                                        SharedPreferences.Editor ed2 = sPref2.edit();
                                        ed2.putString(SAVED_TEXT2, eur.getText().toString());
                                        ed2.commit();
                                }
                        } ,new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                        error.printStackTrace();
                                }
                        });
                        mQueue.add(request);
                        return array;
                }
        }
}

