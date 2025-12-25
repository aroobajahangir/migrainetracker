package com.example.maigrainetracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnHistory, btnPredict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnHistory = findViewById(R.id.btnHistory);
        btnPredict = findViewById(R.id.btnPredict);

        btnAdd.setOnClickListener(v ->
                startActivity(new Intent(this, AddMigraineActivity.class)));

        btnHistory.setOnClickListener(v ->
                startActivity(new Intent(this, HistoryActivity.class)));

        btnPredict.setOnClickListener(v ->
                startActivity(new Intent(this, PredictActivity.class)));
    }
}
