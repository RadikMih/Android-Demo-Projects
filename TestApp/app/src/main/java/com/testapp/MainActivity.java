package com.testapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        final SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        String oldItem = preferences.getString("oldItem", "Nothing created yet...");

        textView.setText(oldItem);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("oldItem", textView.getText().toString());
                editor.apply();

                Animation animation = new AlphaAnimation(1.0f, 0.0f);
                animation.setDuration(1000);
                button.startAnimation(animation);

                Log.d("DEBUG", "Button clicked");
            }
        });


    }
}
