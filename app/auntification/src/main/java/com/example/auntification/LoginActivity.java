package com.example.auntification;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.auntification.ui.login.LoginViewModel;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    static String[] USERNAME_PASSWORD = {
            "d2abaa37a7c3db1137d385e1d8c15fd2", // log: "admin", pass: "admin"
            "22ce1a35b913d3840f9b72fe0f87943e", // log: "m_9sco", pass: "q1w2e3"
            "90db0030e7a6df3b0f4cba5512f72521", // log: "user", pass: "123"

    };

    EditText writing_username;
    EditText writing_password;
    Button sign_in;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        writing_username = (EditText) findViewById(R.id.username);
        writing_password = (EditText) findViewById(R.id.password);
        sign_in = (Button) findViewById(R.id.login);

        View.OnClickListener click_sign_in = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String write_username = writing_username.getText().toString();
                String write_password = writing_password.getText().toString();
                String writing_username_password = write_username + ":" + write_password;
                String hash = MyCrypt.toMd5(writing_username_password);
                if (Arrays.asList(USERNAME_PASSWORD).contains(hash)) {
                    Intent intent = new Intent(LoginActivity.this, SuccessActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(LoginActivity.this, FailActivity.class);
                    startActivity(intent);
                }
            }
        };
        sign_in.setOnClickListener(click_sign_in );

    }
}

