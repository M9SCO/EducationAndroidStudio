package com.example.app;

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
    private RequestQueue mQueue;
    private SharedPreferences sPref1;
    private SharedPreferences sPref2;


    public MyRequest(RequestQueue mQ, SharedPreferences spref1, SharedPreferences spref2){
        mQueue = mQ;
        sPref1 = spref1;
        sPref2 = spref2;
        GetFromAPI();

    }

    protected ArrayList<String> GetFromAPI(Void... params) {
        ArrayList<String> array = new ArrayList<String>();
        String url = "https://www.cbr-xml-daily.ru/daily_json.js";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url, null, this, this);
        mQueue.add(request);
        return array;}

    @Override
    public void onResponse(JSONObject response) {
        String USD = null;
        String EUR = null;
        try {
            JSONObject jsonObject = response.getJSONObject("Valute");

            JSONObject jsonObject1 = jsonObject.getJSONObject("USD");
            JSONObject jsonObject2 = jsonObject.getJSONObject("EUR");

            USD = jsonObject1.getString("Value");
            EUR = jsonObject2.getString("Value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences.Editor ed1 = sPref1.edit();
        SharedPreferences.Editor ed2 = sPref2.edit();

        ed1.putString("saved_text", EUR);
        ed2.putString("saved_text", USD);

        ed1.commit();
        ed2.commit();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
    }


}
