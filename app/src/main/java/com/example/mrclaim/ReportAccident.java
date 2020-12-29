package com.example.mrclaim;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportAccident extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.O)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_accident);

        EditText editText=findViewById(R.id.date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss ");
        String currentDateandTime = sdf.format(new Date());
        editText.setText(currentDateandTime);
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

