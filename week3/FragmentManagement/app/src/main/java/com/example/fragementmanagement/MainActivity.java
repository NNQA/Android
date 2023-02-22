package com.example.fragementmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.res.Configuration;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btnFrgment1);
        Button btn2 = findViewById(R.id.btnFrgment2);
        firstFragment ffg = new firstFragment();
        secondFragment sfg = new secondFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, sfg)
                .commit();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment, ffg)
                        .addToBackStack(null)
                        .commit();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment, sfg)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

}