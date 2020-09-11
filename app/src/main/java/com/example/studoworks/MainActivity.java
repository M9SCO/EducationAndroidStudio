package com.example.studoworks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    CheckBox AccessCheckBox;
    Button SendButton;
    TextView MessageTextView;
    EditText WriteMessageTextBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AccessCheckBox = (CheckBox) findViewById(R.id.AccessCheckBox);
        MessageTextView = (TextView) findViewById(R.id.MessageTextView);
        SendButton = (Button) findViewById(R.id.SendButton);
        WriteMessageTextBox = (EditText) findViewById(R.id.WriteMessageTextBox);

        View.OnClickListener send_text = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageTextView.setText(WriteMessageTextBox.getText().toString());
                WriteMessageTextBox.setText("");
            }
        };
        View.OnClickListener set_access = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean value = AccessCheckBox.isChecked();
//                if (!value) {
//                    SendButton.setBackgroundColor(0,0,0);
//                }



                SendButton.setClickable(value);
                WriteMessageTextBox.setClickable(value);
                SendButton.setFocusable(value);
//                WriteMessageTextBox.setFocusable(value);
                SendButton.setLongClickable(value);
                WriteMessageTextBox.setLongClickable(value);
                SendButton.setCursorVisible(value);
                WriteMessageTextBox.setCursorVisible(value);
            }
        };



        SendButton.setOnClickListener(send_text);
        AccessCheckBox.setOnClickListener(set_access);

    }


}