package com.example.app;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityMain extends AppCompatActivity {
    FrameLayout container;
    FragmentManager fragment_manager;
    FragmentSignIn fragment_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (FrameLayout) findViewById(R.id.container);
        fragment_manager = getSupportFragmentManager();
        fragment_login = new FragmentSignIn();

        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = fragment_manager.beginTransaction();
            fragmentTransaction.add(R.id.container, fragment_login);
            fragmentTransaction.commit();
        }


    }
}