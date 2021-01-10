package com.example.mrclaim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AboutUS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_u_s);
    }

    public void goBack(View view){
        Intent intent = new Intent(AboutUS.this,MainActivity.class);
        startActivity(intent);

    }
}