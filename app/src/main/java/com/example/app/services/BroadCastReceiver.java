package com.example.app.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.JobIntentService;

public class BroadCastReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("SERVICE STARTED");
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED) || intent.getAction().equals("restart")){
            JobInfo job = new JobInfo.Builder(11, new ComponentName(context, PassiveChecker.class))
                    .setBackoffCriteria(1000*60*10,JobInfo.BACKOFF_POLICY_LINEAR)
                    .setPeriodic(1000 * 60*15)
                    .setPersisted(true)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .build();


            JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            scheduler.schedule(job);

        }

    }
}