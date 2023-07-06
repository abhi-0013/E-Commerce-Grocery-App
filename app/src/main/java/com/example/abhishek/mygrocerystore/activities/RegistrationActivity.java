package com.example.abhishek.mygrocerystore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhishek.mygrocerystore.Models.Users;
import com.example.abhishek.mygrocerystore.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    private static final String TAG = "RegistrationActivity";
    private Button SignUp;
    private EditText email,name,password, Phone;
    private ProgressBar progressBar;
    private TextView signin;
    private FirebaseAuth mauth;
    private FirebaseDatabase mdatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        FirebaseApp.initializeApp(RegistrationActivity.this);
        mauth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance();

        progressBar = findViewById(R.id.reg_progress_bar);
        progressBar.setVisibility(View.GONE);
        SignUp = findViewById(R.id.btn_signUp);
        email = findViewById(R.id.reg_email);
        name = findViewById(R.id.reg_name);
        password = findViewById(R.id.reg_password);
        Phone = findViewById(R.id.reg_Phone);
        signin = findViewById(R.id.reg_signIn_text);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: called");
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(RegistrationActivity.this,"button is clicked",Toast.LENGTH_SHORT).show();
                createNewUser();
                progressBar.setVisibility(View.VISIBLE);
            }

        });

    }
    private void createNewUser() {
        String UserName = name.getText().toString();
        String Useremail = email.getText().toString();
        String UserPhone = Phone.getText().toString();
        String UserPassword = password.getText().toString();

//        Log.d(TAG, "createNewUser: email is : "+ Useremail);
        if(TextUtils.isEmpty(UserName)){
            Toast.makeText(this,"Name is Empty!",Toast.LENGTH_LONG).show();
            return;
        }else if(TextUtils.isEmpty(Useremail)){
            Toast.makeText(this,"Email is Empty!",Toast.LENGTH_LONG).show();
            return;
        }else if(TextUtils.isEmpty(UserPhone)){
            Toast.makeText(this,"Phone number is Empty!",Toast.LENGTH_LONG).show();
            return;
        }else if(TextUtils.isEmpty(UserPassword)){
            Toast.makeText(this,"Password is Empty!",Toast.LENGTH_LONG).show();
            return;
        }else if(UserPassword.length()<8){
            Toast.makeText(this,"Password length should be atleast 8",Toast.LENGTH_LONG).show();
            return;
        }else{
            // creating user in Firebase

            mauth.createUserWithEmailAndPassword(Useremail,UserPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Users users = new Users(UserName,Useremail,UserPhone,UserPassword);
                                String id = task.getResult().getUser().getUid();

                                mdatabase.getReference().child("Users").child(id).setValue(users);
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(RegistrationActivity.this,"Successfull",Toast.LENGTH_LONG).show();
                            }else{
//                                Log.d(TAG, "onComplete: ======================");
//                                Log.d(TAG, "onComplete: error"+ task.getException());
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(RegistrationActivity.this,"Error : "+task.getException(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}