package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

public class Application extends android.app.Application {
    public static final  String CHANNEL_ID = "QA";
    @Override
    public void onCreate() {
        super.onCreate();
        createChannel();
    }

    private void createChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.e("aaa","aaa");
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "channel service example", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setSound(null, null);
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                Log.e("aaa","aaa");
                manager.createNotificationChannel(channel);
            }
        }
    }
}
