package com.example.mrclaim.MyVehicles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mrclaim.Model.MyVehicle_Model;
import com.example.mrclaim.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class SelectedVehicleActivity extends AppCompatActivity {


    TextView vehicle_no;
    TextView EXDate;
    TextView Model;
    TextView EnigineNO;
    TextView Capacity;
    TextView Color;


    ImageView image;
    MyVehicle_Model myVehicle_model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_vehicle2);

        vehicle_no=findViewById(R.id.vehicle_no);
        EXDate=findViewById(R.id.EXDate);
        Model=findViewById(R.id.Model);
        EnigineNO=findViewById(R.id.EnigineNO);
        Capacity=findViewById(R.id.Capacity);
        Color=findViewById(R.id.Color);
        image=findViewById(R.id.image);


        myVehicle_model = getIntent().getParcelableExtra("title");
        vehicle_no.setText(myVehicle_model.getVehicleNo()+"'");
        EXDate.setText(myVehicle_model.getExDate()+"'");
        Model.setText(myVehicle_model.getModel()+"'");
        EnigineNO.setText(myVehicle_model.getEngineNo()+"'");
        Capacity.setText(myVehicle_model.getCapacity()+"'");
        Color.setText(myVehicle_model.getColor()+"'");


        try{
            Picasso.get().load(myVehicle_model.getImagePath() )
                    .networkPolicy( NetworkPolicy.NO_CACHE)
                    .memoryPolicy( MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)

                    .into(image );


        }catch ( Exception e){

        }
    }

    public void goToMyVehicleActivity(View view) {


        startActivity(new Intent(SelectedVehicleActivity.this,MyVehiclesActivity.class));
    }
}