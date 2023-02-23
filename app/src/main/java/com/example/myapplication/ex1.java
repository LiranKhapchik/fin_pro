package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class ex1 extends AppCompatActivity {
    private DatabaseReference data;
    private TextView counter;
    int x2;
    private Button home1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);
        data = FirebaseDatabase.getInstance().getReference().child("/b");
        counter = (TextView) findViewById(R.id.counter);
        home1 = (Button) findViewById(R.id.home1);

        getSupportActionBar().hide(); // hide the title bar
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                counter.setText("counter: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                counter.setText("error, invaled!");

            }
        });



        home1.setOnClickListener(new View.OnClickListener() {
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
    }

}
