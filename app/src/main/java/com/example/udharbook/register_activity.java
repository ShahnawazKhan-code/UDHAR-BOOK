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

public class register_activity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText regemail,regpass,regconpass;
    Button regButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // remove action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mAuth = FirebaseAuth.getInstance();
        regemail = findViewById(R.id.loginputemail);
        regpass = findViewById(R.id.reginputpassword);
        regconpass = findViewById(R.id.reginputconfirmpassword);
        regButton = findViewById(R.id.btnregister);



        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });







        TextView btn=findViewById(R.id.alreadyHaveAccount);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register_activity.this, login_activity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    private void createUser(){

        String email = regemail.getText().toString();
        String pass =  regpass.getText().toString();
        String conpass = regconpass.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(register_activity.this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(register_activity.this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(conpass)){
            Toast.makeText(register_activity.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!pass.equals(conpass)){
            Toast.makeText(this, "Confirm Password Not Match", Toast.LENGTH_SHORT).show();
            return;
        }
        else{

            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(register_activity.this, "User Registration Successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(register_activity.this, "Registration Error:"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }




    }

}