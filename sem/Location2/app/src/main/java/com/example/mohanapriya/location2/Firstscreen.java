package com.example.mohanapriya.location2;

import android.app.Activity;
import android.content.Intent;
import android.javapapers.com.androidgeocodelocation.R;
import android.os.Bundle;

public class Firstscreen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstscreen);

        Thread logoTimer = new Thread() {
            public void run(){
                try{
                    int logoTimer = 0;
                    while(logoTimer < 5000){
                        sleep(100);
                        logoTimer = logoTimer +100;
                    }
                    Intent nxt = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(nxt);
                }

                catch (InterruptedException e) {

                    e.printStackTrace();
                }

                finally{
                    finish();
                }
            }
        };

        logoTimer.start();
    }
}



