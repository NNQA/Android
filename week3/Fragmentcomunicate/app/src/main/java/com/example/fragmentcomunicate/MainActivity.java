package com.example.fragmentcomunicate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button firstfg, secondfg;
    TextView fragmentText;
    firstfragment ffg = new firstfragment();
    secondfragment sfg = new secondfragment();
    private  ItemViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstfg = findViewById(R.id.fragmentbtn);
        secondfg = findViewById(R.id.fragmentbtn1);
        fragmentText = findViewById(R.id.textView);

        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        viewModel.getSelectedItem().observe(this, item-> {
            fragmentText.setText(item);
        });
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout,ffg)
                .addToBackStack(null)
                .commit();
        firstfg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framelayout,ffg)
                        .addToBackStack(null)
                        .commit();

            }
        });
        secondfg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framelayout,sfg)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}