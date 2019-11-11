package com.twitterapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    public void redirectUser() {
        if (ParseUser.getCurrentUser() != null) {
            Intent intent = new Intent(getApplicationContext(), FollowActivity.class);
            startActivity(intent);
        }
    }

    public void signUpLogin(View view) {
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        final String username = usernameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null) {
                    Log.i("Info", "Logged in");
                    redirectUser();
                } else {
                    ParseUser parseUser = new ParseUser();
                    parseUser.setUsername(username);
                    parseUser.setPassword(password);

                    parseUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Log.i("Info", "Signed up");
                                redirectUser();
                            } else {
                                Toast.makeText(
                                        MainActivity.this,
                                        e.getMessage().substring(e.getMessage().indexOf(" ")),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        }
                    });
                }
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Twitter: Login");
        redirectUser();

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}
