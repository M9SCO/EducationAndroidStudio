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

public class FragmentSignedIn extends Fragment implements SwipeRefreshLayout.OnRefreshListener, Runnable{

    final String SAVED_TEXT1 = "saved_text";
    final String SAVED_TEXT2 = "saved_text";
    private TextView usd;
    private TextView eur;
    private RequestQueue mQueue;
    private SharedPreferences sPref1;
    private SharedPreferences sPref2;
    private View view;
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
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        view = v;
        mQueue = Volley.newRequestQueue(view.getContext());
        usd = (TextView) view.findViewById(R.id.textView9);
        eur = (TextView) view.findViewById(R.id.textView11);
        sPref1 = getActivity().getSharedPreferences("MyPref1", MODE_PRIVATE);
        sPref2 = getActivity().getSharedPreferences("MyPref2", MODE_PRIVATE);

        updateTexts();
    }

    private void updateTexts(){
        usd.setText("  .    ");
        eur.setText("  .    ");

        if (isNetworkConnected()) {
            new MyRequest(mQueue, sPref1, sPref2);
            swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
            swipeContainer.setOnRefreshListener(this);
        } else {
            Toast toast = Toast.makeText(view.getContext(), "No internet connection!", Toast.LENGTH_SHORT);
            toast.show();

        }
        String savedText1 = sPref1.getString(SAVED_TEXT1, "");
        String savedText2 = sPref2.getString(SAVED_TEXT2, "");
        if (savedText1.length() > 0 && savedText2.length() > 0) {
            usd.setText(savedText1);
            eur.setText(savedText2);
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


    @Override
    public void run() {
        updateTexts();
        swipeContainer.setRefreshing(false);
    }


}