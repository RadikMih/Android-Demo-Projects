package com.parsetechniques;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        ParseObject score = new ParseObject("Score");
        score.put("username", "Radik");
        score.put("score", 86);

        score.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i("SaveInBackGround", "Successful");
                } else {
                    Log.i("SaveInBackGround", "Failed " + e.toString());
                }
            }
        });


        ParseObject tweet = new ParseObject("Tweet");
        tweet.put("username", "Radik");
        tweet.put("message", "Hello World");

        tweet.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i("Saved Tweet", "Successful");
                } else {
                    Log.i("Saved Tweet", "Failed");
                }
            }
        });

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
        query.getInBackground("xxl7na2JET", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null && object != null) {
                    object.put("score", 200);
                    object.saveInBackground();

                    Log.i("ObjectValue", object.getString("username"));
                    Log.i("ObjectValue", Integer.toString(object.getInt("score")));
                } else {
                    Log.i("ObjectValue", "Failed " + e.toString());
                }
            }
        });

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tweet");
        query.getInBackground("YDIAeGCwTP", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null && object != null) {
                    Log.i("Tweet values",object.getString("username")
                                    + " " + object.getString("message"));

                    object.put("message", "Updated Hello World");
                    object.saveInBackground();

                    Log.i("Tweet values",object.getString("username")
                                    + " " + object.getString("message"));
                } else {
                    Log.i("Tweet values", "Failed " + e.toString());
                }
            }
        });
*/
/*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
       // query.whereEqualTo("username", "Radik");
       // query.setLimit(1);
        query.whereGreaterThan("score", 200);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.i("findInBackground", "Retrieved " + objects.size());
                    if (objects.size() > 0) {
                        for (ParseObject object : objects) {
                            Log.i("Item", Integer.toString(object.getInt("score")));
                            object.put("score", object.getInt("score") + 50);
                            object.saveInBackground();
                            Log.i("Item", Integer.toString(object.getInt("score")));
                        }
                    }
                }
            }
        });
*/
/*
        ParseUser user = new ParseUser();

        user.setUsername("Radik");
        user.setPassword("123");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i("Sign Up", "Successful");
                } else {
                    Log.i("Sign Up", "Failed");
                }
            }
        });
*/
/*
        ParseUser.logInInBackground("Radik", "123", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Log.i("Login", "Successful");
                } else {
                    Log.i("Login", "Failed " + e.toString());
                }
            }
        });

         */
        ParseUser.logOut();
        if (ParseUser.getCurrentUser() != null) {
            Log.i("Current User", "Logged In User " + ParseUser.getCurrentUser().getUsername());
        } else {
            Log.i("Current User", "User not logged in");
        }



        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}
