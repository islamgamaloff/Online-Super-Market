package com.example.islamgsayed.onlinesupermarket;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.islamgsayed.onlinesupermarket.Models.UserInformationModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText FirstName;
    private EditText LastName;
    private EditText EditTextemail;
    private EditText EditTextpassword;
    private EditText EditTextrepassword;
    private EditText EditTextphonenumber;
    private EditText EditTextage;
    private Button Btnback;
    private Button Btnregister;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private String User_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

//        if (firebaseAuth.getCurrentUser() != null) {
//            finish();
//            startActivity(new Intent(getApplicationContext(), HomePage.class));
//
//        }
        //progressDialog= new ProgressDialog(this);

        FirstName = findViewById(R.id.Name_Reg_ET);
        EditTextemail = findViewById(R.id.Email_Reg_ET);
        EditTextpassword = findViewById(R.id.Password_Reg_ET);
        EditTextphonenumber = findViewById(R.id.Phone_Reg_ET);
        Btnback = findViewById(R.id.Back_Button);
        Btnregister = findViewById(R.id.Continue);

        Btnregister.setOnClickListener(this);
        Btnback.setOnClickListener(this);

    }
    private void registerUser() {

        String firstname = FirstName.getText().toString().trim();
        String email = EditTextemail.getText().toString().trim();
        String password = EditTextpassword.getText().toString().trim();
        String Phonenumber = EditTextphonenumber.getText().toString().trim();

        if (TextUtils.isEmpty(firstname)) {
            Toast.makeText(this, "please enter Name ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "please enter email ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "please enter password ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(Phonenumber)) {
            Toast.makeText(this, "please enter phone number ", Toast.LENGTH_SHORT).show();
            return;
        }

        // ProgressDialog.setMessage("done");
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    saveUserInformatio();
                    finish();
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                    // Toast.makeText(registrationform.this,"done ",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "something wronge ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void saveUserInformatio() {
        String name = FirstName.getText().toString().trim();

        String Email = EditTextemail.getText().toString().trim();
        String password = EditTextpassword.getText().toString().trim();
        String Phone = EditTextphonenumber.getText().toString().trim();

        String id = firebaseAuth.getCurrentUser().getUid();
        UserInformationModel userInformation = new UserInformationModel(name, Email,password,Phone);
        databaseReference.child(id).setValue(userInformation);


    }

    @Override
    public void onClick(View v) {
        if (v == Btnregister) {
            registerUser();

        }
        if (v == Btnback) {
            //will open login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

}