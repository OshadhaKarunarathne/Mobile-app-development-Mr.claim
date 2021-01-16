package com.example.mrclaim.MyVehicles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mrclaim.Model.MyVehicle_Model;
import com.example.mrclaim.Model.Report_Model;
import com.example.mrclaim.MyVehicles.Adapter.MyVehicleAdapter;
import com.example.mrclaim.R;
import com.example.mrclaim.ShowCase.Adpater.CaseViewAdapter;
import com.example.mrclaim.ShowCase.ShowCaseActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyVehiclesActivity extends AppCompatActivity {

 RecyclerView recyclerView;

    FirebaseDatabase database;
    DatabaseReference dbref;



List<MyVehicle_Model> report_modelList;
MyVehicleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_vehiles_activity);

        recyclerView=findViewById(R.id.recyclerView);



        database=FirebaseDatabase.getInstance();
        dbref=database.getReference("Vehicles");

        //Toast.makeText(MyVehiclesActivity.this,FirebaseAuth.getInstance().getUid(),Toast.LENGTH_SHORT).show();
       // Log.e("userID:",FirebaseAuth.getInstance().getUid());

        report_modelList= new ArrayList<>();
        report_modelList.clear();

        dbref.orderByChild("currentUID").equalTo(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren())
                {


                    MyVehicle_Model vehicle_model=ds.getValue(MyVehicle_Model.class);

                    report_modelList.add(vehicle_model);
                 // Log.e("error",vehicle_model.getCurrentUID());
                   //Toast.makeText(MyVehiclesActivity.this,vehicle_model.getCurrentUID().toString(),Toast.LENGTH_SHORT).show();

                }

                adapter= new MyVehicleAdapter(MyVehiclesActivity.this,report_modelList);
                recyclerView.setLayoutManager(new LinearLayoutManager(MyVehiclesActivity.this));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


                            Log.e("error",error.getMessage());

            }
        });



    }
}