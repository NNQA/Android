package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Myreceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int actionMusic = intent.getIntExtra("actionMusic", 0);

        Intent intentservice = new Intent(context, MyService.class);
        intentservice.putExtra("actionMusic_service",actionMusic );

        context.startService(intentservice);
    }
}
