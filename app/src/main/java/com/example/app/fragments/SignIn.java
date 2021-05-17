package com.example.app.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.app.methods.KnownUsers;
import com.example.app.R;

public class SignIn extends Fragment implements View.OnClickListener {

    private EditText writing_username;
    private EditText writing_password;
    private Button sign_in;
    private Button sign_up;

    public SignIn() {
        // Required empty public constructor


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        writing_username = (EditText) view.findViewById(R.id.username);
        writing_password = (EditText) view.findViewById(R.id.password);
        sign_in = (Button) view.findViewById(R.id.login);
        sign_up = (Button) view.findViewById(R.id.register);

        sign_in.setOnClickListener(this);
        sign_up.setOnClickListener(this);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        assert getFragmentManager() != null;
        switch (v.getId()) {

            case R.id.login:

                String write_username = writing_username.getText().toString();
                String write_password = writing_password.getText().toString();
                if (KnownUsers.get_pass(write_username, write_password, v.getContext())) {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    SignedIn myFragment = new SignedIn();
                    fragmentTransaction.replace(R.id.container, myFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                } else {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    SignInFail myFragment = new SignInFail();
                    fragmentTransaction.replace(R.id.container, myFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                break;
            case R.id.register:
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                SignUp sign_up = new SignUp();
                transaction.replace(R.id.container, sign_up);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }

    }
}