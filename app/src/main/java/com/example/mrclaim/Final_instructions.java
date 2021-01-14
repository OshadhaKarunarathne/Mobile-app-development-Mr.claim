package com.example.mrclaim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Final_instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_instructions);
    }

    public void home(View view) {
        Intent intent = new Intent(Final_instructions.this,NewCase.class);
        startActivity(intent);
}
}