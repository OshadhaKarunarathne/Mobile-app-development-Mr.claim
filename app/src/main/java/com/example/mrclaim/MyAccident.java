package com.example.mrclaim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyAccident extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_accident);
        Button call_agent = (Button)findViewById(R.id.call_agent);


        call_agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number= "+94 112 123456";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+number));
                startActivity(intent);
            }
        });



    }

    public void goBack(View view) {
        Intent intent = new Intent(MyAccident.this, MainActivity.class);
        startActivity(intent);
    }

    public void report(View view){
        Intent intent = new Intent (MyAccident.this,ReportAccident.class);
        startActivity(intent);
    }






}