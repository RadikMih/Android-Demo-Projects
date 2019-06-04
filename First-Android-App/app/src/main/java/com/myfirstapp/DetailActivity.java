package com.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = DetailActivity.this.getIntent();
        String modelName = "";

        if (intent != null && intent.hasExtra("model_name")) {
            modelName = intent.getStringExtra("model_name");
        }

        TextView textView = (TextView)this.findViewById(R.id.text_view_name);
        textView.setText(modelName);





    }
}
