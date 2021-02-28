package com.rku_18fotca11002.tutorial06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this,Welcome.class));
                finish();
            }
        };
        Timer timer =new Timer();
        timer.schedule(timerTask, 1500);
    }
}