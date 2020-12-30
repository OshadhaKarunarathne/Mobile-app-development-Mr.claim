package com.example.mrclaim;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.List;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ReportAccident extends AppCompatActivity implements LocationListener {

    EditText edit_City;
    EditText edit_State;
    EditText edit_FullAddress;
    Button btnShowLocation;
    LocationManager locationManager;
    private double latitude;
    private double longitude;


    @TargetApi(Build.VERSION_CODES.O)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_accident);

        EditText editText = findViewById(R.id.date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss ");
        String currentDateandTime = sdf.format(new Date());
        editText.setText(currentDateandTime);


        initViews();


        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(ReportAccident.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ReportAccident.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                } else {
                    detectCurrentLocation();
                }
            }
        });


    }

    private void initViews() {

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