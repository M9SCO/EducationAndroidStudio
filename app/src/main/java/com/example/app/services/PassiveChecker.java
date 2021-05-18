package com.example.app.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.app.methods.DataBase;
import com.example.app.methods.MyRequest;
import com.example.app.methods.NotificationUtils;
import com.example.app.methods.ParseWeather;
import com.example.app.models.MyAbstractDataBase;
import com.example.app.models.Settings;
import com.example.app.models.SettingsDAO;

import org.json.JSONException;
import org.json.JSONObject;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class PassiveChecker extends JobService {

    final Handler mHandler = new Handler();
    NotificationUtils noticator;

    void toast(final CharSequence text) {
        mHandler.post(new Runnable() {
            @Override public void run() {
                Toast.makeText(PassiveChecker.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        return getdata(this);
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    boolean getdata(Context context){
        System.out.println("SERVICE RUN");

        toast("RUNNING SERVICE");
        RequestQueue mQueue = Volley.newRequestQueue(context);
        noticator = new NotificationUtils(this);
        SharedPreferences sPref1 = getSharedPreferences("jsondatagetnow", MODE_PRIVATE);
        SettingsDAO settingsDAO = DataBase.getInstance(context).getDatabase().settingsDAO();
        Settings lat = settingsDAO.getByKey("lat");
        Settings lon = settingsDAO.getByKey("lon");
        Settings city = settingsDAO.getByKey("city");

        if(lat != null && lon != null){
            new MyRequest(mQueue).get_weather(sPref1, Float.parseFloat(lat.value), Float.parseFloat(lon.value));
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        ParseWeather weather = new ParseWeather(new JSONObject(sPref1.getString("jsondata", "")));
                        weather.toCurrent();
                        noticator.sendAndroidNotification(city.value+": "+ weather.temp, weather.weather_desc,
                                BitmapFactory.decodeResource(context.getResources(), weather.idIcon)
                                );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, 3000);
    }
        return true;
}}