package com.instagramapp;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    Boolean signUpModeActive = true;
    TextView changeSignUpModeTextView;
    EditText passwordEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeSignUpModeTextView = findViewById(R.id.changeSignupModeTextView);
        changeSignUpModeTextView.setOnClickListener(this);

        passwordEditText = findViewById(R.id.passwordEditText);
        passwordEditText.setOnKeyListener(this);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    public void signUp(View view) {
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.matches("") || password.matches("")) {
            Toast.makeText(
                    this,
                    "A username and password are required",
                    Toast.LENGTH_LONG)
                    .show();
        } else {
            if (signUpModeActive) {
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.i("Sign Up", "Successful");
                        } else {
                            Toast.makeText(MainActivity.this,
                                    e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });

            } else {
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Log.i("Sign Up", "Login Successful");
                        } else {
                            Toast.makeText(MainActivity.this,
                                    e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.changeSignupModeTextView) {
            Button singUpButton = findViewById(R.id.signUpButton);
            Log.i("AppInfo", "Change SignUp Mode");
            if (signUpModeActive) {
                signUpModeActive = false;
                singUpButton.setText("Log In");
                changeSignUpModeTextView.setText("Or Sign Up");
            } else {
                signUpModeActive = true;
                singUpButton.setText("Sign Up");
                changeSignUpModeTextView.setText("or Log In");
            }
        }
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            signUp(view);
        }
        return false;
    }
}