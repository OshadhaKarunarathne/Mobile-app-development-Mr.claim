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

        ImageView imageView = findViewById(R.id.imagecar);
        Glide.with(this).asBitmap().load(imageUrl).into(imageView);

        TextView textView1 = findViewById(R.id.sample1);
        textView1.setText((Vehino));

        TextView textView2 = findViewById(R.id.sample2);
        textView2.setText(expdate);

    }
}