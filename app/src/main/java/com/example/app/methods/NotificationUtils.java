package com.example.app.methods;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;

import com.example.app.R;
import com.example.app.activities.Main;

public class NotificationUtils extends ContextWrapper {
    private NotificationManager mManager;
    private Context context;
    public static final String ANDROID_CHANNEL_ID = "com.chikeandroid.tutsplustalerts.ANDROID";
    public static final String IOS_CHANNEL_ID = "com.chikeandroid.tutsplustalerts.IOS";
    public static final String ANDROID_CHANNEL_NAME = "ANDROID CHANNEL";
    public static final String IOS_CHANNEL_NAME = "IOS NAME";
    public NotificationUtils(Context base) {
        super(base);
        context = base;
        createChannels();
    }
    public void createChannels() {
        NotificationChannel androidChannel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID,
                    ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            androidChannel.enableLights(true);
            androidChannel.enableVibration(true);
            androidChannel.setLightColor(Color.GREEN);
            androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            getManager().createNotificationChannel(androidChannel);
            NotificationChannel iosChannel = new NotificationChannel(IOS_CHANNEL_ID,
                    IOS_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            iosChannel.enableLights(true);
            iosChannel.enableVibration(true);
            iosChannel.setLightColor(Color.GRAY);
            iosChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            getManager().createNotificationChannel(iosChannel);

        }

    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public Notification.Builder getAndroidChannelNotification(String title, String body) {
        return new Notification.Builder(getApplicationContext(), ANDROID_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true);
    }

    public Notification.Builder getIosChannelNotification(String title, String body) {
        return new Notification.Builder(getApplicationContext(), IOS_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true);
    }

    public Void sendAndroidNotification(String title, String text, Bitmap bitmap){
        Notification.Builder nb = getAndroidChannelNotification(title, text);
        final PendingIntent pendingIntent = PendingIntent.getActivity(
                context, 1, new Intent(context, Main.class), PendingIntent.FLAG_UPDATE_CURRENT);
        nb.setAutoCancel(true).setWhen(System.currentTimeMillis())
                              .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
        ;


        if (bitmap != null) {
            nb.setLargeIcon(bitmap);
        }
        getManager().notify(101, nb.build());
        return null;
    }


}
