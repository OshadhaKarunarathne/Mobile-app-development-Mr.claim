package com.example.mrclaim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrclaim.Model.Gargae_Model;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class garage_details extends AppCompatActivity implements LocationListener  {

    Button btnLocation;
    EditText vehicle_no;
    EditText gname;
    EditText timedate;
    EditText FullAddress;
    DatabaseReference reference;
    FirebaseDatabase rootNode;

    Button button_back;

    TextView casedetails;

    Button send;

    LocationManager locationManager;
    private double latitude;
    private double longitude;
    long maxId=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_details);


        initView();




        //retrieve value from the intent and set it to text view

        Bundle extras = getIntent().getExtras();
        String data=null;

        if (extras != null) {
            data = extras.getString("data");
            // and get whatever type user account id is
        }
        casedetails.setText(data);




        //define the database variable
        reference=FirebaseDatabase.getInstance().getReference().child("GarageDetails");
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


        button_back = findViewById(R.id.button_back);
        send = findViewById(R.id.Send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("gargae_model");

              Toast.makeText(garage_details.this,vehicle_no.getText().toString(),Toast.LENGTH_SHORT);


                Gargae_Model gargae_model= new Gargae_Model(
                        FirebaseAuth.getInstance().getUid(),
                        vehicle_no.getText().toString(),
                        gname.getText().toString(),
                        timedate.getText().toString(),
                        FullAddress.getText().toString()



                );

                String  vn=vehicle_no.getText().toString();
                String  g=gname.getText().toString();
              //  String  td=timedate.getText().toString();
                String  fa=FullAddress.getText().toString();
              //  String  cd=casedetails.getText().toString();

                if (TextUtils.isEmpty(vn)) {
                    vehicle_no.setError("vehicle no is Required!");
                    return;
                }
                if (TextUtils.isEmpty(g)) {
                    gname.setError("Garage name is Required!");
                    return;
                }

                if (TextUtils.isEmpty(fa)) {
                    FullAddress.setError("Full address is Required!");
                    return;
                }

                reference.child(vehicle_no.getText().toString()).setValue(gargae_model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(garage_details.this,  "Data Inserted Successfully!", Toast.LENGTH_SHORT).show();

                        //send to final instruction activity

                        Intent intent = new Intent(garage_details.this,Final_instructions.class);
                        startActivity(intent);


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(garage_details.this, "Data Inserted Failed!", Toast.LENGTH_SHORT).show();


                    }
                });

            }
        });




        //Current Time and Date

        EditText editText = findViewById(R.id.timedate);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss ");
        String currentDateandTime = sdf.format(new Date());
        editText.setText(currentDateandTime);



        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(garage_details.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(garage_details.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


                } else {
                    detectCurrentLocation();
                }
            }
        });


    }

    private void detectCurrentLocation() {
        Toast.makeText(this, "Please getting your current Location", Toast.LENGTH_SHORT).show();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    private void initView() {
        vehicle_no =  findViewById(R.id.vehicle_no);
        gname = findViewById(R.id.gname);
        timedate = findViewById(R.id.timedate);
        FullAddress = findViewById(R.id.FullAddress);
        casedetails = findViewById(R.id.casedetails);
        btnLocation = findViewById(R.id.btnLocation);
        send = findViewById(R.id.SendData);



        }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude=location.getLatitude();
        longitude=location.getLongitude();
        findAddress();

    }

    private void findAddress() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());
        try {

            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            String fullAddress = addresses.get(0).getAddressLine(0);

            FullAddress.setText(fullAddress);
        } catch (Exception e) {

        }
    }

            @Override
            public void onStatusChanged (String provider,int status, Bundle extras){

            }

            @Override
            public void onProviderEnabled (@NonNull String provider){

            }

        @Override
        public void onProviderDisabled (@NonNull String provider){
            Toast.makeText(this, "Turn on gps", Toast.LENGTH_SHORT).show();

        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                detectCurrentLocation();
            }
            else
            {
                Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show();


            }
        }

    }





    }
