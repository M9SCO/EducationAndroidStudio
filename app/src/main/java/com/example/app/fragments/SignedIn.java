package com.example.app.fragments;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.app.DialogSearchCity;
import com.example.app.activities.Main;
import com.example.app.methods.DataBase;
import com.example.app.methods.MyRequest;
import com.example.app.R;
import com.example.app.methods.NotificationUtils;
import com.example.app.models.Settings;
import com.example.app.models.SettingsDAO;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

public class SignedIn extends Fragment implements SwipeRefreshLayout.OnRefreshListener, Runnable, View.OnClickListener {

    final String SAVED_TEXT1 = "jsondata";
    private RequestQueue mQueue;
    private SharedPreferences sPref1;
    private View view;
    private SwipeRefreshLayout swipeContainer;
    private LinearLayout lv;
    private Button find_button;
    private AutoCompleteTextView find_text;
    public SignedIn() {
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
        sPref1 = getActivity().getSharedPreferences("jsondata", MODE_PRIVATE);
        lv =  getView().findViewById(R.id.fragment_signed_in_container);
        find_button = view.findViewById(R.id.button);
        find_text = view.findViewById(R.id.editTextTextPersonName);
        find_button.setOnClickListener(this);
        System.out.println("SISTEOUT WORKED");
        updateTexts();
    }

    private void updateTexts(){
        if (!isNetworkConnected()) {
            Toast toast = Toast.makeText(view.getContext(), "No internet connection!", Toast.LENGTH_SHORT);
            toast.show();

        } else if (DataBase.getInstance(getContext()).getDatabase().settingsDAO().getByKey("city") == null ) {
            Toast toast = Toast.makeText(view.getContext(), "Enter city name!", Toast.LENGTH_SHORT);
            toast.show();

        }

        else {
            SettingsDAO settingsDAO = DataBase.getInstance(getContext()).getDatabase().settingsDAO();
            Settings lat = settingsDAO.getByKey("lat");
            Settings lon = settingsDAO.getByKey("lon");
            find_text.setText(settingsDAO.getByKey("city").value);


            new MyRequest(mQueue).get_weather(sPref1, Float.parseFloat(lat.value), Float.parseFloat(lon.value));
            swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
            swipeContainer.setOnRefreshListener(this);
            lv.removeAllViews();
            String savedText1 = sPref1.getString(SAVED_TEXT1, "");
            try {
                JSONObject jsonObject = new JSONObject(savedText1);
                for(int i=0; i<=7;i++){
                    JSONObject data = (JSONObject) jsonObject.getJSONArray("daily").get(i);
                    FragmentTransaction mFragmentTransaction = getFragmentManager().beginTransaction();
                    mFragmentTransaction.add(R.id.fragment_signed_in_container, new WeatherTitle(data));
                    mFragmentTransaction.addToBackStack(null);
                    mFragmentTransaction.commit();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

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


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        FragmentManager manager = getFragmentManager();
        SharedPreferences spref = getActivity().getSharedPreferences("jsondatafindcity", MODE_PRIVATE);



        MyRequest request = new MyRequest(mQueue);
        request.findCity(spref, find_text.getText().toString());

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 10000ms
                DialogSearchCity myDialogFragment = new DialogSearchCity(spref);
                myDialogFragment.show(manager, "myDialog");

            }
        }, 2000);




    }





}