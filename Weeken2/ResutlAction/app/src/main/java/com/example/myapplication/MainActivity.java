package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        EditText text1 = (EditText) findViewById(R.id.inputA);
        EditText text2 = (EditText) findViewById(R.id.inputB);
        EditText text3 = (EditText) findViewById(R.id.result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(text1.getText().toString());
                int b= Integer.parseInt(text2.getText().toString());
                text3.setText(String.valueOf(a+b));
            }
        });
    }
    public int Add(int A, int B) {
        return A + B;
    }
}