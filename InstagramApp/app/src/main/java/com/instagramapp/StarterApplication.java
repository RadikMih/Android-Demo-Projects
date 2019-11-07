package com.instagramapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;


public class StarterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("044971eacd8be18bbeb105f112c4fb9bc44d550c")
                .clientKey("7fb5d027fd5b0675e645cd5a18088a7f63ceaf80")
                .server("http://ec2-3-120-129-72.eu-central-1.compute.amazonaws.com:80/parse/")
                .build()
        );

       // ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}