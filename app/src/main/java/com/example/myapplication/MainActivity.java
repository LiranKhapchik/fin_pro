package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

        txt = findViewById(R.id.b);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        ImageButton imgbw = (ImageButton) findViewById(R.id.imgbw);
        ImageButton imgbpu = (ImageButton) findViewById(R.id.imgbpu);

        data = FirebaseDatabase.getInstance().getReference().child("/b");
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        i += 2;
                        mData = snapshot.getValue().toString();
                        txt.setText("data: " + " " + i);
                        String str = String.valueOf(i);

                        myRef.setValue("bey" + str);

                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        i = 0;
                        mData = snapshot.getValue().toString();
                        txt.setText("data: " + mData + " " + i);
                        myRef2.setValue("^_^");
                        myRef.setValue("hi ;)");

                    }
                });

                imgbpu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ex1activ();
                        Toast.makeText(getApplicationContext(), "Hello Toast!11", Toast.LENGTH_SHORT).show();
                    }
                });
                imgbw.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ex1activ();
                        Toast.makeText(getApplicationContext(), "Hello Toast!12", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

            public void ex1activ() {
                Intent intent;
                intent = new Intent(MainActivity.this, ex1.class);
                startActivities(new Intent[]{intent});
            }
        });
    }
}