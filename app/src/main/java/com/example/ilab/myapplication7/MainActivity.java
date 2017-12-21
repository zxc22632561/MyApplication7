package com.example.ilab.myapplication7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(){

                    public void run(){
                        try {
                            URL url = new URL("http://www.nhu.edu.tw");
                            InputStream inputStream = url.openStream();
                            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    EditText editText = (EditText) findViewById(R.id.editText);

                                    try {
                                        editText.setText(bufferedReader.readLine());

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                            inputStream.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
            }
        });
    }
}
