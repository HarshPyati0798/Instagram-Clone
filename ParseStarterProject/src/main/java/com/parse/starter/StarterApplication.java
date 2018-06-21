/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;


public class StarterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("de6f84d5c4a96f607dd51d7f791d871cf91605b4")
                .clientKey("66159af19bef585f7b4e8c198de1d79b28f157cc")
                .server("http://18.191.136.131:80/parse/")
                .build()
        );

        /*ParseUser user = new ParseUser();
        user.setUsername("harshvivek802");
        user.setPassword("reverseflash123@");
        user.setEmail("harshvivek802@gmail.com");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Log.v("Success","We were able to sign up");
                }else {
                    e.printStackTrace();
                }
            }
        });
        ParseUser.logInInBackground("harshvivek802", "reverseflash123@", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null){
                    Log.v("Success","You are logged in");
                }else {
                    e.printStackTrace();
                }
            }
        });

        ParseUser.logOut();
        if (ParseUser.getCurrentUser() != null){
            Log.v("Success", ParseUser.getCurrentUser().getUsername());
        }else {
            Log.v("Failure","You are not logged in");
        }
         */
        //ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}
