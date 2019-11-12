package com.whatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    Boolean loginModeActive = false;

    public void redirectIfLoggedIn() {
        if (ParseUser.getCurrentUser() != null) {
            Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
            startActivity(intent);
        }
    }

    public void toggleLoginMode(View view) {

        Button loginSignUpButton = findViewById(R.id.signUpButton);
        TextView toggleLoginModeTextView = findViewById(R.id.toggleLoginModeTextView);

        if (loginModeActive) {
            loginModeActive = false;
            loginSignUpButton.setText("Sign Up");
            toggleLoginModeTextView.setText("or Log in");
        } else {
            loginModeActive = true;
            loginSignUpButton.setText("Log In");
            toggleLoginModeTextView.setText("or Sign Up");
        }

    }

    public void signUpLogin(View view) {

        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (loginModeActive) {
            ParseUser.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (e == null) {
                        Log.i("Info", "User logged in");
                        redirectIfLoggedIn();
                    } else {
                        e.printStackTrace();
                        Toast.makeText(
                                MainActivity.this,
                                getExceptionMessage(e),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                }
            });
        } else {
            ParseUser user = new ParseUser();
            user.setUsername(username);
            user.setPassword(password);
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.i("Info", "user signed up");
                        redirectIfLoggedIn();
                    } else {
                        e.printStackTrace();
                        Toast.makeText(
                                MainActivity.this,
                                getExceptionMessage(e),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                }
            });
        }
    }

    private String getExceptionMessage(ParseException e) {
        String message = e.getMessage();
        if (message.toLowerCase().contains("java")) {
            message = e.getMessage().substring(e.getMessage().indexOf(" "));
        }
        return message;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("WhatsApp Login");
        redirectIfLoggedIn();
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}
