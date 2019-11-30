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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private EditText editTextUsername;
    private EditText Password;
    private Button Btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), HomePage.class));

        }
        editTextUsername = findViewById(R.id.Email_Login_ET);
        Password = findViewById(R.id.Password_Login_ET);
        Btnlogin = findViewById(R.id.login_Button);
        firebaseAuth = FirebaseAuth.getInstance();


        Btnlogin.setOnClickListener(this);
    }

    private void UserLogin() {
        String email = editTextUsername.getText().toString().trim();
        String password = Password.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "please enter first name ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "please enter last name ", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == Btnlogin) {

            UserLogin();
        }

    }
}
