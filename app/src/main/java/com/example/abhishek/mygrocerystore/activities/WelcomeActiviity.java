package com.example.abhishek.mygrocerystore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.abhishek.mygrocerystore.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActiviity extends AppCompatActivity {
    private static final String TAG = "WelcomeActiviity";
    private ProgressBar progressBar;

    private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_activiity);
        progressBar = findViewById(R.id.welcome_progressBar);
        progressBar.setVisibility(View.GONE);

        FirebaseApp.initializeApp(WelcomeActiviity.this);
        mauth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mauth.getCurrentUser() != null){
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(WelcomeActiviity.this, MainActivity.class));
            Toast.makeText(this,"Please Wait you are already logged in ",Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void Login(View view) {
        Log.d(TAG, "Login: called");
        progressBar.setVisibility(View.VISIBLE);
        startActivity(new Intent(WelcomeActiviity.this, LoginActivity.class));
    }

    public void Registration(View view) {
        Log.d(TAG, "Registration: called");
        progressBar.setVisibility(View.VISIBLE);
        startActivity(new Intent(WelcomeActiviity.this, RegistrationActivity.class));
    }
}