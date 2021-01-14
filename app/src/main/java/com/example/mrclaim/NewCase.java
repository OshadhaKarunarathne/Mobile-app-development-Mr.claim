package com.example.mrclaim;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewCase extends AppCompatActivity  {

    TextView a,b,c,d;
    Button display;

    DatabaseReference reff;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_case);

        a=(TextView) findViewById(R.id.first);
        b=(TextView) findViewById(R.id.second);
        c=(TextView) findViewById(R.id.third);
        d=(TextView) findViewById(R.id.fourth);

        display = findViewById(R.id.Display);


    display.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           // dbref.orderByChild("currentUID").equalTo(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            reff = FirebaseDatabase.getInstance().getReference().child("Report").child("CAH-7784");
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String uid = dataSnapshot.child("currentUID").getValue().toString();
                    String vehicleno = dataSnapshot.child("vehicleNo").getValue().toString();
                    String dateandtime = dataSnapshot.child("dateTime").getValue().toString();
                    String location = dataSnapshot.child("address").getValue().toString();
                    String emergencyv = dataSnapshot.child("emergencyVehicle").getValue().toString();
                    String mobility = dataSnapshot.child("mobility").getValue().toString();

                    a.setText(uid);
                    b.setText(vehicleno);
                    c.setText(dateandtime);
                    d.setText(location);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    });



}

    public void mycases(View view) {
        Intent inent = new Intent(this, Accident_history.class);
        startActivity(inent);
    }
}






