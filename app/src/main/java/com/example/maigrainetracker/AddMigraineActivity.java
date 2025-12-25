package com.example.maigrainetracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddMigraineActivity extends AppCompatActivity {

    EditText etDate, etPain, etTrigger, etDuration;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_migraine);

        etDate = findViewById(R.id.etDate);
        etPain = findViewById(R.id.etPain);
        etTrigger = findViewById(R.id.etTrigger);
        etDuration = findViewById(R.id.etDuration);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {

            String date = etDate.getText().toString();
            String pain = etPain.getText().toString();
            String trigger = etTrigger.getText().toString();
            String duration = etDuration.getText().toString();

            if (date.isEmpty() || pain.isEmpty() || trigger.isEmpty() || duration.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs = getSharedPreferences("MigraineData", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putString("date", date);
            editor.putString("pain", pain);
            editor.putString("trigger", trigger);
            editor.putString("duration", duration);
            editor.apply();

            Toast.makeText(this, "Migraine Data Saved Successfully", Toast.LENGTH_SHORT).show();
        });
    }
}
