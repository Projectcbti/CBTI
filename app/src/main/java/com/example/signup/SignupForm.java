package com.example.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignupForm extends AppCompatActivity {

    EditText txt_fullName,txt_username,txt_email,txt_password;
    Button btn_create;
    RadioButton radioGenderMale,getRadioGenderFemale;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    String gender="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        getSupportActionBar().setTitle("Mindfull Sleep");

        //casting views
        txt_fullName = (EditText)findViewById(R.id.txt_full_name);
        txt_username = (EditText) findViewById(R.id.txt_user_name);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_password = (EditText) findViewById(R.id.txt_password);
        btn_create = (Button) findViewById(R.id.btn_create);
        radioGenderMale = (RadioButton) findViewById(R.id.radio_male);
        getRadioGenderFemale = (RadioButton) findViewById(R.id.radio_Female);


        databaseReference =FirebaseDatabase.getInstance().getReference("Patient");
        firebaseAuth =FirebaseAuth.getInstance();
        btn_create.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
             //   String gender="";
                final String fullname=txt_fullName.getText().toString();
                final String username=txt_username.getText().toString();
                final String email=txt_email.getText().toString();
                String password=txt_password.getText().toString();

                if(radioGenderMale.isChecked()){
                    gender="Male";

                }
                if(getRadioGenderFemale.isChecked()){
                    gender="Female";
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignupForm.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    //Toast.markText(SignupForm.this, "Please Enter Email", Toast)
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignupForm.this, "Please Enter password", Toast.LENGTH_SHORT).show();
                    //Toast.markText(SignupForm.this, "Please Enter Email", Toast)
                }
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(SignupForm.this, "Please Enter user", Toast.LENGTH_SHORT).show();
                    //Toast.markText(SignupForm.this, "Please Enter Email", Toast)
                }
                if(TextUtils.isEmpty(fullname)){
                    Toast.makeText(SignupForm.this, "Please Enter name", Toast.LENGTH_SHORT).show();
                    //Toast.markText(SignupForm.this, "Please Enter Email", Toast)
                }
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupForm.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                        patient information = new patient(
                                            fullname,
                                            username,
                                                email,
                                              gender
                                        );

                                        firebaseDatabase.getInstance().getReference("Patient")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(SignupForm.this, "Registering Complete", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                            }
                                        });
                                } else {

                                }


                            }
                        });
        }
        });
    }
}
