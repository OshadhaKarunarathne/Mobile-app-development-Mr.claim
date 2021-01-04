package com.example.mrclaim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class garage_details extends AppCompatActivity {

      /*      EditText Vnumber;
            EditText gname;
            EditText time;
            EditText city;
            EditText state;
            EditText fulladdress;
            Button send;
            Button back;
            long maxid=0;
    LocationManager locationManager;
    private double latitude;
    private double longitude;


    DatabaseReference reference;
    Report report;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_accident);

        Vnumber = findViewById(R.id.vehicle_no);
        gname = findViewById(R.id.gname);
        time = findViewById(R.id.timeanddate);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        fulladdress=findViewById(R.id.FullAddress);
        send=(Button) findViewById(R.id.SendData);
        back = findViewById(R.id.button_back);

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


        report=new Report();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  Vnumber_create=Vnumber.getText().toString();
                String  gname_create=gname.getText().toString();
                String  time_create=time.getText().toString();
                String  city_create=city.getText().toString();
                String  state_create=state.getText().toString();
                String  fulladdress_create=fulladdress.getText().toString();

                reference.child(String.valueOf(maxid+1)).setValue(report);
                Toast.makeText(garage_details.this, "Data Inserted Successfully!", Toast.LENGTH_SHORT).show();



                if (TextUtils.isEmpty((CharSequence) Vnumber_create)) {
                    Vnumber.setError("This can not be Empty!");
                    return;
                }
                if (TextUtils.isEmpty((CharSequence) gname_create)) {
                    gname.setError("This can not be Empty!");
                    return;
                }
            }
        });

        EditText editText = findViewById(R.id.timeanddate);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss ");
        String currentDateandTime = sdf.format(new Date());
        editText.setText(currentDateandTime);

        initViews();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(garage_details.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(garage_details.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                } else {
                    detectCurrentLocation();
                }
            }

            private void detectCurrentLocation() {
            }
        });


    }



    private void initViews() {

        state = findViewById(R.id.state);
        city = findViewById(R.id.city);
        fulladdress = findViewById(R.id.FullAddress);
        send = findViewById(R.id.SendData);
    }

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


            String State = addresses.get(0).getAdminArea();
            String City = addresses.get(0).getLocality();
            String FullAddress = addresses.get(0).getAddressLine(0);



            state.setText(State);
            city.setText(City);
            fulladdress.setText(FullAddress);
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this);

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
    }                                    */
}
