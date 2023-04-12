package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.CountDownTimer;


import androidx.appcompat.app.AppCompatActivity;

import java.security.spec.RSAOtherPrimeInfo;
public class ex1 extends AppCompatActivity {

    private DatabaseReference alpha0;
    private DatabaseReference alpha1;
    private  DatabaseReference highest;
    private TextView timerTextView;
    private TextView generalText;

    private Button startStopButton;
    private Button resetButton;
    private Button home;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = settings.timeMs; // 60 seconds
    private int cnt = 0;
    private int time = 0;
    private double alpha_angle0 = 0;
    private double alpha_angle1 = 0;
    private double differential_angle = 0;
    double Perpendicular = 0;
    private boolean timerRunning = false;
    private boolean complete_movement = true;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);

        getSupportActionBar().hide(); // hide the title bar
        alpha0 = FirebaseDatabase.getInstance().getReference().child("/alpha00");
        alpha1 = FirebaseDatabase.getInstance().getReference().child("/alpha01");
        highest = FirebaseDatabase.getInstance().getReference().child("/highest");

        generalText = findViewById(R.id.general_text_view);
        timerTextView = findViewById(R.id.timer_text_view);

        startStopButton = findViewById(R.id.start_stop_button);
        resetButton = findViewById(R.id.reset_button);
        home = (Button) findViewById(R.id.home);
        //.......................................//

        // Read from the database
        highest = FirebaseDatabase.getInstance().getReference("highest");
        alpha0.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                alpha_angle0 = Double.valueOf(value);
                if (alpha_angle0 > 180) alpha_angle0 = 360 - alpha_angle0;

            }  @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        alpha1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                alpha_angle1 = Double.valueOf(value);
                if (alpha_angle1 > 180) alpha_angle1 = 360 - alpha_angle1;
                differential_angle = 180 - (alpha_angle0+alpha_angle1);
            }  @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
        }
    });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home();
                Toast.makeText(getApplicationContext(), "home", Toast.LENGTH_SHORT).show();
            }

            public void home() {
                Intent intent;
                intent = new Intent(ex1.this, MainActivity.class);
                startActivities(new Intent[]{intent});
            }
        });

        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    stopTimer();
                } else {
                    startTimer();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }


    private void startTimer() {
        generalText.setVisibility(View.VISIBLE);
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                // Handle timer finish here
                timerRunning = false;
                if (Integer.valueOf(String.valueOf(highest)) < cnt){
                    highest.setValue(String.valueOf(cnt));
                }
                startStopButton.setText("start");
            }
        }.start();

        timerRunning = true;
        startStopButton.setText("stop!");
//        resetButton.setVisibility(View.INVISIBLE);

    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        timerRunning = false;
        startStopButton.setText("start");
        resetButton.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        timeLeftInMillis = settings.timeMs;
        updateTimer();
        //    resetButton.setVisibility(View.INVISIBLE);
    }

    private void updateTimer() {
        int seconds = (int) (timeLeftInMillis / 1000);
        String timeLeftFormatted = String.format("%02d", seconds);
        timerTextView.setText(timeLeftFormatted + " sec.");

        generalText.setText("\ndifferential_angle:" + Math.abs(differential_angle) +"\n cnt: "+ cnt +"\nPerpendicular"+ Perpendicular);

        Perpendicular = Math.sqrt(Math.pow(20, 2) + Math.pow(40, 2) - 20 * 40 * Math.cos(Math.abs(differential_angle)));
        Perpendicular = Math.round(Perpendicular);
        if (Perpendicular < 40 && Perpendicular > 0 && complete_movement == true)
        {complete_movement = false; ++cnt;}
        else if (Perpendicular > 40 && Perpendicular < 60 && complete_movement == false) {
            complete_movement = true;
        }
    }

}

