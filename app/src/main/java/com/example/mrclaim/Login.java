package com.example.mrclaim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    public FirebaseAuth mAuth;

    EditText email;
    EditText pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);

    }

    public void Auth() {
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

                            startActivity(new Intent(Login.this, MainActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.please re enter correct email and password.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }

                        // ...

                });

    }

    public void login(View view){

        Auth();
    }

    public void Regsiter(View view){ startActivity(new Intent(Login.this, register.class));}

    public void NavigateForgetMyPassword(View v) {
        Intent inent = new Intent(this, ResetPasswordActivity.class);
        startActivity(inent);
    }


}






