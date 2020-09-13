package com.example.auntification.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.auntification.FailActivity;
import com.example.auntification.R;
import com.example.auntification.SuccessActivity;
import com.example.auntification.ui.login.LoginViewModel;
import com.example.auntification.ui.login.LoginViewModelFactory;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    static String[] USERNAME_PASSWORD = {"admin:admin"};

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

                if (Arrays.asList(USERNAME_PASSWORD).contains(writing_username_password)) {
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

