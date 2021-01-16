package com.example.mrclaim.MyAccidents;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mrclaim.Model.Report_Model;
import com.example.mrclaim.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AccidentImageActivity extends AppCompatActivity {


    Button finsh;
    ImageView img;
    StorageReference mStorageRef;
    Uri imageUri;



    StorageReference storageReference;
    private int IMAGE_CAPTURE_CODE = 1001;
    private static final int PERMISSION_CODE = 1000;




    String VH_ID;
    String DATE;
    String CITY;
    String ADS;



    Report_Model report;



    DatabaseReference reference;

    long maxid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accident_image_activity);



        report=new Report_Model();

        reference=FirebaseDatabase.getInstance().getReference().child("Report");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists())
                    maxid=(snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            VH_ID = extras.getString("VH_ID");
            DATE = extras.getString("DATE");
            CITY = extras.getString("CITY");
            ADS = extras.getString("ADS");

            //The key argument here must match that used in the other activity

            Log.e("Reictive Data",VH_ID+"");
            Log.e("Reictive Data",DATE+"");
            Log.e("Reictive Data",CITY+"");
            Log.e("Reictive Data",ADS+"");

        }


        mStorageRef = FirebaseStorage.getInstance().getReference("Images");

        finsh = (Button) findViewById(R.id.finsh);
        img = (ImageView) findViewById(R.id.imgview);

        finsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                storageReference = FirebaseStorage.getInstance().getReference();
                //uploade image to firebase storage
                final StorageReference fileRef = storageReference.child("Accident_Images/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/" + System.currentTimeMillis() + ".jpg");
                fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        //retriving
                        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {



                              //  Toast.makeText(AccidentImageActivity.this, "Image Upload Success !", Toast.LENGTH_SHORT).show();

                            report.setImagePath(String.valueOf(uri));
                            report.setCurrentUID(FirebaseAuth.getInstance().getUid());
                            report.setVehicleNo(VH_ID);
                            report.setDateTime(DATE);
                            report.setCity(CITY);
                            report.setAddress(ADS);


                               // reference= FirebaseDatabase.getInstance().getReference().child("Report");

                                Toast.makeText(AccidentImageActivity.this, "id"+maxid, Toast.LENGTH_SHORT).show();

                                reference.child(String.valueOf(String.valueOf(maxid+1))).setValue(report).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        Toast.makeText(AccidentImageActivity.this, "Data Inserted Successfully!", Toast.LENGTH_SHORT).show();
                                        //open image upload activity
//                        Intent intent = new Intent(ReportAccidentActivity.this, AccidentImageActivity.class);
                    startActivity(new Intent(AccidentImageActivity.this,Final_instructionsActivity.class));

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Toast.makeText(AccidentImageActivity.this, "Data Inserted Failed!", Toast.LENGTH_SHORT).show();



//                                startActivity(intent);
                                    }
                                });


                                //send to final instruction activity

//


                                //to set picked data to current user data



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AccidentImageActivity.this, "Image Not Retive !", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AccidentImageActivity.this, "Image Uploaded is Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(AccidentImageActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(AccidentImageActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AccidentImageActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_CODE);
                } else {
                    openCamera();
                }
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists())
                    maxid=(snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    Toast.makeText(this, "Permissiondenied...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            img.setImageURI(imageUri);
        }
    }

    public void next(View view) {
//        Intent intent = new Intent(Imgupload.this, NewCase.class);
//        startActivity(intent);
    }
}