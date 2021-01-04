package com.example.mrclaim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

                for (DataSnapshot ds:snapshot.getChildren())
                {

                    history=ds.getValue(History.class);
                    list.add(history.getVehicleNo().toString()+"    -    "+history.getDateTime().toString());
                }
                myListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
