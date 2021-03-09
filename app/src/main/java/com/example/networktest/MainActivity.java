package com.example.networktest;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
    }

    public void markDigits(View v) {
        TextView textView = findViewById(R.id.textView);
        TextView editText = findViewById(R.id.editText);

        textView.setMovementMethod(new ScrollingMovementMethod());

        textView.setText(Utilities.markPairs(editText.getText().toString()));
    }

}
