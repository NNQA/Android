package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class broadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            boolean noconnect = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            );
            if(noconnect) {
                Toast.makeText(context, "disconnect", Toast.LENGTH_SHORT).show();
            }
            else  {
                Toast.makeText(context, "connected", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
