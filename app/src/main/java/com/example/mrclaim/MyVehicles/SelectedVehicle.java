package com.example.mrclaim.MyVehicles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mrclaim.Model.MyVehicle_Model;
import com.example.mrclaim.R;

public class SelectedVehicle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_vehicle);

        Intent intent = getIntent();
        MyVehicle_Model vehicle = intent.getParcelableExtra("title");

        String Capacity = vehicle.getCapacity();
        String Color = vehicle.getColor();
        String EngineNo = vehicle.getEngineNo();
        String Exdate = vehicle.getExDate();
        String Model = vehicle.getModel();
        String CurrentUID = vehicle.getCurrentUID();
        String ImagePath = vehicle.getImagePath();
        String VehicleNo = vehicle.getVehino();

        ImageView imageView = findViewById(R.id.imagecar);
        Glide.with(this).asBitmap().load(ImagePath).into(imageView);

        TextView textView1 = findViewById(R.id.Modelno);
        textView1.setText(Model);

        TextView textView2 = findViewById(R.id.CurrentUid);
        textView2.setText(CurrentUID);

        TextView textView3 = findViewById(R.id.Exdate);
        textView3.setText(Exdate);

        TextView textView4 = findViewById(R.id.engineno);
        textView4.setText(EngineNo);

        TextView textView5 = findViewById(R.id.color);
        textView5.setText(Color);

        TextView textView6 = findViewById(R.id.Capacity);
        textView6.setText(Capacity);

        TextView textView7 = findViewById(R.id.VehiNo);
        textView7.setText(VehicleNo);
    }
}