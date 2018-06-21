
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    EditText userName, password;
    TextView login;
    ImageView logo;
    RelativeLayout background;
    Boolean signUpModeActive = true;

    public void showUser(){
        Intent intent = new Intent(this,UserListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            signUp(v);
        }
        return false;
    }


    public void onClick(View view) {
        if (view.getId() == R.id.loginTextView) {
            Button signUpButton = (Button) findViewById(R.id.signUp);
            if (signUpModeActive) {
                signUpModeActive = false;
                signUpButton.setText("Log In");
                login.setText("or, Sign Up");
            } else {
                signUpModeActive = true;
                signUpButton.setText("Sign Up");
                login.setText("or, Log In");
            }
        } else if (view.getId() == R.id.backgroundLayout || view.getId() == R.id.logoImageView) {
            InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Instagram");

        userName = (EditText) findViewById(R.id.userNameEditText);
        password = (EditText) findViewById(R.id.passwordEditText);

        login = (TextView) findViewById(R.id.loginTextView);
        login.setOnClickListener(this);

        logo = (ImageView) findViewById(R.id.logoImageView);
        logo.setOnClickListener(this);
        background = (RelativeLayout) findViewById(R.id.backgroundLayout);
        background.setOnClickListener(this);
        password.setOnKeyListener(this);

        if (ParseUser.getCurrentUser() != null){
            showUser();
        }

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    public void signUp(View view) {
        if (userName.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(this, "Enter a valid username or password", Toast.LENGTH_SHORT).show();
        } else {

            if (signUpModeActive) {
                ParseUser user = new ParseUser();
                user.setUsername(userName.getText().toString());
                user.setPassword(password.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.v("Success", "You have signed up to this amazing app");
                            showUser();
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                ParseUser.logInInBackground(userName.getText().toString(), password.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e == null && user != null) {
                            Log.v("Log In", "Success");
                            showUser();
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }

    }


}