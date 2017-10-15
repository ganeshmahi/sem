package com.example.csh.musicbackground;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;
    AssetFileDescriptor afd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
// Read the music file from the asset folder
            //afd = getAssets().openFd(“lab.mp3”);
            afd = getAssets().openFd("lab.mp3");
// Creation of new media player;
            player = new MediaPlayer();
// Set the player music source.
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),afd.getLength());
// Set the looping and play the music.
            player.setLooping(true);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.start();
    }
}
