package com.example.app;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class ServicePassiveChecker extends JobIntentService {

    static final int JOB_ID = 1000;
    final Handler mHandler = new Handler();


    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
        SharedPreferences sPref1 = getSharedPreferences("MyPref1", MODE_PRIVATE);
        SharedPreferences sPref2 = getSharedPreferences("MyPref2", MODE_PRIVATE);
        new MyRequest(mQueue, sPref1, sPref2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        toast("All work complete");}


    // Helper for showing tests
    void toast(final CharSequence text) {
        mHandler.post(new Runnable() {
            @Override public void run() {
                Toast.makeText(ServicePassiveChecker.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

}