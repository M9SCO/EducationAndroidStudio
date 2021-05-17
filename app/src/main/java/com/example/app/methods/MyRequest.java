package com.example.app.methods;

import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MyRequest implements Response.Listener<JSONObject>, Response.ErrorListener  {
    private final static String appid = "0434a8be71c216e6cdd03ba4eeffcbb3";
    private final static String units = "metric";

    private final static String URL_SEARCH = "https://api.openweathermap.org/data/2.5/find";
    private final static String URL_ONECALL = "https://api.openweathermap.org/data/2.5/onecall";


    private final RequestQueue mQueue;
    private SharedPreferences s_pref;


    public MyRequest(RequestQueue mQ){
        mQueue = mQ;

    }

    public void findCity(SharedPreferences sPref, String q){
        String url = URL_SEARCH+"?q="+q;
        send_request(sPref, url);
    }

    public void get_weather(SharedPreferences sPref, float lat, float lon){
        String url = URL_ONECALL+"?lat="+lat+"&lon="+lon;
        send_request(sPref, url);

    }

    private void send_request(SharedPreferences sPref, String url){
        s_pref = sPref;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url+"&appid="+appid+"&units="+units, null, this, this);
        System.out.println(request.getUrl());
        mQueue.add(request);

    }


    @Override
    public void onResponse(JSONObject response) {
        SharedPreferences.Editor editor_s_pref = s_pref.edit();
        System.out.println(response.toString());

        editor_s_pref.putString("jsondata", response.toString());
        editor_s_pref.apply();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println("ERRROR RESPONCE");

        error.printStackTrace();
    }


}
