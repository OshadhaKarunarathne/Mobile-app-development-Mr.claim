package com.example.mrclaim.SignPack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mrclaim.Home;
import com.example.mrclaim.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    public FirebaseAuth mAuth;

    EditText email;
    EditText pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        init();
    }

    private void init() {

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);

    }

    public void LoginMethod(View view) {
        String email_c = email.getText().toString();
        String passl_c = pass.getText().toString();

        if (TextUtils.isEmpty(email_c)) {
            email.setError("Email is Required!");
            return;
        }
        if (TextUtils.isEmpty(passl_c)) {
            pass.setError("Password is Required!");
            return;
        }




        mAuth.signInWithEmailAndPassword(email_c, passl_c)

                .addOnCompleteListener(this,(task) -> {

                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithEmail:success.");
                        FirebaseUser user = mAuth.getCurrentUser();

                        startActivity(new Intent(LoginActivity.this, Home.class));
                        finish();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Authentication failed.please re enter correct email and password.",
                                Toast.LENGTH_SHORT).show();
                        // updateUI(null);
                    }

                    // ...

                });


    }

    public void goToRegister(View view) {

        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));

    }

    public void goToForgotPassword(View view) {

        startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));
    }
}