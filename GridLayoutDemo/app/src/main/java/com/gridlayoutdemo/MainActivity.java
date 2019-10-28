package com.gridlayoutdemo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonTapped(View view) {
        int id = view.getId();
        String ourID = view.getResources().getResourceEntryName(id);

        int resourceID = getResources().
                getIdentifier(ourID, "raw", "com.gridlayoutdemo");

        MediaPlayer mplayer = MediaPlayer.create(this, resourceID);
        mplayer.start();


        Log.i("button tapped", ourID);
    }

}
