package com.example.module4app1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView brazil = findViewById(R.id.brazil);
        brazil.setTranslationX(-1000);
        brazil.setTranslationY(-1000);
    }

    public void fade(View view) {
        ImageView brazil = findViewById(R.id.brazil);
        //ImageView newImage = findViewById(R.id.newImage);


        // ------ Make visible or invisible
        //alpha make item transparent. 0 is none, 1 is visible
        // brazil.animate().alpha(0f).setDuration(2000); // mili seconds
        //newImage.animate().alpha(1f).setDuration(5000);

        // ------------ Move to the side
        //brazil.animate().translationXBy(1000).setDuration(2000);
        //brazil.animate().translationYBy(1000).setDuration(2000);

        // ----------- Rotation
        // brazil.animate().rotation(180).setDuration(2000);

        // ------------- Make big or small
//        brazil.animate().scaleX(0.5f).scaleY(0.5f).setDuration(2000);
//        brazil.animate().scaleX(1).scaleY(1).setDuration(8000);


        brazil.animate()
                .translationXBy(1000)
                .translationYBy(1000)
                .rotation(3600)
                .setDuration(3000);


    }

}
