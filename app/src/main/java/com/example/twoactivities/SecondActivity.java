package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView mDisplayMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        mDisplayMessage = (TextView) findViewById(R.id.display_message);
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        mDisplayMessage.setText(message);
    }
}