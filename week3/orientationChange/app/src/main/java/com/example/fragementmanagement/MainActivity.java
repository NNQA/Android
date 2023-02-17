package com.example.fragementmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super .onConfigurationChanged(configuration);

        if(configuration.orientation == configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

}