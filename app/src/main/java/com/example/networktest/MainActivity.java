package com.example.networktest;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    String serverMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    class BackgroundThread implements Runnable {
        String matriculationNumber;

        BackgroundThread(String matriculationNumber) {
            this.matriculationNumber = matriculationNumber;
        }

        @Override
        public void run() {
            try {
                String sentence;
                String modifiedSentence;

                Socket clientSocket = new Socket("se2-isys.aau.at", 53212);

                DataOutputStream outToServer = new DataOutputStream((clientSocket.getOutputStream()));
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                sentence = matriculationNumber;
                outToServer.writeBytes(sentence + '\n');
                modifiedSentence = inFromServer.readLine();

                System.out.println("FROM SERVER: " + modifiedSentence);
                serverMessage = modifiedSentence;

                clientSocket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sentRequest(View v) {
        TextView editText = findViewById(R.id.editText);

        BackgroundThread backgroundThread = new BackgroundThread(editText.getText().toString());
        Thread t = new Thread(backgroundThread);
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TextView textView = findViewById(R.id.textView);
        textView.setText(serverMessage);
    }

    public void markDigits(View v) {
        TextView textView = findViewById(R.id.textView);
        TextView editText = findViewById(R.id.editText);

        textView.setMovementMethod(new ScrollingMovementMethod());

        textView.setText(Utilities.markPairs(editText.getText().toString()));
    }

}
