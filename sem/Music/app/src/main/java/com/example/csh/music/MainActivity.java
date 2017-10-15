package com.example.csh.music;


import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    MediaPlayer mp        = null;
    String hello         = "Hello!";
    String goodbye        = "GoodBye!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonHello = (Button) findViewById(R.id.idHello);
        buttonHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managerOfSound(hello);
            }
        });


        final Button buttonGoodBye = (Button) findViewById(R.id.idGoodBye);
        buttonGoodBye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managerOfSound(goodbye);
            }
        });
    }


    protected void managerOfSound(String theText) {
        if (mp != null) {
            mp.reset();
            mp.release();
        }
        if (theText == hello)
            mp = MediaPlayer.create(this, R.raw.hello);
        else if (theText == goodbye)
            mp = MediaPlayer.create(this, R.raw.goodbye);
        mp.start();
    }
}
