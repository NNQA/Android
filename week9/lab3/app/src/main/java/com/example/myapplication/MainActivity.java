package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {
    EditText t1, t2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = findViewById(R.id.textname);
        t2 = findViewById(R.id.textage);
        button = findViewById(R.id.button);
        DatabaseReference temp = FirebaseDatabase.getInstance().getReference().child("test");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model m = new model(t1.getText().toString(), t2.getText().toString());
                temp.push().setValue(m);
                Toast.makeText(MainActivity.this, "successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}