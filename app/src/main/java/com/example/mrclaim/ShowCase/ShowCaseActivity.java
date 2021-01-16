package com.example.mrclaim.ShowCase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mrclaim.Model.Report_Model;
import com.example.mrclaim.R;
import com.example.mrclaim.ShowCase.Adpater.CaseViewAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowCaseActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    FirebaseDatabase database;
    DatabaseReference dbref;

    List<Report_Model> reportModelList;
    CaseViewAdapter caseViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_case_activity);


        init();

        LoadData();
    }

    private void LoadData() {
        database=FirebaseDatabase.getInstance();
        dbref=database.getReference("Report");

        dbref.orderByChild("currentUID").equalTo(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren())
                {


                    Report_Model report_model=ds.getValue(Report_Model.class);

                    reportModelList.add(report_model);
                    // Log.e("error",history.getDateTime());
//                    Toast.makeText(Accident_history.this,history.getVehicleNo().toString(),Toast.LENGTH_SHORT).show();

                }

                caseViewAdapter= new CaseViewAdapter(ShowCaseActivity.this,reportModelList);
                recyclerView.setLayoutManager(new LinearLayoutManager(ShowCaseActivity.this));
                recyclerView.setAdapter(caseViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void init() {
        reportModelList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
    }
}