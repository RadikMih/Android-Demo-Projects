package com.showandhideuielemnts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView helloWorld;

    public void show(View view) {
        helloWorld.setVisibility(View.VISIBLE);
    }

    public void hide(View view) {
        helloWorld.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloWorld = findViewById(R.id.helloWorldTextView);


    }
}
