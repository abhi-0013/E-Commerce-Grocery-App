package com.example.abhishek.mygrocerystore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhishek.mygrocerystore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private Button SignIn;
    private EditText email,password;
    private TextView signup;
    private ProgressBar progressBar;

    private FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(LoginActivity.this);
        mauth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.login_progressbar);
        progressBar.setVisibility(View.GONE);
        SignIn = findViewById(R.id.btn_signIn);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        signup = findViewById(R.id.login_signUp_text);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
                progressBar.setVisibility(View.VISIBLE);
//                Toast.makeText(LoginActivity.this,"button is also clicked",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void LoginUser() {
        String Useremail = email.getText().toString();
        String UserPassword = password.getText().toString();

        if(TextUtils.isEmpty(Useremail)){
            Toast.makeText(this,"Email is Empty!",Toast.LENGTH_LONG).show();
            return;
        }else if(TextUtils.isEmpty(UserPassword)){
            Toast.makeText(this,"Password is Empty!",Toast.LENGTH_LONG).show();
            return;
        }else if(UserPassword.length()<8){
            Toast.makeText(this,"Password length should be atleast 8",Toast.LENGTH_LONG).show();
            return;
        }else {
            mauth.signInWithEmailAndPassword(Useremail,UserPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressBar.setVisibility(View.GONE);
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                Toast.makeText(LoginActivity.this,"Login Suceesfully",Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this,"Error : "+task.getException(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}