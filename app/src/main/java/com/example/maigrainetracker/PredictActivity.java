package com.example.maigrainetracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PredictActivity extends AppCompatActivity {

    EditText etPainPredict;
    TextView tvResult;
    Button btnPredict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict);

        etPainPredict = findViewById(R.id.etPainPredict);
        tvResult = findViewById(R.id.tvResult);
        btnPredict = findViewById(R.id.btnPredict);

        // 🔽 YAHAN CLICK LISTENER
        btnPredict.setOnClickListener(v -> {

            // 🔹 YAHI WO CODE HAI
            SharedPreferences prefs = getSharedPreferences("MigraineData", MODE_PRIVATE);
            String painText = prefs.getString("pain", "");

            if (painText.isEmpty()) {
                tvResult.setText("No migraine data found");
                return;
            }

            int pain = Integer.parseInt(painText);

            if (pain >= 7) {
                tvResult.setText("High Migraine Risk");
            } else if (pain >= 4) {
                tvResult.setText("Medium Migraine Risk");
            } else {
                tvResult.setText("Low Migraine Risk");
            }
        });
    }
}
