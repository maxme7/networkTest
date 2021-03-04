package com.example.networktest;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sentRequest(View v) {
        TextView textView = findViewById(R.id.textView);
        textView.setText(Utilities.markPairs("123456"));
    }

}
