package com.example.mrclaim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        CardView customer_care = (CardView) findViewById(R.id.CustomerCare);
        CardView hotline = (CardView) findViewById(R.id.emergency_call);

        customer_care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "+94 112 123456";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });
        hotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "3210";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });
    }

    public void goBack(View view) {
        Intent intent = new Intent(ContactUs.this, MainActivity.class);
        startActivity(intent);
    }
}