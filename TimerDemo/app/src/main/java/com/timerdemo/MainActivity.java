package com.timerdemo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        final Handler handler = new Handler();
//        Runnable runnable= new Runnable() {
//
//            @Override
//            public void run() {
//                // insert code to be run
//
//                Log.i("Runnable has run!", "a second must have passed...");
//                handler.postDelayed(this, 1000);
//            }
//        };
//        handler.post(runnable);

        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // countdown is counting down (every second)
                Log.i("Seconds left", String.valueOf(millisUntilFinished /1000));
            }

            @Override
            public void onFinish() {
                // Counter is finished (after 10 seconds)
                Log.i("Done", "Countdown Timer Finished");
            }
        }.start();
    }
}
