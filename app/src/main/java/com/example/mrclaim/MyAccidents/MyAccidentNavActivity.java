package com.example.mrclaim.MyAccidents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.mrclaim.Home;
import com.example.mrclaim.R;
import com.example.mrclaim.ShowCase.ShowCaseActivity;

public class MyAccidentNavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_accident_nav_activity);
    }

    public void goBackToHome(View view) {

        startActivity(new Intent(MyAccidentNavActivity.this, Home.class));
    }

    public void goToReportAccident(View view) {

        startActivity(new Intent(MyAccidentNavActivity.this, Instruction.class));
    }

    public void goToShowCase(View view) {

        startActivity(new Intent(MyAccidentNavActivity.this, ShowCaseActivity.class));

    }

    public void goToCallingAgent(View view) {

        String number = "+94 112 123456";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);

    }
}