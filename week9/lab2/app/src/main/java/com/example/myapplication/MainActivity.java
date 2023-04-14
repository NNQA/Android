package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText t1, t2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = findViewById(R.id.button);
        DatabaseReference temp = FirebaseDatabase.getInstance().getReference().child("test");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playsong();
            }
        });
    }
    public void playsong() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/sacred-courier-380213.appspot.com/o/1199342751146922891.mp4?alt=media&token=19618f37-2a7c-411d-8163-2a50182de952");
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.prepare();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}