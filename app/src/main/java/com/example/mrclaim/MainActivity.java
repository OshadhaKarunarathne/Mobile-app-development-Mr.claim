package com.example.mrclaim;

import androidx.annotation.NonNull;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ViewFlipper viewFlipper;
    private ImageView imageView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    public Button button;
    public View v;

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
        getSupportActionBar().setDisplayShowTitleEnabled(false);
            //Navigation drawer
            drawerLayout= findViewById(R.id.drawer);
            navigationView= findViewById(R.id.navigation_view);
            navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

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
            actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

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

            //emergency vehicle button
        Button btn_emergency_vehicle = (Button)findViewById(R.id.btn_emergency_vehicle);
        btn_emergency_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number= "1414";
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
        navigationDrawer();

    }

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.home:

                        //startActivity(new Intent(MainActivity.this,MyAccident.class));
                        Toast.makeText(MainActivity.this, "Returned to home", Toast.LENGTH_SHORT).show();
                        navigationView.setCheckedItem(R.id.home);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.log_out:
                        FirebaseAuth.getInstance().signOut();
                        Intent intent=new Intent(MainActivity.this,Login.class);
                        startActivity(intent);
                        break;
                    case R.id.profile:
                        Intent intent1 =new Intent(MainActivity.this,Profile_View.class);
                        startActivity(intent1);
                        break;
                    case R.id.aboutUs:
                        Intent intent2 = new Intent(MainActivity.this,AboutUS.class);
                        startActivity(intent2);
                        break;
                    case R.id.contactus:
                        Intent intent4 = new Intent(MainActivity.this,ContactUs.class);
                        startActivity(intent4);
                        break;
                }
                return false;

            }

        });
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    public void flipperImages(int image) {

        // image flipper animation controlling
        imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(5000); // image flipper interval in milliseconds.
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.log_out){
            Intent intent = new Intent(MainActivity.this,MyAccident.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu,menu);
        return true;
    }

    public void btn_accidents(View view){
        startActivity(new Intent(MainActivity.this, MyAccident.class));

    }
    public void btn_insurance(View view){

        startActivity(new Intent(MainActivity.this, MyVehicles.class));
    }
}

