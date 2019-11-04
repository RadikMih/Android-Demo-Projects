package com.instagramcopy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseAnalytics;

public class MainActivity extends AppCompatActivity {
    //
//    Boolean signUpModeActive = true;
//    TextView changeSignUpModeTextView;
//    EditText passwordEditText;
//
//    @Override
//    public void onClick(View view) {
//        if (view.getId() == R.id.changeSignupModeTextView) {
//            Button singUpButton = findViewById(R.id.signupButton);
//            if (signUpModeActive) {
//                signUpModeActive = false;
//                singUpButton.setText("Log In");
//                changeSignUpModeTextView.setText("Or Sign Up");
//            } else {
//                signUpModeActive = true;
//                singUpButton.setText("Sign Up");
//                changeSignUpModeTextView.setText("or Log In");
//            }
//
//
//        }
//        // Log.i("AppInfo", "Change SignUp Mode");
//    }
//
//    @Override
//    public boolean onKey(View view, int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
//            signUp(view);
//        }
//
//
//
//
//        return false;
//    }
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

//
//        changeSignUpModeTextView = findViewById(R.id.changeSignupModeTextView);
//        changeSignUpModeTextView.setOnClickListener(this);
//
//
//
//
//        passwordEditText = findViewById(R.id.passwordEditText);
//        passwordEditText.setOnKeyListener(this);
//
    }
//
//    public void signUp(View view) {
//        EditText usernameEditText = findViewById(R.id.usernameEditText);
//
//        String username = usernameEditText.getText().toString();
//        String password = passwordEditText.getText().toString();
//
//        if (username.equals("") || password.equals("")) {
//            Toast.makeText(
//                    this,
//                    "A username and password are required",
//                    Toast.LENGTH_LONG)
//                    .show();
//        } else {
//            if (signUpModeActive) {
//                ParseUser user = new ParseUser();
//                user.setUsername(username);
//                user.setPassword(password);
//            } else {
//                // TODO Login user


    //           }


//        }
//    }


}
