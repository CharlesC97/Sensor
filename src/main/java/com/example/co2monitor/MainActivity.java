package com.example.co2monitor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


//Packages used for Firebase

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    // Public and Private members used in MainActivity/
    private TextView co2lable;
    private TextView humidityLable;
    private TextView tempLable;

    private TextView co2Levels;
    private TextView temperature;
    private TextView humidity;

    private Button suggestion;
    private Button info;
    private Button past;
    private Button realButton;





    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference co2data = mDatabase.child("co2_ppm");
    DatabaseReference tempData = mDatabase.child("temperature");
    DatabaseReference humidityData = mDatabase.child("humidity");

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;



    //Methods:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //RealButton
        realButton = (Button) findViewById(R.id.realButton);
        realButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });


        /*------Hooks------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        /*-------ToolBar------*/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*------NavigationMenu------*/





        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //
        co2lable = findViewById(R.id.label);//Indicates that the numbers below are CO2 ppms
        humidityLable = findViewById(R.id.humidityLabletextView);
        tempLable = findViewById(R.id.tempLableTextView);

        co2Levels = findViewById(R.id.realButton);//Used to show ppm
        temperature = findViewById(R.id.tempTextView);
        humidity = findViewById(R.id.humidityTextView);



    }




    //Method to open realButton Dialog
    public void openDialog() {
        RealDialog realDialog = new RealDialog();
        realDialog.show(getSupportFragmentManager(), "real dialog");

    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }

    }

    //Method used to go to the suggestion activity
    public void openSuggestionActivity() {
        Intent intent = new Intent(this, SuggestionActivity.class);
        startActivity(intent);
    }

    //Method used to go to the info activity
    public void openInfoActivity() {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);

    }

    public void openPastActivity() {
        Intent intent = new Intent(this, PastActivity.class);
        startActivity(intent);
    }

    public void openAboutActivity() {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    public void openSettingsActivity() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }





    // CO2 ppm will be shown with onStart after onCreate is called
    @Override
    protected void onStart() {
        super.onStart();




        co2data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String co2data = dataSnapshot.getValue(String.class);
                co2Levels.setText(co2data);
                /*if (Integer.parseInt(co2data) < 401) {
                    co2Levels.setTextColor(Color.parseColor("#99CC00"));
                } else if (Integer.parseInt(co2data) < 1001) {
                    co2Levels.setTextColor(Color.parseColor("#669900"));
                } else if (Integer.parseInt(co2data) < 2001) {
                    co2Levels.setTextColor(Color.parseColor("FFBB33"));
                } else if (Integer.parseInt(co2data) < 5000) {
                    co2Levels.setTextColor(Color.parseColor("FF8800"));
                } else {
                    co2Levels.setTextColor(Color.parseColor("CC00000"));
                }*/

                if (Integer.parseInt(co2data) < 401) {

                    realButton = findViewById(R.id.realButton);
                    realButton.setOnClickListener(new View.OnClickListener() {



                        @Override
                        public void onClick(View view) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                            builder.setTitle("250-400ppm");
                            builder.setMessage("Normal background concentration in outdoor ambient air.");

                            builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which){
                                    dialog.cancel();

                                }
                            });
                            builder.show();



                        }

                    });


                } else if (Integer.parseInt(co2data) < 1001) {

                    realButton = findViewById(R.id.realButton);
                    realButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                            builder.setTitle("400-1000ppm");
                            builder.setMessage("Typical concentration level of occupied indoor spaces with good air exchange.");

                            builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which){
                                    dialog.cancel();

                                }
                            });
                            builder.show();



                        }
                    });


                } else if (Integer.parseInt(co2data) < 2001) {

                    realButton = findViewById(R.id.realButton);
                    realButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                            builder.setTitle("1000-2000ppm");
                            builder.setMessage("Level associated with complaints of drowsiness and poor air.");

                            builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which){
                                    dialog.cancel();

                                }
                            });
                            builder.show();



                        }
                    });


                } else if (Integer.parseInt(co2data) < 5000) {

                    realButton = findViewById(R.id.realButton);
                    realButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                            builder.setTitle("2000-5000ppm");
                            builder.setMessage("Headaches, sleepiness and stagnant, stale, stuffy air. Poor " +
                                    " concentration, loss of attention, increased heart rate and slight nausea may also be " +
                                    " present.");

                            builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which){
                                    dialog.cancel();

                                }
                            });
                            builder.show();



                        }
                    });


                } else {


                    realButton = findViewById(R.id.realButton);
                    realButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                            builder.setTitle("5000ppm");
                            builder.setMessage(" Indication of unusual air conditions where high levels of other gases could also" +
                                    " be present. Toxicity or oxygen deprivation could occur. This is the permissible exposure " +
                                    " limit for daily workplace exposures. It is recommended that the average concentration over " +
                                    " an 8-hour period should not exceed 5,000 ppm.");

                            builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which){
                                    dialog.cancel();


                                }
                            });
                            builder.show();


                        }
                    });


                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        tempData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.getValue(String.class);
                temperature.setText(temp + " ˚C");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        humidityData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String hum = dataSnapshot.getValue(String.class);
                humidity.setText(hum + " %");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_tips:
                Intent i1 = new Intent(MainActivity.this, SuggestionActivity.class);
                startActivity(i1);
                break;
            case R.id.nav_info:
                Intent i2 = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(i2);
                break;
            case R.id.past_readings:
                Intent i3 = new Intent(MainActivity.this, PastActivity.class);
                startActivity(i3);
            case R.id.install_info:
                Intent i4 = new Intent(MainActivity.this, InstallActivity.class);
                startActivity(i4);
                break;




        }


        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}