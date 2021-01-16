package com.example.mrclaim.MyAccidents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mrclaim.R;
import com.example.mrclaim.ShowCase.ShowCaseActivity;

public class Final_instructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_instructions_activity);
    }

    public void goToMyCase(View view) {
        startActivity( new Intent(Final_instructionsActivity.this, ShowCaseActivity.class));
        finish();

    }
}