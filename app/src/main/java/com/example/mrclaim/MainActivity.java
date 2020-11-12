package com.example.mrclaim;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // image flipper
        int images[] = {R.drawable.image1,R.drawable.image2,R.drawable.image3};
        viewFlipper = findViewById(R.id.flipper);
        for(int image: images){
            flipperImages(image);

        //Navigation drawer
            toolbar= findViewById(R.id.drawer_toolbar);
            setSupportActionBar(toolbar);
            drawerLayout= findViewById(R.id.drawer);
            navigationView= findViewById(R.id.navigation_view);

            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open, R.string.close);
            drawerLayout.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            actionBarDrawerToggle.syncState();
        }
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