package com.example.mrclaim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.mrclaim.MyAccidents.MyAccidentNavActivity;
import com.example.mrclaim.MyVehicles.MyVehiclesActivity;
import com.example.mrclaim.SignPack.LoginActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Home extends AppCompatActivity {
//side bar
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    LinearLayout contentView;
    ActionBarDrawerToggle toggle;


 // side bar itesms
    TextView naUsername;
    ImageView navPro;


    //for image slider

    private ViewFlipper viewFlipper;
    private ImageView imageView;


    //to store the image for database
    StorageReference storageReference;





    DatabaseReference databaseReference ;
    FirebaseDatabase firebaseDatabase;
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        init();


        navigationDrawer();


        slider();



        LoadProfileData();

    }


    @Override
    protected void onStart() {
        super.onStart();
        LoadProfileData();
    }

    private void LoadProfileData() {



        //loading image from the database

        storageReference= FirebaseStorage.getInstance().getReference();
        StorageReference profileRef=storageReference.child("Users/"+FirebaseAuth.getInstance().getUid()+"/Profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso.get().load(uri)
                        .into(navPro);

            }
        });


//Log.e("Curent user id",FirebaseAuth.getInstance().getUid());


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference yourRef = rootRef.child("UserData").child(FirebaseAuth.getInstance().getUid());
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String birthday = dataSnapshot.child("birthday").getValue(String.class);
                String firstname = dataSnapshot.child("firstname").getValue(String.class);
                String lastname = dataSnapshot.child("lastname").getValue(String.class);
                String nic = dataSnapshot.child("nic").getValue(String.class);



                naUsername.setText(""+firstname);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


                Log.e("Curent user id",databaseError.getMessage());

            }
        };
        yourRef.addListenerForSingleValueEvent(eventListener);





    }

    private void slider() {
        // image flipper
        int images[] = {R.drawable.image1, R.drawable.image2, R.drawable.image3};
        for (int image : images) {
            flipperImages(image);
        }
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



     private void init() {

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);
        viewFlipper = findViewById(R.id.flipper);


        //inlize the side bar

         View heder=navigationView.getHeaderView( 0 );
         naUsername=heder.findViewById( R.id.navBar_name );
         navPro=heder.findViewById( R.id.navPro );



    }


    private void navigationDrawer() {

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:

                        //startActivity(new Intent(MainActivity.this,MyAccident.class));
                        Toast.makeText(Home.this, "Returned to home", Toast.LENGTH_SHORT).show();
                        navigationView.setCheckedItem(R.id.home);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.log_out:
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(Home.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.profile:
                        Intent intent1 = new Intent(Home.this, ProfileActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.aboutUs:
                        Intent intent2 = new Intent(Home.this, AboutUS.class);
                        startActivity(intent2);
                        break;
                    case R.id.contactus:
                        Intent intent4 = new Intent(Home.this, ContactUs.class);
                        startActivity(intent4);
                        break;

                }
                return false;
            }
        });

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

       // animateNavigationDrawer();
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    public void EmergencyCall119(View view) {
        String number = "119";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);


    }

    public void Emergency_Vehicle114(View view) {

        String number = "1414";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }

    public void goToMyAccidentScreen(View view) {

    startActivity( new Intent(Home.this, MyAccidentNavActivity.class));

    }

    public void goToMyInsuranceScreen(View view) {
        startActivity( new Intent(Home.this, MyVehiclesActivity.class));


    }
}