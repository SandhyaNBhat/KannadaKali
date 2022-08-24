package com.spcreations.kannadakali;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Navigation drawer Included in version 3.0
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar myToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);




        //Set the toolbar
        myToolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, myToolbar,R.string.navigation_open,R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        navigationView.setNavigationItemSelectedListener(this);



        //Find the view that shows the categories defined on the app

        // TextView alphabets = (TextView)findViewById(R.id.alphabets);
        CardView numbers = findViewById(R.id.cv_numbers);
        CardView family = findViewById(R.id.cv_family);
        CardView colors = findViewById(R.id.cv_colors);
        CardView flowers = findViewById(R.id.cv_flowers);
        CardView fruits = findViewById(R.id.cv_fruits);
        CardView days = findViewById(R.id.cv_days);
        CardView phrases = findViewById(R.id.cv_phrases);
        CardView conversations = findViewById(R.id.cv_conversations);

        //Set clicklistener on each of the views.

     /*   alphabets.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent i = new Intent(MainActivity.this, AlphabetsActivity.class);
                startActivity(i);

            }

        });*/

        numbers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent i = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(i);

            }

        });


      family.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent i = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(i);

            }

        });

        colors.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent i = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(i);

            }

        });

        flowers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent i = new Intent(MainActivity.this, FlowersActivity.class);
                startActivity(i);

            }

        });

        fruits.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent i = new Intent(MainActivity.this, FruitsActivity.class);
                startActivity(i);

            }

        });


        days.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent i = new Intent(MainActivity.this, DaysActivity.class);
                startActivity(i);

            }

        });

        phrases.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent i = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(i);

            }

        });
       // Included for version 2 upgrade.
        conversations.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent i = new Intent(MainActivity.this, ConversationsActivity.class);
                startActivity(i);

            }

        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_item_share:
                String messageBody = "Hello, check out this Kannada Kali App, you can start learning Kannada in easy way!\n\n";
                messageBody += "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                String messageSubject = "Kannada Kali App";

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("plain/text");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, messageBody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, messageSubject);

                startActivity(Intent.createChooser(sharingIntent, "Share Using"));

                break;

            case R.id.menu_item_contact:
                String emailSubject = "Feedback - KannadaKali App";
                String[] emailAddress = new String[]{"spcreations2020@outlook.com"};

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:")); // only email apps should handle this
                emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
                }

            case R.id.menu_item_requestfeature:
                String subject = "Kannada Kali App - New Feature Request";
                String[] address = new String[]{"spcreations2020@outlook.com"};

                Intent eIntent = new Intent(Intent.ACTION_SENDTO);
                eIntent.setData(Uri.parse("mailto:")); // only email apps should handle this
                eIntent.putExtra(Intent.EXTRA_EMAIL, address);
                eIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
               /* if (eIntent.resolveActivity(getPackageManager()) != null) {
                    Log.v("TAG","Just before starting email intent");
                    startActivity(eIntent);
                }*/

                try {
                    startActivity(eIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
                }

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}

