package com.example.auntification;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.auntification.ui.login.LoginViewModel;

public class SessionSignInActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    EditText writing_username;
    EditText writing_password;
    Button sign_in;
    Button sign_up;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_sign_in);
        writing_username = (EditText) findViewById(R.id.username);
        writing_password = (EditText) findViewById(R.id.password);
        sign_in = (Button) findViewById(R.id.login);
        sign_up = (Button) findViewById(R.id.register);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String write_username = writing_username.getText().toString();
                String write_password = writing_password.getText().toString();
                if (KnownUsers.get_pass(write_username, write_password, getApplicationContext())) {
                    startActivity(new Intent(SessionSignInActivity.this, SessionActivity.class));
                } else {
                    startActivity(new Intent(SessionSignInActivity.this, SessionSignInFail.class));
                }
            }
        });



        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SessionSignInActivity.this, SessionSignUpActivity.class));
            }
        });

    }
}

