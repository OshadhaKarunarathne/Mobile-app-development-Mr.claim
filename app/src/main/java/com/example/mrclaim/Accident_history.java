package com.example.mrclaim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Accident_history extends AppCompatActivity {




    ListView myListView;
    ArrayList <String> list;
    ArrayAdapter<String> adapter;

    History history;

    FirebaseDatabase database;
    DatabaseReference dbref;


    long maxid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident_history);



        history=new History();


        myListView=(ListView) findViewById(R.id.myListView);
        database=FirebaseDatabase.getInstance();
        dbref=database.getReference("Report");



        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.tvVno,list);


        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists())
                    maxid=(snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//database
        dbref.orderByChild("currentUID").equalTo(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren())
                {

                   // Log.e("error",history.getDateTime());
//                    Toast.makeText(Accident_history.this,history.getVehicleNo().toString(),Toast.LENGTH_SHORT).show();
                    history=ds.getValue(History.class);
                    list.add(history.getVehicleNo().toString()+"    -    "+history.getDateTime().toString());
                }

                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//
//        dbref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for (DataSnapshot ds:snapshot.getChildren())
//                {
//
//                    history=ds.getValue(History.class);
//                    list.add(history.getVehicleNo().toString()+"    -    "+history.getDateTime().toString());
//                }
//                myListView.setAdapter(adapter);
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//
//        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Toast.makeText(Accident_history.this,myListView.getItemAtPosition(position)+"",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Accident_history.this,garage_details.class);

                intent.putExtra("data", myListView.getItemAtPosition(position)+"");


                startActivity(intent);
            }
        });


    }





}
