package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button buttonstr, buttonstop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edtext);
        buttonstr = findViewById(R.id.stser);
        buttonstop = findViewById(R.id.stpser);

        buttonstr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickStartService();
            }
        });
        buttonstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickStopService();
            }
        });

    }

    private void clickStartService() {
        Song song = new Song("thuong em", "qa",R.drawable.qa,R.raw.iuem);
        Intent intent = new Intent(this, MyService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_song", song);

        intent.putExtras(bundle);

        startService(intent);
    }

    private void  clickStopService() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }
}