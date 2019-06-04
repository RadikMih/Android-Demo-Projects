package com.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekBar;
    TextView timerTextView;
    Boolean counterIsActive = false;
    Button controllerButton;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerTextView = findViewById(R.id.timerTextView);
        controllerButton = findViewById(R.id.controllerButton);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void controlTimer(View view) {
        if (!counterIsActive) {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            controllerButton.setText("Stop");

            timer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 80, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();
                    resetTimer();
                }
            }.start();
        } else {
            resetTimer();
        }
    }

    public void resetTimer () {
        timerTextView.setText("00:30");
        timerSeekBar.setEnabled(true);
        timerSeekBar.setProgress(30);
        timer.cancel();
        controllerButton.setText("GO!");
        counterIsActive = false;
    }

    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String minutesString = Integer.toString(minutes);
        if (minutes < 10) {
            minutesString = "0" + minutesString;
        }


        String secondsString = Integer.toString(seconds);
        if (seconds < 10) {
            secondsString = "0" + secondsString;
        }

        timerTextView.setText(minutesString + ":" + secondsString);
    }

}
