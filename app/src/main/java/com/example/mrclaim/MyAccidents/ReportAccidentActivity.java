package com.example.mrclaim.MyAccidents;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReportAccidentActivity extends AppCompatActivity  implements LocationListener {

    Button mSendData;
    EditText VehicleNo;
    EditText DateandTime;
    EditText edit_City;
    EditText edit_State;
    EditText edit_FullAddress;
    Button btnShowLocation;
    LocationManager locationManager;
    RadioButton radioButton;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    private double latitude;
    private double longitude;
    long maxid=0;



    DatabaseReference reference;
    Report_Model report;



    @TargetApi(Build.VERSION_CODES.O)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_accident);

       init();



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



        report=new Report_Model();

        mSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String r1=radioButton.getText().toString();
                String r2=radioButton2.getText().toString();
                String r3=radioButton3.getText().toString();
                String r4=radioButton4.getText().toString();
                if (radioButton.isChecked()){
                    report.setMobility(r1);


                }else
                    report.setMobility(r2);

                if (radioButton4.isChecked()){
                    report.setEmergencyVehicle(r4);

                }else
                    report.setEmergencyVehicle(r3);



                String state =edit_State.getText().toString();
                String city =edit_City.getText().toString();
                String address =edit_FullAddress.getText().toString();
                String vn =VehicleNo.getText().toString();

                if (TextUtils.isEmpty(vn)) {
                    VehicleNo.setError("vehicle no is Required!");
                    return;
                }


                if (TextUtils.isEmpty(city)) {
                    edit_City.setError("city no is Required!");
                    return;
                }

                if (TextUtils.isEmpty(state)) {
                    edit_State.setError("state no is Required!");
                    return;
                }


                if (TextUtils.isEmpty(address)) {
                    edit_FullAddress.setError("Address no is Required!");
                    return;
                }

//                report.setCurrentUID(FirebaseAuth.getInstance().getUid());
//                report.setVehicleNo(VehicleNo.getText().toString().trim());
//                report.setDateTime(DateandTime.getText().toString().trim());
//                report.setCity(edit_City.getText().toString().trim());
//                report.setAddress(edit_FullAddress.getText().toString().trim());

                Intent intent = new Intent(getBaseContext(), AccidentImageActivity.class);
                intent.putExtra("VH_ID", VehicleNo.getText().toString());
                intent.putExtra("DATE", DateandTime.getText().toString());
                intent.putExtra("CITY", edit_City.getText().toString().trim());
                intent.putExtra("ADS", edit_FullAddress.getText().toString().trim());
                startActivity(intent);



//                reference.child(String.valueOf(maxid+1)).setValue(report).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//
//                        Toast.makeText(ReportAccidentActivity.this, "Data Inserted Successfully!", Toast.LENGTH_SHORT).show();
//                        //open image upload activity
////                        Intent intent = new Intent(ReportAccidentActivity.this, AccidentImageActivity.class);
////                        startActivity(intent);
//
//                        Intent intent = new Intent(getBaseContext(), AccidentImageActivity.class);
//                        intent.putExtra("Table_ID", String.valueOf(maxid));
//                        startActivity(intent);
//                        finish();
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                        Toast.makeText(ReportAccidentActivity.this, "Data Inserted Failed!", Toast.LENGTH_SHORT).show();
//                    }
//                });


            }
        });




        //Current Time and Date

        EditText editText = findViewById(R.id.date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss ");
        String currentDateandTime = sdf.format(new Date());
        editText.setText(currentDateandTime);





        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(ReportAccidentActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ReportAccidentActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                } else {
                    detectCurrentLocation();
                }
            }
        });


    }

    private void init() {



        VehicleNo =(EditText) findViewById(R.id.Vehicle_No);
        DateandTime=(EditText) findViewById(R.id.date);
        edit_City= (EditText) findViewById(R.id.editCity);
        edit_State= (EditText) findViewById(R.id.editState);
        edit_FullAddress= (EditText) findViewById(R.id.editFullAddress);
        mSendData=(Button) findViewById(R.id.SendData);
        radioButton=(RadioButton)findViewById(R.id.radioButton);
        radioButton2=(RadioButton)findViewById(R.id.radioButton2);
        radioButton3=(RadioButton)findViewById(R.id.radioButton3);
        radioButton4=(RadioButton)findViewById(R.id.radioButton4);


        edit_State = findViewById(R.id.editState);
        edit_City = findViewById(R.id.editCity);
        edit_FullAddress = findViewById(R.id.editFullAddress);
        btnShowLocation = findViewById(R.id.btnLocation);
    }




    public void onRadioButtonSelected(View view) {

        Boolean isChecked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButton:
                if (isChecked)
                    showMessage("Mobility:Yes");
                break;
            case R.id.radioButton2:
                if (isChecked)
                    showMessage("Mobility:No");
        }
        switch (view.getId()) {
            case R.id.radioButton4:
                if (isChecked)
                    showMessage("Need Emergency Vehicle:Yes");
                break;
            case R.id.radioButton3:
                if (isChecked)
                    showMessage("Need Emergency Vehicle:No");
        }
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        findAddress();
    }

    private void findAddress() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);


            String state = addresses.get(0).getAdminArea();
            String city = addresses.get(0).getLocality();
            String fullAddress = addresses.get(0).getAddressLine(0);


            edit_State.setText(state);
            edit_City.setText(city);
            edit_FullAddress.setText(fullAddress);
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Toast.makeText(this, "Please Turn On Location.", Toast.LENGTH_SHORT).show();
    }


    private void detectCurrentLocation() {
        Toast.makeText(this, "Getting your Current Location", Toast.LENGTH_SHORT).show();
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



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==1){
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