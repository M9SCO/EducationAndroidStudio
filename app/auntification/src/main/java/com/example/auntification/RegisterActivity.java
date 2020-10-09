package com.example.auntification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    EditText registered_username;
    EditText registered_password;
    EditText registered_password_again;
    TextView errors;
    Button registered;

    private void check_valid_data(){
        String error_out = "";

        if (!registered_password.getText().toString().equals(registered_password_again.getText().toString()))
            error_out += "\n" + getResources().getString(R.string.different_password);
        else if (registered_password.getText().toString().length() <6)
            error_out += "\n" + getResources().getString(R.string.short_password);
        if (registered_username.getText().toString().length() <=3)
            error_out += "\n" + getResources().getString(R.string.short_username);
        else if ((registered_username.getText().toString().length() >=16))
            error_out += "\n" + getResources().getString(R.string.large_username);
        registered.setEnabled(error_out.isEmpty());
        errors.setText(error_out);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registered_username = (EditText) findViewById(R.id.registrationUsername);
        registered_password = (EditText) findViewById(R.id.registrationPassword);
        registered_password_again = (EditText) findViewById(R.id.registrationPasswordAgain);
        registered = (Button) findViewById(R.id.registration);
        errors = (TextView) findViewById(R.id.textErrors);

        TextWatcher checker = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                check_valid_data();}
            @Override
            public void afterTextChanged(Editable editable) {}};

        registered_username.addTextChangedListener(checker);
        registered_password_again.addTextChangedListener(checker);
        registered_password.addTextChangedListener(checker);

        registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String log_pass = registered_username.getText().toString() + ":" + registered_password.getText().toString();
                KnownUsers.addHashes(MyCrypt.toMd5(log_pass));
                final Dialog dialog = new Dialog(RegisterActivity.this);
                dialog.setContentView(R.layout.layout_dialog);
                Button ok_button = (Button) dialog.findViewById(R.id.button);
                ok_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        finish();

                    }
                });
                dialog.setCancelable(false);
                dialog.show();


            }
        });



    }
}