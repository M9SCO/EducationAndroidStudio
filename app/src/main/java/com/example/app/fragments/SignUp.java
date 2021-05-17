package com.example.app.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app.methods.KnownUsers;
import com.example.app.R;


public class SignUp extends Fragment implements View.OnClickListener {

    private EditText registered_username;
    private EditText registered_password;
    private EditText registered_password_again;
    private TextView errors;
    private Button registered;


    public SignUp() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        registered_username = (EditText) view.findViewById(R.id.registrationUsername);
        registered_password = (EditText) view.findViewById(R.id.registrationPassword);
        registered_password_again = (EditText) view.findViewById(R.id.registrationPasswordAgain);
        registered = (Button) view.findViewById(R.id.registration);
        errors = (TextView) view.findViewById(R.id.textErrors);
        registered.setOnClickListener(this);


        TextWatcher checker = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String error_out = "";
                if (!registered_password.getText().toString().equals(registered_password_again.getText().toString()))
                    error_out += "\n" + getResources().getString(R.string.different_password);
                else if (registered_password.getText().toString().length() < 6)
                    error_out += "\n" + getResources().getString(R.string.short_password);
                if (registered_username.getText().toString().length() <= 3)
                    error_out += "\n" + getResources().getString(R.string.short_username);
                else if ((registered_username.getText().toString().length() >= 16))
                    error_out += "\n" + getResources().getString(R.string.large_username);
                registered.setEnabled(error_out.isEmpty());
                errors.setText(error_out);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        registered_username.addTextChangedListener(checker);
        registered_password_again.addTextChangedListener(checker);
        registered_password.addTextChangedListener(checker);

    }

    @Override
    public void onClick(View view) {
        String login = registered_username.getText().toString();
        String password = registered_password.getText().toString();
        KnownUsers.set_pass(login, password, getActivity().getApplicationContext());
        getFragmentManager().popBackStackImmediate();


    }
}