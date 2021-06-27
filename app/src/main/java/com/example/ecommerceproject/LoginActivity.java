package com.example.ecommerceproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText etName, etEmail, etPassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intit();
    }

    private void intit() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        auth = FirebaseAuth.getInstance();
    }

    public void MainSignUp(View view) //signUp
    {
        Intent intent = new Intent(LoginActivity.this, RegisterationActivity.class);
        startActivity(intent);
    }
    public void LogIn(View view) //signIn
    {

        String userEmail = etEmail.getText().toString();
        String userPassword = etPassword.getText().toString();
        if(check().equals(true))
        {
            auth.signInWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {

                                Toast.makeText(LoginActivity.this,"LogIn Successful",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this,"Error"+ task.getException(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }
    public Boolean check()
    {
        String userEmail = etEmail.getText().toString();
        String userPassword = etPassword.getText().toString();
        Boolean check = false;

        if(userEmail.isEmpty())
        {
            etEmail.setError("Email can not be left empty");

        }
        else if(userPassword.isEmpty())
        {
            etPassword.setError("Password can not be left empty");
        }
        else
        {
            check = true;
        }

        return check;


    }

}