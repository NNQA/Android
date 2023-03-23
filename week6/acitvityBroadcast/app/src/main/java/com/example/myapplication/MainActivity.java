package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button buttonstr, buttonstop;
    private RelativeLayout relativeLayout;
    private ImageView imgsong, imgPlayOrPause, imgClear;
    private TextView txtT, txtS;
    private Song song1;
    private boolean isPlaying;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if(bundle == null) {
                return;
            }
            song1 = (Song) bundle.get("object_action");
            isPlaying = (boolean) bundle.get("status_player");
            int action = (int) bundle.get("action_music");
            handleLayoutMusic(action);
        }


    };
    private void handleLayoutMusic(int action) {
        switch (action) {
            case 4:
                relativeLayout.setVisibility(View.VISIBLE);
                ShowinForSong();
                setStatus();
                break;
            case 1:
                setStatus();
                break;
            case 2:
                setStatus();
                break;
            case 3:
                relativeLayout.setVisibility(View.GONE);
                break;
        }

    }
    private  void setStatus() {
        if(isPlaying) {
            imgPlayOrPause.setImageResource(R.drawable.pause);
        }
        else {
            imgPlayOrPause.setImageResource(R.drawable.play);
        }
    }
    private void ShowinForSong() {
        if(song1 == null) {
            return;
        }
        imgsong.setImageResource(song1.getImgae());
        txtT.setText(song1.getTitle());
        txtT.setText(song1.getSingle());

        imgPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlaying) {
                    sendAction(1);
                } else {
                    sendAction(2);
                }
            }
        });
        imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAction(3);
            }
        });
    }
    private void sendAction(int action) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("actionMusic_service", action);
        startService(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("send_data"));

        buttonstr = findViewById(R.id.stser);
        buttonstop = findViewById(R.id.stpser);

        relativeLayout = findViewById(R.id.layout_bottom);
        imgsong = findViewById(R.id.imgsong);
        imgPlayOrPause = findViewById(R.id.imgPause);
        imgClear = findViewById(R.id.imgClear);
        txtT = findViewById(R.id.title);
        txtS = findViewById(R.id.single);


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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }
}