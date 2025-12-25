package com.example.maigrainetracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryActivity extends AppCompatActivity {

    TextView tvDate, tvPain, tvTrigger, tvDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        tvDate = findViewById(R.id.tvDate);
        tvPain = findViewById(R.id.tvPain);
        tvTrigger = findViewById(R.id.tvTrigger);
        tvDuration = findViewById(R.id.tvDuration);

        SharedPreferences prefs = getSharedPreferences("MigraineData", MODE_PRIVATE);

        String date = prefs.getString("date", "No Data");
        String pain = prefs.getString("pain", "No Data");
        String trigger = prefs.getString("trigger", "No Data");
        String duration = prefs.getString("duration", "No Data");

        tvDate.setText("Date: " + date);
        tvPain.setText("Pain Level: " + pain);
        tvTrigger.setText("Trigger: " + trigger);
        tvDuration.setText("Duration: " + duration + " Hours");
    }
}
