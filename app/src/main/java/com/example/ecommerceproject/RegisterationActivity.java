package com.example.ecommerceproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterationActivity extends AppCompatActivity {
    EditText etName, etEmail, etPassword;
    Button btnSignUp;
    TextView tvBtnSignIn;
    private FirebaseAuth auth;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        init();

    }
    public void init()
    {

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnLogIn);
        tvBtnSignIn = findViewById(R.id.tvBtnSignIn);
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null)
        {
            Intent intent = new Intent(RegisterationActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } //need to understand its functiona
        sharedPreferences = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean("firstTime",true);
        if(isFirstTime)
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstTime",false);
            editor.commit();

            Intent intent = new Intent(RegisterationActivity.this, OnBoarding.class);
            startActivity(intent);
            finish();

        }



    }
    public void signUp(View view)
    {
        String userName = etName.getText().toString();
        String userEmail = etEmail.getText().toString();
        String userPassword = etPassword.getText().toString();
        if(check().equals(true))
        {

            auth.createUserWithEmailAndPassword(userEmail,userPassword)
                    .addOnCompleteListener(RegisterationActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {

                                Toast.makeText(RegisterationActivity.this,"Registeration Successful",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterationActivity.this, MainActivity.class);
                                startActivity(intent);


                            }
                            else
                            {
                                Toast.makeText(RegisterationActivity.this,"Registeration Failed"+ task.getException(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }

    }
    public void signIn(View view)
    {

        Intent intent = new Intent(RegisterationActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public Boolean check()
    {
        boolean check = false;
        String userName = etName.getText().toString();
        String userEmail = etEmail.getText().toString();
        String userPassword = etPassword.getText().toString();

        if(userName.isEmpty())
        {
            etName.setError("Name can not be left empty");
        }
        else if(userEmail.isEmpty())
        {
            etEmail.setError("Email can not be left empty");
        }
        else if(userPassword.isEmpty())
        {
            etPassword.setError("Password can not be left empty");
        }
        else
        {
            check=true;
        }
        return check;
    }
}