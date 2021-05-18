package com.example.app.activities;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.os.Build;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.app.fragments.SignIn;
import com.example.app.fragments.SignedIn;
import com.example.app.R;
import com.example.app.services.BroadCastReceiver;

public class Main extends AppCompatActivity {
    FrameLayout container;
    FragmentManager fragment_manager;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void UpdateService(){
        JobScheduler scheduler = (JobScheduler) getSystemService(getApplicationContext().JOB_SCHEDULER_SERVICE);
        boolean hasBeenScheduled = false;
        for ( JobInfo jobInfo : scheduler.getAllPendingJobs() ) {
            if ( jobInfo.getId() == 11 ) {
                hasBeenScheduled = true;
                break ;
            }
        }
        if (!hasBeenScheduled){
            new BroadCastReceiver().onReceive(this,getIntent().setAction("restart"));
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UpdateService();

        setContentView(R.layout.activity_main);

        container = (FrameLayout) findViewById(R.id.container);
        fragment_manager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = fragment_manager.beginTransaction();
            fragmentTransaction.add(R.id.container, new SignIn());
            fragmentTransaction.commit();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDestroy() {
        UpdateService();
        super.onDestroy();
}


}