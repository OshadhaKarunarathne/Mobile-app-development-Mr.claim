package com.example.mrclaim;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewCase extends AppCompatActivity  {

    TextView first;
    TextView second;
    TextView third;
    TextView fourth;
    TextView fifth;
    Button up;


    FirebaseDatabase database;
    DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_case);


        first=(TextView)findViewById(R.id.first);
        second=(TextView)findViewById(R.id.second);
        third=(TextView)findViewById(R.id.third);
        fourth=(TextView)findViewById(R.id.fourth);
        fifth=(TextView)findViewById(R.id.fifth);
        up=(Button)findViewById(R.id.btnupload);


    up.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            databaseReference=FirebaseDatabase.getInstance().getReference().child("Report").child("");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                   // String CaseNo=snapshot.child("CaseNo").getValue().toString();
                    String VehicleNo=snapshot.child("VehicleNo").getValue().toString();
                    String DateTime=snapshot.child("DateTime").getValue().toString();
                    String Address=snapshot.child("Address").getValue().toString();
                    String EmergencyVehicle=snapshot.child("EmergencyVehicle").getValue().toString();


                   // first.setText(CaseNo);
                    second.setText(VehicleNo);
                    third.setText(DateTime);
                    fourth.setText(Address);
                    fifth.setText(EmergencyVehicle);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    });

}}






