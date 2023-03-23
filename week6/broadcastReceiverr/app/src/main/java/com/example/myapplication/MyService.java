package com.example.myapplication;

import static com.example.myapplication.Application.CHANNEL_ID;
//import static com.example.myapplication.Application.CHANNEl_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import org.jetbrains.annotations.NotNull;

public class MyService extends Service {
    private MediaPlayer mediaPlayer;
    private static final int ACTION_PAUSE = 1;
    private static final int ACTION_RESUME = 2;
    private static final int ACTION_CLEAR = 3;
    private boolean isPlaying;
    private Song song1;
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("qa", "start service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            Song song = (Song) bundle.get("object_song");
            if(song != null) {
                song1 = song;
                startMusic(song);
                sendNotification(song);
            }
        }

        int actionMusic = intent.getIntExtra("actionMusic_service", 0);
        handleActionMusic(actionMusic);

        return START_NOT_STICKY;
    }

    private void startMusic(Song song) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(),song.getResource());
        }
        mediaPlayer.start();
        isPlaying = true;
    }

    private  void handleActionMusic(int action) {
        switch (action) {
            case ACTION_PAUSE:
                pauseMusic();
                break;
            case ACTION_RESUME:
                resumeMusic();
                break;
            case ACTION_CLEAR:
                stopSelf();
                break;
        }
    }

    private void pauseMusic() {
        if(mediaPlayer != null && isPlaying) {
            mediaPlayer.pause();
            isPlaying = false;
            sendNotification(song1);
        }
    }

    private void resumeMusic() {
        if(mediaPlayer != null && !isPlaying) {
            mediaPlayer.start();
            isPlaying = true;
            sendNotification(song1);
        }
    }

    private void sendNotification(Song song) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), song.getImgae());
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.notification);
        remoteViews.setTextViewText(R.id.title, song.getTitle());
        remoteViews.setTextViewText(R.id.single, song.getSingle());
        remoteViews.setImageViewBitmap(R.id.imgsong,bitmap);

        remoteViews.setImageViewResource(R.id.imgPause, R.drawable.pause);
        if(isPlaying) {
            remoteViews.setOnClickPendingIntent(R.id.imgPause,getpendingIntent(this,ACTION_PAUSE));
            remoteViews.setImageViewResource(R.id.imgPause, R.drawable.play);
        } else {
            remoteViews.setOnClickPendingIntent(R.id.imgPause,getpendingIntent(this,ACTION_RESUME));
            remoteViews.setImageViewResource(R.id.imgPause, R.drawable.play);
        }
        remoteViews.setOnClickPendingIntent(R.id.imgPause,getpendingIntent(this,ACTION_CLEAR));
        Notification notification =
                new Notification.Builder(this, CHANNEL_ID)
                        .setCustomContentView(remoteViews)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentIntent(pendingIntent)
                        .build();

// Notification ID cannot be 0.
        startForeground(1, notification);
    }
    private PendingIntent getpendingIntent(@NotNull Context context, int action) {
        Intent intent = new Intent(this, Myreceiver.class);
        intent.putExtra("actionMusic", action);

        return PendingIntent.getBroadcast(context.getApplicationContext(), action, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("qa", "stop service");
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}