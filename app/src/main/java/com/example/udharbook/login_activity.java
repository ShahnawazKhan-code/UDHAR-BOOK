package com.example.udharbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_activity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText editTextemail,editTextpass;
    Button loginB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextemail = findViewById(R.id.loginputemail);
        editTextpass = findViewById(R.id.inputlogin_password);
        loginB = findViewById(R.id.login_button);
        mAuth = FirebaseAuth.getInstance();

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });




        // remove action bar
        TextView btn=findViewById(R.id.textviewSignUp);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // change activity login to registration
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_activity.this,register_activity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    private void loginUser(){

        String email, pass;
        email = editTextemail.getText().toString();
        pass = editTextpass.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(login_activity.this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(login_activity.this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(login_activity.this, "login Successful ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login_activity.this,MainActivity2.class));
                        finish();
                    }
                    else{
                        Toast.makeText(login_activity.this, "Login Error:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


}