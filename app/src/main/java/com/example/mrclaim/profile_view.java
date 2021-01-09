package com.example.mrclaim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrclaim.Model.Gargae_Model;
import com.example.mrclaim.Model.profile_view_model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile_view extends AppCompatActivity {


    EditText Firstname;
    EditText Lastname;
    EditText NIC;
    EditText Birthday;
    DatabaseReference reference;
    FirebaseDatabase rootNode;


    TextView casedetails;

    Button send;


   long maxId=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        initView();



        //define the database variable
        reference=FirebaseDatabase.getInstance().getReference().child("Profile");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists())
                    maxId=(snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        send = findViewById(R.id.btnUpdate);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("profile_view_model");

                Toast.makeText(profile_view.this,Firstname.getText().toString(),Toast.LENGTH_SHORT);


                profile_view_model Profile_view_model= new profile_view_model(
                        FirebaseAuth.getInstance().getUid(),
                        Firstname.getText().toString(),
                        Lastname.getText().toString(),
                        NIC.getText().toString(),
                        Birthday.getText().toString()

                );


                reference.child(Firstname.getText().toString()).setValue(Profile_view_model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(profile_view.this, "Data Inserted Successfully!", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(profile_view.this, "Data Inserted Failed!", Toast.LENGTH_SHORT).show();


                    }
                });

            }
        });

    }

    private void initView() {
        Firstname =  findViewById(R.id.First_name);
        Lastname =  findViewById(R.id.Last_name);
        NIC =  findViewById(R.id.NIC);
        Birthday =  findViewById(R.id.Birthday);



    }
}