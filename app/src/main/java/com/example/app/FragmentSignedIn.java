package com.example.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import static android.content.Context.MODE_PRIVATE;

public class FragmentSignedIn extends Fragment implements SwipeRefreshLayout.OnRefreshListener, Runnable, Response.Listener<JSONObject>, Response.ErrorListener {

    final String SAVED_TEXT1 = "saved_text";
    final String SAVED_TEXT2 = "saved_text";
    TextView usd;
    TextView eur;
    SharedPreferences sPref1;
    SharedPreferences sPref2;
    private RequestQueue mQueue;
    private SwipeRefreshLayout swipeContainer;

    public FragmentSignedIn() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signed_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mQueue = Volley.newRequestQueue(view.getContext());
        usd = (TextView) view.findViewById(R.id.textView9);
        eur = (TextView) view.findViewById(R.id.textView11);
        usd.setText("  .    ");
        eur.setText("  .    ");
        if (isNetworkConnected()) {
            getfromAPI();
            swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
            swipeContainer.setOnRefreshListener(this);
        } else {
            sPref1 = getActivity().getSharedPreferences("MyPref1", MODE_PRIVATE);
            String savedText1 = sPref1.getString(SAVED_TEXT1, "");
            sPref2 = getActivity().getSharedPreferences("MyPref2", MODE_PRIVATE);
            String savedText2 = sPref2.getString(SAVED_TEXT2, "");
            if (savedText1.length() > 0 && savedText2.length() > 0) {
                usd.setText(savedText1);
                eur.setText(savedText2);
            }
            Toast toast = Toast.makeText(view.getContext(), "No internet connection!", Toast.LENGTH_SHORT);
            toast.show();

        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(this, 1000); // Delay in millis
    }


    protected ArrayList<String> getfromAPI(Void... params) {
        ArrayList<String> array = new ArrayList<String>();
        String url = "https://www.cbr-xml-daily.ru/daily_json.js";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url, null, this, this);
        mQueue.add(request);
        return array;
    }

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
        sPref1 = getActivity().getSharedPreferences("MyPref1", MODE_PRIVATE);
        SharedPreferences.Editor ed1 = sPref1.edit();
        ed1.putString(SAVED_TEXT1, usd.getText().toString());
        ed1.commit();
        sPref2 = getActivity().getSharedPreferences("MyPref2", MODE_PRIVATE);
        SharedPreferences.Editor ed2 = sPref2.edit();
        ed2.putString(SAVED_TEXT2, eur.getText().toString());
        ed2.commit();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
    }

    @Override
    public void run() {
        if (isNetworkConnected()) {
            usd.setText("  .    ");
            eur.setText("  .    ");
            getfromAPI();
        } else {
            Toast toast = Toast.makeText(getView().getContext(), "No internet connection!", Toast.LENGTH_SHORT);
            toast.show();
        }
        swipeContainer.setRefreshing(false);
    }


}