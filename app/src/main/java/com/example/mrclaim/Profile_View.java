package com.example.mrclaim;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrclaim.Model.Profile_model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Profile_View extends AppCompatActivity {


    EditText Firstname;
    EditText Lastname;
    EditText NIC;
    EditText Birthday;


    TextView casedetails;

    Button send;


   long maxId=0;

//database reference

    FirebaseDatabase rootNode;
    DatabaseReference reference;




    DatabaseReference databaseReference ;
    FirebaseDatabase firebaseDatabase;



    ImageView userImageView;




    private static final int PReqCode = 2 ;



    //to store the image for database

    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

         initView();

         LoadProfileData();

         //insert data
         send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //insert Query
                rootNode = FirebaseDatabase.getInstance();
                reference=FirebaseDatabase.getInstance().getReference();
                reference = rootNode.getReference("UserData");

                Toast.makeText(Profile_View.this,Firstname.getText().toString(),Toast.LENGTH_SHORT);


                Profile_model Profile_model = new Profile_model(
                        FirebaseAuth.getInstance().getUid(),
                        Firstname.getText().toString(),
                        Lastname.getText().toString(),
                        NIC.getText().toString(),
                        Birthday.getText().toString()

                );


                reference.child(FirebaseAuth.getInstance().getUid()).setValue(Profile_model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Profile_View.this, "Data Inserted Successfully!", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Profile_View.this, "Data Inserted Failed!", Toast.LENGTH_SHORT).show();


                    }
                });

            }
        });



        userImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAndRequestForPermission();
                Intent OpenGalleryIntent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(OpenGalleryIntent,1000);
            }
        });

    }

    private void checkAndRequestForPermission() {
        if (ContextCompat.checkSelfPermission(Profile_View.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Profile_View.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(Profile_View.this,"Please accept for required permission", Toast.LENGTH_SHORT).show();

            }

            else
            {
                ActivityCompat.requestPermissions(Profile_View.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }


            return;
        }else{

        }

    }

    private void LoadProfileData() {

        //loading image from the database

        storageReference= FirebaseStorage.getInstance().getReference();
        StorageReference profileRef=storageReference.child("Users/"+FirebaseAuth.getInstance().getUid()+"/Profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso.get().load(uri)
                        .into(userImageView);

            }
        });


        //to load the data from th edatabase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserData");

        //reference to database by current user id

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference yourRef = rootRef.child("UserData").child(FirebaseAuth.getInstance().getUid());
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String birthday = dataSnapshot.child("birthday").getValue(String.class);
                String firstname = dataSnapshot.child("firstname").getValue(String.class);
                String lastname = dataSnapshot.child("lastname").getValue(String.class);
                String nic = dataSnapshot.child("nic").getValue(String.class);



                Firstname.setText(firstname+"");
                Lastname.setText(lastname+"");
                NIC.setText(nic+"");
                Birthday.setText(birthday+"");


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        yourRef.addListenerForSingleValueEvent(eventListener);

    }

    private void initView() {
        Firstname =  findViewById(R.id.First_name);
        Lastname =  findViewById(R.id.Last_name);
        NIC =  findViewById(R.id.NIC);
        Birthday =  findViewById(R.id.Birthday);
        send = findViewById(R.id.btnUpdate);
        userImageView = findViewById(R.id.userImageView);



    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== 1000){
            if(resultCode == Activity.RESULT_OK){
                Uri imageUri=data.getData();
                // image_user_profile.setImageURI(imageUri);


                uploadImageToFirebase(imageUri);

            }

        }
    }

    private void uploadImageToFirebase(Uri imageUri) {



        storageReference= FirebaseStorage.getInstance().getReference();
        //uploade image to firebase storage
        final StorageReference fileRef=storageReference.child("Users/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/Profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                //retriving
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {


                        Picasso.get().load(uri).into(userImageView);


                        //to set picked data to current user data



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Profile_View.this,"Image Not Retive !",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Profile_View.this,"Image Uploaded is Failed",Toast.LENGTH_SHORT).show();
            }
        });


    }

}