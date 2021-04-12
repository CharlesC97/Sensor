package com.example.co2monitor;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class InstallActivity extends AppCompatActivity {

    private Button power;
    private Button location;
    private Button accuracy;
    private Button precaution;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Used in order to make a back arrow past_button to go back to the MainActivity


        power = findViewById(R.id.power);
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InstallActivity.this);
                builder.setTitle("Power");
                builder.setMessage("The device needs to be plugged into the wall for power.");

                builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        location = findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InstallActivity.this);
                builder.setTitle("Location");
                builder.setMessage("- Do not put it in a place that's in close proximity to people in the house for long periods of time, such as on top of a desk." + "\n" + "- Do not place it in someplace that is wet." + "\n" + "- Put it in a place that pets cannot bother it.");


                builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        accuracy = findViewById(R.id.accuracy);
        accuracy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InstallActivity.this);
                builder.setTitle("Accuracy");
                builder.setMessage("Please wait 10 minutes in order to receive accurate values.");

                builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        precaution = findViewById(R.id.precaution);
        precaution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InstallActivity.this);
                builder.setTitle("Precaution");
                builder.setMessage("- Do not place it upside down." + "\n" + "- Do not place anything on top of it.");

                builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });


    }
}