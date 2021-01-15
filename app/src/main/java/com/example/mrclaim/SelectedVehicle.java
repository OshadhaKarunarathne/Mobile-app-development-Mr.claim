package com.example.mrclaim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SelectedVehicle extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_vehicle);

        Intent intent = getIntent();
        Vehicle vehicle = intent.getParcelableExtra("title");

        String imageUrl = vehicle.getImageurl();
        String Vehino = vehicle.getVehino();
        String expdate = vehicle.getExpdate();
        String model = vehicle.getModel();
        String color = vehicle.getColor();
        String chassisNo = vehicle.getChassisNo();
        String engineNo = vehicle.getEngineNo();
        String engcapacity = vehicle.getEngcapacity();


        ImageView imageView = findViewById(R.id.imagecar);
        Glide.with(this).asBitmap().load(imageUrl).into(imageView);

        TextView textView1 = findViewById(R.id.vehno);
        textView1.setText((Vehino));

        TextView textView2 = findViewById(R.id.exp);
        textView2.setText(expdate);

        TextView textView3 = findViewById(R.id.model);
        textView3.setText(model);

        TextView textView4 = findViewById(R.id.color);
        textView4.setText(color);

        TextView textView5 = findViewById(R.id.chassis);
        textView5.setText(chassisNo);

        TextView textView6 = findViewById(R.id.engineNo);
        textView6.setText(engineNo);

        TextView textView7 = findViewById(R.id.engineCap);
        textView7.setText(engcapacity);



    }
}