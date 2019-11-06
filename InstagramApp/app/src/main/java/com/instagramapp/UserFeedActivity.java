package com.instagramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class UserFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);

        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        ImageView imageView = new ImageView(getApplicationContext());

        imageView.setLayoutParams(new ViewGroup.LayoutParams(
             ViewGroup.LayoutParams.MATCH_PARENT,
             ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        imageView.setImageDrawable(getResources().getDrawable(R.drawable.logo));
        linearLayout.addView(imageView);





    }
}
