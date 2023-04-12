package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class MainActivity extends AppCompatActivity {
    private DatabaseReference data;

    private TextView txt;
    private Button button;
    private Button button2;

    //private ImageSlider imageSlider;
    private ImageButton imgbw;
    private ImageButton imgbpu;
    int i = 0;
    String mData;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); // hide the title bar
        FirebaseDatabase database = FirebaseDatabase.getInstance();
         DatabaseReference myRef = database.getReference("/c");
        DatabaseReference myRef2 = database.getReference("/b");

        data = FirebaseDatabase.getInstance().getReference("highest");

        txt = findViewById(R.id.b);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);

        ImageButton makbilittBut = (ImageButton) findViewById(R.id.makbilitt_Button);
        ImageButton imgbw = (ImageButton) findViewById(R.id.imgbw);
        ImageButton imgbpu = (ImageButton) findViewById(R.id.imgbpu);
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gotoSetting();
                        Toast.makeText(getApplicationContext(), "Hello Toast!12", Toast.LENGTH_SHORT).show();
                    }
                });

                makbilittBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goToex2activ();
                        Toast.makeText(getApplicationContext(), "Hello Toast!13", Toast.LENGTH_SHORT).show();
                    }
                });
                imgbpu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goToex1activ();
                        Toast.makeText(getApplicationContext(), "Hello Toast!11", Toast.LENGTH_SHORT).show();
                    }
                });
                imgbw.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goToex1activ();
                        Toast.makeText(getApplicationContext(), "Hello Toast!12", Toast.LENGTH_SHORT).show();
                    }
                });
    }


            public void goToex1activ() {
                Intent intent;
                intent = new Intent(MainActivity.this, ex1.class);
                startActivities(new Intent[]{intent});
            }

    public void goToex2activ() {
        Intent intent;
        intent = new Intent(MainActivity.this, ex2_makbilitt.class);
        startActivities(new Intent[]{intent});
    }

            public void gotoSetting() {
                Intent intent;
                intent = new Intent(MainActivity.this, settings.class);
                startActivities(new Intent[]{intent});
            }

        }
