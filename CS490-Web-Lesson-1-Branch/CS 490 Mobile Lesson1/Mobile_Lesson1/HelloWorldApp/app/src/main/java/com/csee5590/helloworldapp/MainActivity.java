package com.csee5590.helloworldapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText usernameCtrl = (EditText) findViewById(R.id.passwordText);
                EditText passwordCtrl = (EditText) findViewById(R.id.passwordText);
                String username = usernameCtrl.getText().toString();
                String password = passwordCtrl.getText().toString();

                boolean validationFlag = false;

                //verify username and password aren't empty
                if (!username.isEmpty() && !password.isEmpty()){
                    if (username.equals("Admin") && password.equals("Admin")){
                        validationFlag = true;
                    }

                }
                if (!validationFlag){

                } else {
                    Intent redirect = new Intent( MainActivity.this, WelcomeScreen.class);
                    startActivity(redirect);
                }
            }
        });

    }
}
