package com.example.mrclaim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class ReportAccident extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_accident);
    }


    public void onRadioButtonSelected(View view){

        Boolean isChecked=((RadioButton)view).isChecked();

        switch (view.getId())
        {
            case R.id.radioButton:
                if (isChecked)
                    showMessage("Mobility:Yes");
                break;
            case  R.id.radioButton2:
                if (isChecked)
                    showMessage("Mobility:No");
        }
        switch (view.getId())
        {
            case R.id.radioButton4:
                if (isChecked)
                    showMessage("Need Emergency Vehicle:Yes");
                break;
            case  R.id.radioButton3:
                if (isChecked)
                    showMessage("Need Emergency Vehicle:No");
        }
    }
    public void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


}
