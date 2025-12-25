package com.example.maigrainetracker;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    ImageView logo;
    TextView tvWelcome;
    EditText etEmail, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logo = findViewById(R.id.logo);
        tvWelcome = findViewById(R.id.tvWelcome);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        logo.startAnimation(slideUp);
        tvWelcome.startAnimation(slideUp);
        etEmail.startAnimation(slideUp);
        etPassword.startAnimation(slideUp);
        btnLogin.startAnimation(slideUp);
    }
}
