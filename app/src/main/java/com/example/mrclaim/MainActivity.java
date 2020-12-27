package com.example.mrclaim;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    private ImageView imageView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // image flipper
        int images[] = {R.drawable.image1,R.drawable.image2,R.drawable.image3};
        viewFlipper = findViewById(R.id.flipper);
        for(int image: images) {
            flipperImages(image);
        }
        //Toolbar
            toolbar= findViewById(R.id.drawer_toolbar);
            setSupportActionBar(toolbar);

            //Navigation drawer
            drawerLayout= findViewById(R.id.drawer);
            navigationView= findViewById(R.id.navigation_view);
            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open, R.string.close);
            drawerLayout.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
            actionBarDrawerToggle.syncState();
            actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            });
            actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_hamburg);

            //119 button_
            Button btn_emergency = (Button)findViewById(R.id.btn_emergency);
            btn_emergency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String number= "119";
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+number));
                    startActivity(intent);
                }
            });

            //button My Accident
            button= (Button) findViewById(R.id.btn_accidents);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,MyAccident.class);
                    startActivity(intent);
                }
            });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu,menu);
        return true;
    }


    public void flipperImages(int image){

        // image flipper animation controlling
        imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(5000); // image flipper interval in milliseconds.
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);


    }
}