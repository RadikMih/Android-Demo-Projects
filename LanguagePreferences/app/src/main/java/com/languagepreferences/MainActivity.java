package com.languagepreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.english) {
            setLanguage("English");
        } else if (item.getItemId() == R.id.spanish){
            setLanguage("Spanish");
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        sharedPreferences = this
                .getSharedPreferences("com.languagepreferences", Context.MODE_PRIVATE);
        String language = sharedPreferences.getString("language", "");

        if (language.equals("")) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_btn_speak_now)
                    .setTitle("Choose a language")
                    .setMessage("Which language would you like?")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLanguage("English");
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLanguage("Spanish");
                        }
                    })
                    .show();
        } else {
            textView.setText(language);
        }
    }

    private void setLanguage(String language) {
        sharedPreferences.edit().putString("language", language).apply();
        textView.setText(language);
    }
}
