package com.example.mrclaim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MyVehicles extends AppCompatActivity {

    private RecyclerView MyVehiclesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vehicles);

        MyVehiclesRecyclerView = findViewById(R.id.MyVehiclesView);
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("110-4321", "12-02-2021","https://i.ebayimg.com/images/g/h4EAAOSwqYBbQkKM/s-l400.jpg"));
        vehicles.add(new Vehicle("18-1234","22-03-2021","https://www.autobay.lk/wp-content/uploads/2012/10/911821-500x374.jpg"));

        VehicleRecViewAdapter adapter= new VehicleRecViewAdapter(this);
        adapter.setVehicles(vehicles);

        MyVehiclesRecyclerView.setAdapter(adapter);
        MyVehiclesRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }
}