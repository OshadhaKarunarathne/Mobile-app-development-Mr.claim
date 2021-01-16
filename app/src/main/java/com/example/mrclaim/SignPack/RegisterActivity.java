package com.example.mrclaim.SignPack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mrclaim.Model.Profile_model;
import com.example.mrclaim.ProfileActivity;
import com.example.mrclaim.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public FirebaseAuth mAuth;
    private FirebaseFirestore fStore;

    EditText FirstNameText;
    EditText email;
    EditText password;
    EditText confirm_password;
    EditText refferal_code;
    ProgressBar progressBar;





    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

         init();

    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        fStore= FirebaseFirestore.getInstance();
        FirstNameText=findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        confirm_password=findViewById(R.id.confirm_password);
        refferal_code=findViewById(R.id.refferal_code);
        progressBar=findViewById(R.id.progressBar);
    }

    public void RegisterUser(View view) {


        String  username_create=FirstNameText.getText().toString();

        String  email_create=email.getText().toString();
        String password_create=password.getText().toString();
        String confirm_password_create=confirm_password.getText().toString();
        String  refferal_code_create=refferal_code.getText().toString();

        if (TextUtils.isEmpty(username_create)) {
            FirstNameText.setError("Username is Required!");
            return;
        }
        if (TextUtils.isEmpty(email_create)) {
            email.setError("Email is Required!");
            return;
        }
        if (TextUtils.isEmpty(password_create)) {
            password.setError("Password is Required!");
            return;
        }
        if (TextUtils.isEmpty(confirm_password_create)) {
            confirm_password.setError("Please enter password confirmation!");

            return;
        }
        if (TextUtils.isEmpty(refferal_code_create)) {
            refferal_code.setError("Refferal code is Required!");
            return;
        }
        if (password.length() < 8) {
            password.setError("Password must be 8 charactors!");
            return;
        }

        if (!confirm_password_create.equals(password_create)) {
            confirm_password.setError("confirm password is not match ");
            return;
        }


        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email_create, password_create)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            //FirebaseUser user = mAuth.getCurrentUser();


//                            final String  userID=mAuth.getCurrentUser().getUid();



                            rootNode = FirebaseDatabase.getInstance();
                            reference= FirebaseDatabase.getInstance().getReference();
                            reference = rootNode.getReference("UserData");

                            Profile_model Profile_model = new Profile_model(
                                    FirebaseAuth.getInstance().getUid(),
                                    FirstNameText.getText().toString(),
                                    "",
                                   "",
                                   ""

                            );


                            reference.child(FirebaseAuth.getInstance().getUid()).setValue(Profile_model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(RegisterActivity.this, "Data Inserted Successfully!", Toast.LENGTH_SHORT).show();


                                    startActivity( new Intent(RegisterActivity.this,LoginActivity.class));

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this, "Data Inserted Failed!", Toast.LENGTH_SHORT).show();


                                }
                            });

                            //updateUI(user);
                        } else {

                            progressBar.setVisibility(View.INVISIBLE);
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //  updateUI(null);
                        }

                        // ...
                    }
                });
    }
}