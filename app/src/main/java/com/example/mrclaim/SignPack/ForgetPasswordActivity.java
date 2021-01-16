package com.example.mrclaim.SignPack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mrclaim.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {
    private EditText inputEmail;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password_activity);

        init();
    }

    private void init() {

        inputEmail = (EditText) findViewById(R.id.EditTextSurname);
        auth = FirebaseAuth.getInstance();
    }


    public void goToRegister(View view) {

        startActivity(new Intent(ForgetPasswordActivity.this,RegisterActivity.class));

    }

    public void FrogetPasswordSubmit(View view) {

        String email = inputEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplication(), "Enter your mail address", Toast.LENGTH_SHORT).show();
            return;

        }
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgetPasswordActivity.this, "We send you an e-mail", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(ForgetPasswordActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}