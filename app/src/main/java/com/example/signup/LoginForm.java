package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("Mindfull Sleep");

    }
//code for onclick will open refernce open
    public void btn_signup(View view) {
        startActivity(new Intent(getApplicationContext(),SignupForm.class));
    }
}
